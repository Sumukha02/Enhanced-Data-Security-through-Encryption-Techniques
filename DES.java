package com.FirstProject;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.Random;
final class DES {
    byte[] skey = new byte[1000];
    String skeyString;
    static byte[] raw;
    String inputUsername,inputPassword,encryptedData,decryptedPassword;
    public DES() {
        JFrame f1= new JFrame("DES Algorithm");
        JLabel l1 = new JLabel("USERNAME: ");
        JLabel l2 = new JLabel("PASSWORD: ");
        l1.setFont(new Font("Verdana", Font.ITALIC, 25));
        l1.setBounds(20,100,400,170);
        f1.add(l1);
        l2.setFont(new Font("Verdana", Font.ITALIC, 25));
        l2.setBounds(20,150,400,170);
        f1.add(l2);
        JTextField t1 = new JTextField("");
        t1.setBounds(200,170,180,30);
        f1.add(t1);
        JTextField t11 = new JTextField("");
        t11.setBounds(200,220,180,30);
        f1.add(t11);
        JButton b = new JButton("ENTER");
        b.setBackground(Color.ORANGE);
        b.setBounds(100,300,200,40);
        f1.add(b);
        f1.setSize(500,500);
        f1.setLayout(null);
        f1.setResizable(false);
        f1.setVisible(true);
        f1.getContentPane().setBackground(new Color(103,50,245));
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    generateSymmetricKey();

                    inputUsername= t1.getText();
                    inputPassword= t11.getText();
                    JOptionPane.showMessageDialog(null,"Entered Successfully");
                    byte[] ibyte = inputPassword.getBytes();
//                    byte[] dbyte= decrypt(raw,encrypt(raw, ibyte));
                    byte[] ebyte=encrypt(raw, ibyte);
//                    byte[] ebyte2 = encrypt(raw,ebyte);
//                    byte[] ebyte3 = encrypt(raw,ebyte2);
                    byte[] dbyte= decrypt(raw,ebyte);

                    String encryptedData = new String(ebyte);
//                    String encbyte3 = new String(ebyte3);
                    System.out.println("Encrypted Password "+encryptedData);
                    JOptionPane.showMessageDialog(null,"Encrypted Data "+"\n"+encryptedData);

                    int result = JOptionPane.showConfirmDialog(f1,"Do want to decrypt the code", "DES Algorithm",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if(result == JOptionPane.YES_OPTION){
                        String decryptedPassword = new String(dbyte);
                        System.out.println("Decrypted Password "+decryptedPassword);
                                JOptionPane.showMessageDialog(null,"Decrypted Password "+"\n"+decryptedPassword);
                    }else {
                        System.out.println("Program ended");
                    }

                }
                catch(Exception er) {
                    System.out.println(er);
                }
            }
        });

    }
    void generateSymmetricKey() {
        try {
            Random r = new Random();
            int num = r.nextInt(10000);
            String knum = String.valueOf(num);
            byte[] knumb = knum.getBytes();
            skey=getRawKey(knumb);
            skeyString = new String(skey);
            System.out.println("DES Symmetric key = "+skeyString);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }private static byte[] getRawKey(byte[] seed) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("DES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(56, sr);
        SecretKey skey = kgen.generateKey();
        raw = skey.getEncoded();
        return raw;
    }
    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }
    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "DES");
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }
    public static void main(String args[]) {
        DES des = new DES();
    }}