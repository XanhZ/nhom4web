package com.nhom4web.filter;

import com.nhom4web.utils.Json;
import com.nhom4web.utils.request.SachRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SachFilter extends AbstractFilter {
    protected boolean kiemTraDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan == null || !Pattern.matches("/\\d+", duongDan)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
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
        } else {
            if (!Pattern.matches("/\\d*", duongDan)) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return false;
            }
            Pattern patternMa = Pattern.compile("\\d+");
            Matcher matcher = patternMa.matcher(duongDan);
            int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
            req.setAttribute("ma", ma > 0 ? ma : null);
        }
        return true;
    }

    protected boolean kiemTraPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String duongDan = req.getPathInfo();
        if (duongDan != null && !duongDan.equals("/")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Map<String, String> loi = new SachRequest().getLoi(req);
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
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        Pattern patternMa = Pattern.compile("\\d+");
        Matcher matcher = patternMa.matcher(duongDan);
        int ma = matcher.find() ? Integer.parseInt(matcher.group()) : -1;
        Map<String, String> loi = new SachRequest().getLoi(req);
        if (loi.size() != 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Json.chuyenThanhJson(resp, loi);
            return false;
        }
        req.setAttribute("ma", ma > 0 ? ma : null);
        return true;
    }
}
