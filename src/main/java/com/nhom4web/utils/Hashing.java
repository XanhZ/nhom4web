package com.nhom4web.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    public static String maHoaSHA256(String chuoiGoc) throws NoSuchAlgorithmException {
        MessageDigest msgds = MessageDigest.getInstance("SHA-256");
        // Ma hoa
        byte[] hashMaHoa = msgds.digest(chuoiGoc.getBytes(StandardCharsets.UTF_8));

        // Chuyen cac byte sang hex string
        StringBuilder chuoiHex = new StringBuilder(2 * hashMaHoa.length);
        for (byte b : hashMaHoa) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                chuoiHex.append('0');
            }
            chuoiHex.append(hex);
        }

        return chuoiHex.toString();
    }
}
