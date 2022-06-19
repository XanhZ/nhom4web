package com.nhom4web.dao.impl;

import com.nhom4web.dao.IDAO;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractDAO<T> implements IDAO<T> {
    /**
     * Thông tin cơ sở dữ liệu
     */
    private static final String URL = "jdbc:mysql://localhost:3306/nhom4_web?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "034201";
    public static Connection ketNoi;

    /**
     * Thông tin bảng
     */
    protected final String tenBang;

    public AbstractDAO(String tenBang) {
        this.tenBang = tenBang;
        if (AbstractDAO.ketNoi == null) {
            this.taoKetNoi();
        }
    }

    /**
     * Cập nhật đối tượng trong cơ sở dữ liệu
     *
     * @param t đối tượng cần cập nhật
     * @param luu commit vào cơ sở dữ liệu (true|false)
     * @return true nếu thành công, false nếu thất bại
     */
    @Override
    public boolean capNhat(T t, boolean luu) {
        try {
            LinkedHashMap<String, Object> duLieu = this.sangMap(t);
            int ma = (int) duLieu.remove("ma");
            List<String> temp = duLieu.keySet().stream().map(o -> o + " = ?").collect(Collectors.toList());
            String sql = String.format(
                    "UPDATE %s SET %s WHERE ma = %d",
                    this.tenBang,
                    String.join(", ", temp),
                    ma
            );
            PreparedStatement ps = ketNoi.prepareStatement(sql);

            AbstractDAO.setThamSoTruyVan(ps, duLieu.values());
            ps.executeUpdate();

            if (luu) ketNoi.commit();
            ps.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            if (luu) this.rollback();
        }
        return false;
    }

    /**
     * Cập nhật một danh sách các đối tượng trong cơ sở dữ liệu
     *
     * @param list danh sách các đối tượng cần cập nhật
     * @param luu  commit vào cơ sở dữ liệu (true|false)
     * @return true nếu thành công, ngược lại trả về false
     */
    @Override
    public boolean capNhat(List<T> list, boolean luu) {
        for (T t : list) {
            if (!this.capNhat(t, luu)) return false;
        }
        return true;
    }

    /**
     * Lấy tất cả các bản ghi trong cơ sở dữ liệu
     *
     * @return Danh sách các thực thể nếu thành công, ngược lại trả về null
     */
    @Override
    public List<T> layTatCa() {
        try {
            String sql = String.format("SELECT * FROM %s", this.tenBang);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            List<T> list = this.sangThucThes(ps.executeQuery());
            ps.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Tìm bản ghi theo mã
     *
     * @param ma mã của thực thể cần tìm
     * @return một thực thể nếu tìm thấy, ngược lại trả về null
     */
    @Override
    public T tim(int ma) {
        T t = null;
        try {
            String sql = String.format("SELECT * FROM %s WHERE ma = %d", this.tenBang, ma);
            PreparedStatement ps = ketNoi.prepareStatement(sql);
            t = this.sangThucThe(ps.executeQuery());
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * Thiết lập tham số tại index chỉ định
     *
     * @param ps     đối tượng PreparedStatement cần chèn tham số
     * @param index  chỉ số cần chèn
     * @param thamSo dữ liệu cần chèn
     */
    public static void setThamSoTai(PreparedStatement ps, int index, Object thamSo) {
        try {
            if (thamSo instanceof Integer) {
                ps.setInt(index, (Integer) thamSo);
            } else if (thamSo instanceof Long) {
                ps.setLong(index, (Long) thamSo);
            } else if (thamSo instanceof String) {
                ps.setString(index, (String) thamSo);
            } else if (thamSo instanceof Boolean) {
                ps.setBoolean(index, (Boolean) thamSo);
            } else if (thamSo instanceof Timestamp) {
                ps.setTimestamp(index, (Timestamp) thamSo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Chèn các tham số truy vấn vào ps
     *
     * @param ps             đối tượng PreparedStatement cần chèn tham số
     * @param thamSoTruyVans các tham số dưới dạng args
     */
    public static void setThamSoTruyVan(PreparedStatement ps, Object... thamSoTruyVans) {
        int i = 0;
        for (Object thamSo : thamSoTruyVans) {
            AbstractDAO.setThamSoTai(ps, ++i, thamSo);
        }
    }

    /**
     * Chèn các tham số truy vấn vào ps
     *
     * @param ps             đối tượng PreparedStatement cần chèn tham số
     * @param thamSoTruyVans các tham số chứa trong Collection
     */
    public static void setThamSoTruyVan(PreparedStatement ps, Collection<?> thamSoTruyVans) {
        int i = 0;
        for (Object thamSo : thamSoTruyVans) {
            AbstractDAO.setThamSoTai(ps, ++i, thamSo);
        }
    }

    /**
     * Tạo một kết nối chung tới cơ sở dữ liệu
     */
    protected void taoKetNoi() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            AbstractDAO.ketNoi = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            ketNoi.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Rollback lại transaction
     */
    protected void rollback() {
       try {
           ketNoi.rollback();
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }

    /**
     * Thêm một hàng vào cơ sở dữ liệu
     *
     * @param t đối tượng thêm vào cơ sở dữ liệu
     * @param luu commit vào cơ sở dữ liệu (true|false)
     * @return true nếu thành công, ngược lại trả về false
     */
    @Override
    public boolean them(T t, boolean luu) {
        try {
            LinkedHashMap<String, Object> duLieu = this.sangMap(t);
            String sql = String.format(
                    "INSERT INTO %s (%s) VALUES (%s)",
                    this.tenBang,
                    String.join(", ", duLieu.keySet()),
                    String.join(", ", Collections.nCopies(duLieu.size(), "?"))
            );
            PreparedStatement ps = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            AbstractDAO.setThamSoTruyVan(ps, duLieu.values());
            ps.executeUpdate();
            this.setKhoaChinh(t, ps.getGeneratedKeys());

            if (luu) ketNoi.commit();
            ps.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (luu) this.rollback();
        }
        return false;
    }

    /**
     * Thêm nhiều hàng vào cơ sở dữ liệu
     *
     * @param list danh sách đối tượng cần thêm vào cơ sở dữ liệu
     * @param luu commit vào cơ sở dữ liệu (true|false)
     * @return true nếu thành công, ngược lại trả về false
     */
    @Override
    public boolean them(List<T> list, boolean luu) {
        try {
            LinkedHashMap<String, Object> duLieu = this.sangMap(list.get(0));
            String temp = String.join(", ", Collections.nCopies(duLieu.size(), "?"));
            String sql = String.format(
                    "INSERT INTO %s (%s) VALUES %s",
                    this.tenBang,
                    String.join(", ", duLieu.keySet()),
                    String.join(", ", Collections.nCopies(list.size(), "(" + temp + ")"))
            );
            PreparedStatement ps = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            int i = 0;
            for (T t: list) {
                for (Object o : this.sangMap(t).values()) {
                    AbstractDAO.setThamSoTai(ps, ++i, o);
                }
            }

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            for (T t : list) this.setKhoaChinh(t, rs);

            if (luu) ketNoi.commit();
            ps.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (luu) this.rollback();
        }
        return false;
    }

    /**
     * Thêm một hàng vào cơ sở dữ liệu
     *
     * @param duLieu chứa dữ liệu dưới cặp key - value
     * @return khóa chính của thực thể, trả về -1 nếu thất bại
     */
    public int them(LinkedHashMap<String, Object> duLieu) {
        int khoaChinh = -1;
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
                PreparedStatement ps = ketNoi.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                AbstractDAO.setThamSoTruyVan(ps, duLieu.values());
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    khoaChinh = rs.getInt(1);
                }
                ketNoi.commit();
                ps.close();
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

    /**
     * Xóa bản ghi theo mã
     *
     * @param ma mã của thực thể cần xóa
     * @param luu commit vào cơ sở dữ liệu (true|false)
     * @return true nếu thành công, false nếu thất bại
     */
    @Override
    public boolean xoa(int ma, boolean luu) {
        try {
            String sql = String.format("DELETE FROM %s WHERE ma = ?", this.tenBang);
            PreparedStatement ps = ketNoi.prepareStatement(sql);

            AbstractDAO.setThamSoTruyVan(ps, ma);
            ps.executeUpdate();

            if (luu) ketNoi.commit();
            ps.close();

            return true;
        } catch (SQLException e) {
            if (luu) this.rollback();
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Chuyển ResultSet rs sang danh sách thực thể
     *
     * @param rs kết quả của truy vấn
     * @return danh sách thực thể
     */
    protected abstract List<T> sangThucThes(ResultSet rs);

    /**
     * Chuyển ResultSet rs sang một thực thể
     *
     * @param rs kết quả của truy vấn
     * @return một thực thể
     */
    protected abstract T sangThucThe(ResultSet rs);

    /**
     * Chuyển đối tượng sang các cột trong bảng
     *
     * @param t đối tượng cần chuyển đối
     * @return Map Key - Value tương ứng với cột - giá trị trong bảng
     */
    protected abstract LinkedHashMap<String, Object> sangMap(T t);

    /**
     * Thiết lập khóa chính của đối tượng sau khi thêm thành công vào cơ sở dữ liệu
     *
     * @param t  đối tượng cần thiết lập khóa chính
     * @param rs khóa chính được trả về trong ResultSet
     */
    protected void setKhoaChinh(T t, ResultSet rs) throws Exception {
        if (rs.next()) {
            t.getClass().getMethod("setMa", Integer.class).invoke(t, rs.getInt(1));
        }
    }
}