package com.example.projetointegrado;

import android.content.Context;

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

    public static String converteData(Date date){
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return  df.format(date);
        }catch (Exception e){
            return "";
        }
    }


    public static String converteHora(Date date){
        SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
        try {
            return  df.format(date);
        }catch (Exception e){
            return "";
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

    public static void enviarEmail(String email, String assunto, String mensagem, Context context){

        //Creating SendMail object
        Mail sm = new Mail(context, email, assunto, mensagem);

        //Executing sendmail to send email
        sm.execute();

    }

    public static String gerarChave(){
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssSSS");
        return ft.format(dNow);
    }



}
