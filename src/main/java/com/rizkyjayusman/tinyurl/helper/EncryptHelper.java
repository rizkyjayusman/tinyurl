package com.rizkyjayusman.tinyurl.helper;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class EncryptHelper {
    public static String encrypt(String url) {
        log.info("EncryptHelper.encrypt() :: start encrypt string value :: {}", url);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(url.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            String encryptedStr = hexString.substring(0, 8);
            log.info("EncryptHelper.encrypt() :: finish encrypt string value :: {}", encryptedStr);

            return encryptedStr;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
}
