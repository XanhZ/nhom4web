package com.nhom4web.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Json {
    /**
     * Trả về json của đối tượng trong response
     *
     * @param resp HttpServletResponse cần trả về client
     * @param obj  đối tượng cần chuyển sang JSON
     * @throws IOException
     */
    public static void chuyenThanhJson(HttpServletResponse resp, Object obj) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String ketQua = gson.toJson(obj);
        PrintWriter writer = resp.getWriter();
        writer.write(ketQua);
        writer.flush();
    }
}
