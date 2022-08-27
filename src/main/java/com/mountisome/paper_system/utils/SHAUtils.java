package com.mountisome.paper_system.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtils {

    public static String getSHA(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] shaBytes = md.digest(str.getBytes());
        return bytesToHexString(shaBytes);
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (byte aByte : bytes) {
            int v = aByte & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
