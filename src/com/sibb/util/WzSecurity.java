package com.sibb.util;

import com.sibb.Client;

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
        Client c = Client.getClient();
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
            byte messageDigest[] = m.digest(c.getPassword().getBytes());
            for (int i = 0; i < 1000; i++)
                messageDigest = m.digest(messageDigest);

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));

            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
