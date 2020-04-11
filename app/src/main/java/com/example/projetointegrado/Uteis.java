package com.example.projetointegrado;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Uteis {

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){
        }
        return null;
    }

    public static int converteStringToInt(String campo){
        try{
            return Integer.parseInt(campo);
        }catch (Exception e){
            return 0;
        }
    }

    public static String converteDataHora(Date date){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy - hh:mm a");
        try {
            return  df.format(date);
        }catch (Exception e){
            return "";
        }
    }



}
