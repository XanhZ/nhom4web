package com.nhom4web.filter;

import com.nhom4web.utils.Json;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractFilter implements Filter {
    /**
     * Một số luật cơ bản của dữ liệu
     */
    public static final String KHONG_BO_TRONG = "khong-bo-trong";
    public static final String SO_DUONG = "so-duong";
    public static final String SO_NGUYEN_DUONG = "so-nguyen-duong";
    public static final String EMAIL = "email";
    public static final String SDT = "sdt";
    public static final String MAT_KHAU = "mat-khau";

    /**
     * Regular Expression
     */
    public static final String REGEX_SO_DUONG = "\\d+(.\\d+)?";
    public static final String REGEX_SO_NGUYEN_DUONG = "^[1-9]\\d*";
    public static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public static final String REGEX_SDT = "\\d{10}";
    public static final String REGEX_MAT_KHAU = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,32}$";
    public static final String REGEX_HINH_ANH = "([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";

    /**
     * Tập các luật của Filter
     */
    public final Map<String, String> luat = new HashMap<>();
    public final Map<String, String> e = new HashMap<>();

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Kiểm tra hợp lệ
        boolean hopLe = true;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String httpMethod = req.getMethod();
        switch (httpMethod) {
            case "DELETE": {
                hopLe = this.kiemTraDelete(req, resp);
                break;
            }
            case "GET": {
                hopLe = this.kiemTraGet(req, resp);
                break;
            }
            case "POST": {
                hopLe = this.kiemTraPost(req, resp);
                break;
            }
            case "PUT": {
                hopLe = this.kiemTraPut(req, resp);
                break;
            }
            default: {
                hopLe = false;
                resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                break;
            }
        }
        // Chuyển tiếp tới filter, controller tiếp theo nếu hợp lệ
        if (hopLe) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * Kiểm tra dữ liệu của Request dựa trên các luật
     *
     * @param req Request cần kiểm tra dữ liệu
     * @return Lỗi của dữ liệu
     */
    public Map<String, String> getLoi(HttpServletRequest req) {
        Map<String, String> loi = new HashMap<>();
        Map<String, String[]> paraMap = req.getParameterMap();
        this.luat.forEach((truong, luatStr) -> {
            String[] duLieus = paraMap.get(truong);
            String[] luats = luatStr.split("/");
            for (String luat : luats) {
                if (!isHopLe(duLieus, luat)) {
                    loi.put(truong, e.get(truong + "." + luat));
                    break;
                }
            }
        });
        return loi;
    }

    /**
     * Kiểm tra tên các file gửi đến có phải là file image
     * @param files Các file được gửi đến từ Request
     * @return Lỗi nếu tên file không hợp lệ ngược lại trả về null
     */
    public String kiemTraFiles(Collection<Part> files) {
        for (Part file : files) {
            String tenFile = this.layTenFile(file);
            if (!Pattern.matches(REGEX_HINH_ANH, tenFile)) return "Hình ảnh không đúng định dạng";
        }
        return null;
    }

    /**
     * Kiểm tra tên file gửi đến có phải là file image
     * @param file file được gửi đến từ Request
     * @return Lỗi nếu tên file không hợp lệ ngược lại trả về null
     */
    public String kiemTraFile(Part file) {
        if (file == null) return "Không được để trống ảnh";
        if (!Pattern.matches(REGEX_HINH_ANH, this.layTenFile(file))) return "Hình ảnh không đúng định dạng";
        return null;
    }

    /**
     * Lấy tên file từ Content-Disposition
     * @param file Part được gửi tới từ request
     * @return tên file
     */
    private String layTenFile(Part file) {
        Matcher matcher = Pattern.compile("filename=\".*\"").matcher(file.getHeader("content-disposition"));
        if (matcher.find()) {
            String tmp = matcher.group();
            return tmp.substring(10, tmp.length() - 1);
        }
        return null;
    }

    /**
     * Kiểm tra dữ liệu có đúng luật không
     *
     * @param duLieus Dữ liệu cần kiểm tra
     * @param luat   Luật của dữ liệu
     * @return true nếu dữ liệu hợp lệ và ngược lại
     */
    private boolean isHopLe(String[] duLieus, String luat) {
        if (luat.equals(KHONG_BO_TRONG)) {
            return duLieus != null && duLieus.length != 0;
        }
        for (String duLieu : duLieus) {
            switch (luat) {
                case SO_DUONG:
                    return Pattern.matches(REGEX_SO_DUONG, duLieu);

                case SO_NGUYEN_DUONG:
                    return Pattern.matches(REGEX_SO_NGUYEN_DUONG, duLieu);

                case EMAIL:
                    return Pattern.matches(REGEX_EMAIL, duLieu);

                case SDT:
                    return Pattern.matches(REGEX_SDT, duLieu);

                case MAT_KHAU:
                    return Pattern.matches(REGEX_MAT_KHAU, duLieu);
            }
        }
        return true;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    protected boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan == null || !Pattern.matches("/\\d+", duongDan)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(duongDan);
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }

    protected boolean kiemTraGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan == null) {
            req.setAttribute("ma", null);
            return true;
        }
        if (!Pattern.matches("/\\d+", duongDan)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(duongDan);
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }

    protected boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan != null) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Map<String, String> loi = this.getLoi(req);
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        return true;
    }

    protected boolean kiemTraPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan == null || !Pattern.matches("/\\d+", duongDan)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(duongDan);
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        Map<String, String> loi = this.getLoi(req);
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }
}
