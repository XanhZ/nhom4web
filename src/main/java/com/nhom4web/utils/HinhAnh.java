package com.nhom4web.utils;

import com.cloudinary.Cloudinary;
import com.nhom4web.model.HinhAnhSach;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HinhAnh {
    private static final String CLOUDINARY_URL = "cloudinary://923346781537818:IXWJ1uydnFdLioVJ8-4G4HcB4Kc@xanhz";
    private static final Cloudinary CLOUDINARY = new Cloudinary(CLOUDINARY_URL);

    public static List<HinhAnhSach> luuVaoCloud(List<Part> hinhAnhs) {
        List<HinhAnhSach> hinhAnhSachs = new ArrayList<>();
        for (Part hinhAnh : hinhAnhs) {
            try {
                HinhAnhSach hinhAnhSach = new HinhAnhSach();
                File temp = File.createTempFile("temp", HinhAnh.getDuoiMoRong(hinhAnh));
                FileUtils.copyInputStreamToFile(hinhAnh.getInputStream(), temp);
                Map res = CLOUDINARY.uploader().upload(temp, null);
                hinhAnhSach.setDuongDan((String) res.get("secure_url"));
                hinhAnhSach.setPublicId((String) res.get("public_id"));
                hinhAnhSachs.add(hinhAnhSach);
                boolean xoaTemp = temp.delete();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return hinhAnhSachs;
    }

    public static String getDuoiMoRong(Part hinhAnh) {
        String tenFile = hinhAnh.getSubmittedFileName();
        return tenFile.substring(tenFile.lastIndexOf('.'));
    }

    public static boolean xoaTrenCloud(List<String> publicIds) {
        try {
            CLOUDINARY.api().deleteResources(publicIds, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
