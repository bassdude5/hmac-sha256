package com.hmac;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMAC {
    public static String encode(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256"); // use this algorithm
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256"); // generate secret key
        sha256_HMAC.init(secret_key); // assign it

        return Base64.getEncoder().encodeToString(sha256_HMAC.doFinal(data.getBytes("UTF-8"))); // generate hash of base
                                                                                                // 64 encoded string
    }
}