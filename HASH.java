package com.FirstProject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;
class Hash {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    public Hash() {
        try {
            String inputMessage=JOptionPane.showInputDialog(null,"Enter password to encrpyt");
            String hmac_message = calculateRFC2104HMAC(inputMessage, "key1");
            JOptionPane.showMessageDialog(null," decrypted password: "+hmac_message);
        }
        catch(HeadlessException | InvalidKeyException | NoSuchAlgorithmException |
                SignatureException e) {
            System.out.println(e);
        }}
    private static String toHexString(byte[] bytes)
    {
        Formatter formatter = new Formatter();
        for (byte b : bytes)
        {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
    public static String calculateRFC2104HMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException,
            InvalidKeyException
    {
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(),
                HMAC_SHA1_ALGORITHM);
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);return toHexString(mac.doFinal(data.getBytes()));
    }
    public static void main(String args[]) {
        Hash fh = new Hash();
    }}