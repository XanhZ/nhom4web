package com.nhom4web.dao.impl;

import com.nhom4web.dao.IDAO;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractDAO<T> implements IDAO<T> {
    /**
     * Thông tin cơ sở dữ liệu
     */
    private static final String URL = "jdbc:mysql://localhost:3306/nhom4_web";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    /**
     * Thông tin bảng
     */
    protected String tenBang;

    /**
     * Đóng kết nối, truy vấn và kết quả
     *
     * @param ketNoi kết nối tới cơ sở dũ liệu
     * @param stmt   câu truy vấn
     * @param rs     kết quả trả về của truy vấn
     */
    protected void dongTruyVan(Connection ketNoi, PreparedStatement stmt, ResultSet rs) {
        try {
            if (ketNoi != null) {
                ketNoi.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Tạo kết nối tới cơ sở dữ liệu
     *
     * @return một kết nối
     */
    protected Connection getKetNoi() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lấy tất cả các bản ghi trong cơ sở dữ liệu
     *
     * @return Danh sách các thực thể
     */
    public List<T> layTatCa() {
        List<T> list = new ArrayList<>();
        try {
            String sql = String.format("SELECT * FROM %s", this.tenBang);
            Connection ketNoi = this.getKetNoi();
            PreparedStatement stmt = ketNoi.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(this.sangThucThe(rs));
            }
            this.dongTruyVan(ketNoi, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Tìm bản ghi theo mã
     *
     * @param ma mã của thực thể cần tìm
     * @return một thực thể nếu tìm thấy, ngược lại trả về null
     */
    public T layTheoMa(int ma) {
        T t = null;
        try {
            String sql = String.format("SELECT * FROM %s WHERE ma = ?", this.tenBang);
            Connection ketNoi = this.getKetNoi();
            PreparedStatement stmt = ketNoi.prepareStatement(sql);
            this.setThamSoTruyVan(stmt, ma);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                t = this.sangThucThe(rs);
            }
            this.dongTruyVan(ketNoi, stmt, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * Chuyển ResultSet rs sang thực thể
     *
     * @param rs một hàng của kết quả truy vấn
     * @return một thực thể
     */
    protected abstract T sangThucThe(ResultSet rs);

    /**
     * Thiết lập tham số tại index chỉ định
     *
     * @param stmt   đối tượng PreparedStatement cần chèn tham số
     * @param index  chỉ số cần chèn
     * @param thamSo dữ liệu cần chèn
     */
    protected final void setThamSoTai(PreparedStatement stmt, int index, Object thamSo) {
        try {
            if (thamSo instanceof Integer) {
                stmt.setInt(index, (Integer) thamSo);
            } else if (thamSo instanceof Long) {
                stmt.setLong(index, (Long) thamSo);
            } else if (thamSo instanceof String) {
                stmt.setString(index, (String) thamSo);
            } else if (thamSo instanceof Boolean) {
                stmt.setBoolean(index, (Boolean) thamSo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chèn các tham số truy vấn vào stmt
     *
     * @param stmt           đối tượng PreparedStatement cần chèn tham số
     * @param thamSoTruyVans các tham số dưới dạng args
     */
    protected final void setThamSoTruyVan(PreparedStatement stmt, Object... thamSoTruyVans) {
        int i = 0;
        for (Object thamSo : thamSoTruyVans) {
            this.setThamSoTai(stmt, ++i, thamSo);
        }
    }

    /**
     * Chèn các tham số truy vấn vào stmt
     *
     * @param stmt           đối tượng PreparedStatement cần chèn tham số
     * @param thamSoTruyVans các tham số chứa trong Collection
     */
    protected final void setThamSoTruyVan(PreparedStatement stmt, Collection<?> thamSoTruyVans) {
        int i = 0;
        for (Object thamSo : thamSoTruyVans) {
            this.setThamSoTai(stmt, ++i, thamSo);
        }
    }

    /**
     * Thiết lập tên bảng
     *
     * @param tenBang tên bảng cần thiết lập
     */
    protected final void setTenBang(String tenBang) {
        this.tenBang = tenBang;
    }

    /**
     * Thêm một hàng vào cơ sở dữ liệu
     *
     * @param duLieu chứa dữ liệu dưới cặp key - value
     * @return khóa chính của thực thể, trả về -1 nếu thất bại
     */
    public int them(LinkedHashMap<String, Object> duLieu) {
        int khoaChinh = -1;
        Connection ketNoi = this.getKetNoi();
        if (ketNoi != null) {
            try {
                String[] temp = new String[duLieu.size()];
                Arrays.fill(temp, "?");
                String sql = String.format(
                        "INSERT INTO %s (%s) VALUES (%s)",
                        this.tenBang,
                        String.join(", ", duLieu.keySet()),
                        String.join(", ", temp)
                );
                ketNoi.setAutoCommit(false);
                PreparedStatement stmt = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                this.setThamSoTruyVan(stmt, duLieu.values());
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    khoaChinh = rs.getInt(1);
                }
                ketNoi.commit();
                this.dongTruyVan(ketNoi, stmt, rs);
            } catch (SQLException e) {
                try {
                    ketNoi.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return khoaChinh;
    }

    ;

    /**
     * Cập nhật thực thể theo mã
     *
     * @param duLieu chứa dữ liệu dưới cặp key - value
     * @param ma     mã của thực thể cần cập nhật
     * @return true nếu cập nhật thành công
     */
    public boolean capNhat(LinkedHashMap<String, Object> duLieu, int ma) {
        Connection ketNoi = this.getKetNoi();
        if (ketNoi != null) {
            try {
                List<String> temp = duLieu.keySet().stream().map(o -> o + " = ?").collect(Collectors.toList());
                String sql = String.format(
                        "UPDATE %s SET %s WHERE ma = %d",
                        this.tenBang,
                        String.join(", ", temp),
                        ma
                );
                ketNoi.setAutoCommit(false);
                PreparedStatement stmt = ketNoi.prepareStatement(sql);
                this.setThamSoTruyVan(stmt, duLieu.values());
                stmt.executeUpdate();
                ketNoi.commit();
                this.dongTruyVan(ketNoi, stmt, null);
                return true;
            } catch (SQLException e) {
                try {
                    ketNoi.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Xóa bản ghi theo mã
     *
     * @param ma mã của thực thể cần xóa
     * @return true nếu xóa thành công
     */
    public boolean xoaTheoMa(int ma) {
        Connection ketNoi = this.getKetNoi();
        if (ketNoi != null) {
            try {
                String sql = String.format("DELETE FROM %s WHERE ma = ?", this.tenBang);
                ketNoi.setAutoCommit(false);
                PreparedStatement stmt = ketNoi.prepareStatement(sql);
                this.setThamSoTruyVan(stmt, ma);
                stmt.executeUpdate();
                ketNoi.commit();
                this.dongTruyVan(ketNoi, stmt, null);
                return true;
            } catch (SQLException e) {
                try {
                    ketNoi.rollback();
                } catch (SQLException e1) {
                    e.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * Xóa các bản ghi có mã nằm trong danh sách
     *
     * @param mas mảng chứa danh sách các mã cần xóa
     * @return true nếu xóa thành công
     */
    public boolean xoaTheoMa(int[] mas) {
        Connection ketNoi = this.getKetNoi();
        if (ketNoi != null) {
            try {
                String sql = String.format("DELETE FROM %s WHERE ma IN ?", this.tenBang);
                ketNoi.setAutoCommit(false);
                PreparedStatement stmt = ketNoi.prepareStatement(sql);
                this.setThamSoTruyVan(stmt, (Object) mas);
                stmt.executeUpdate();
                ketNoi.commit();
                this.dongTruyVan(ketNoi, stmt, null);
                return true;
            } catch (SQLException e) {
                try {
                    ketNoi.rollback();
                } catch (SQLException e1) {
                    e.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return false;
    }
}
