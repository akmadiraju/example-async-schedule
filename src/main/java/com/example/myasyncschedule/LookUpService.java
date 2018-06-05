package com.example.myasyncschedule;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class LookUpService{
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    @Async
    public void userFunction(String name) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] dataBytes = new byte[1024];
        int nread = 0;
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("static/"+name))) {
            while ((nread = inputStream.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        byte[] mdbytes = md.digest();
        //convert the byte to hex format method 1
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
            buffer.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

            System.out.println("Hex format : " + buffer.toString());

            //convert the byte to hex format method 2
            StringBuffer hexString = new StringBuffer();
            for (int i=0;i<mdbytes.length;i++) {
                hexString.append(Integer.toHexString(0xFF & mdbytes[i]));
            }

            System.out.println("Hex format : " + hexString.toString());
    }

}
