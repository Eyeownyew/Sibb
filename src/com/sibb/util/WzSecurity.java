package com.sibb.util;

import java.security.MessageDigest;

/**
 * @author Eyeownywe
 * @version $Revision: 1.0 $
 */
public class WzSecurity {

    /**
     * Method md5.
     *
     * @param s String
     * @return String
     */
    public static String md5(String s) {
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            byte messageDigest[] = m.digest(s.getBytes());
            messageDigest = m.digest(messageDigest);

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest)
                hexString.append(Integer.toHexString(0xFF & b));

            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
