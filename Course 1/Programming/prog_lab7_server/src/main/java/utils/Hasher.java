package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    private static String algName = "MD2";
    private static String pepper = "E!@$ @RN#3f";

    public static String hash(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance(algName);
            return new String(md.digest((pepper + password + salt).getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


}
