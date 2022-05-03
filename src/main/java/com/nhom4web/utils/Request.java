package com.nhom4web.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Request {
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

    /**
     * Tập các luật của Request
     */
    public final Map<String, String> luat = new HashMap<>();
    public final Map<String, String> e = new HashMap<>();

    public Map<String, String> getLoi(HttpServletRequest req) {
        Map<String, String> loi = new HashMap<>();
        this.luat.forEach((truong, luatStr) -> {
            String duLieu = req.getParameter(truong);
            String[] luats = luatStr.split("/");
            for (String luat : luats) {
                if (!isHopLe(duLieu, luat)) {
                    loi.put(truong, e.get(truong + "." + luat));
                    break;
                }
            }
        });
        return loi;
    }

    private boolean isHopLe(String duLieu, String luat) {
        if (duLieu == null) {
            return false;
        }
        switch (luat) {
            case KHONG_BO_TRONG: return !duLieu.equals("");

            case SO_DUONG: return Pattern.matches(REGEX_SO_DUONG, duLieu);

            case SO_NGUYEN_DUONG: return Pattern.matches(REGEX_SO_NGUYEN_DUONG, duLieu);

            case EMAIL: return Pattern.matches(REGEX_EMAIL, duLieu);

            case SDT: return Pattern.matches(REGEX_SDT, duLieu);

            case MAT_KHAU: return Pattern.matches(REGEX_MAT_KHAU, duLieu);

            default: return true;
        }
    }
}
