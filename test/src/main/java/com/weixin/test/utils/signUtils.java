package com.weixin.test.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class signUtils {

   private static String  token="rocky";

    public static boolean ischeckSign(String signature,String timestamp,String nonce){

        String[] arr={token,nonce,timestamp};
        Arrays.sort(arr);
        StringBuilder content=new StringBuilder();
        for (int i=0;i<arr.length;i++){
            content.append(arr[i]);
        }

        MessageDigest md=null;
        String tmpStr=null;

        try {
            md=MessageDigest.getInstance("SHA-1");
            byte[] digest=md.digest(content.toString().getBytes());
            tmpStr=byteToString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return tmpStr!=null?tmpStr.equals(signature):false;

    }

    private static String byteToString(byte[] digest) throws UnsupportedEncodingException {
        return new String(digest);


    }


}
