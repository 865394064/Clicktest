package com.example.clicktest;

import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {
    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于保存公私密钥

    private static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC5KlF855Yq8rpM/CNQDeoS4hyVO8q" +
            "FQ6dpb+SvXC0CqU70myDzYoIqEaRtsNscFTvo0cLTHDsT0JiMkx0A/HWL3tVkcDar4POZW2a6HDK6cjUIOD" +
            "2nNf2UY5aIe6c7S25NuHLz5N9dDCSd2iI/1xMmFdhYiEKlf68XXfdSRWE3MwIDAQAB";
    private static String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALkqUXznliryukz8I1AN6hLiHJU7yoVDp2lv5K9cLQKpTvSbIPNigioRpG2w2xwVO+jRwtMcOxPQmIyTHQD8dYve1WRwNqvg85lbZrocMrpyNQg4Pa" +
            "c1/ZRjloh7pztLbk24cvPk310MJJ3aIj/XEyYV2FiIQqV/rxdd91JFYTczAgMBAAECfylF3pNp8bScVaWX0i+7KnRz+rKs" +
            "ZUcLB8w0OXmcEDvQC/M//flXWu9/widdVVc1kE1ZTdHDDLvVV5CDF7jWkdtivWvf/IQFU/7bo2MlpvC2P0A5PKDkIlG25KX" +
            "GpuOwGC4JClaVfMQDh9On1XnynJyYJ8MvhpY5/wqr75EAmk0CQQD4EXpRrYNjs4cR5GoImQzrqmoet+VmiVFqeCMaHzcL0F7" +
            "FLwXUb7Up5Kdn6Pjx6lz4ovMdoQ7l6Z2CDTBI0jwlAkEAvxX1ZVg0FZKW/BtaCGuiVHfki0dIi5lqybKr/4bUJzMvZ9ccfvYa" +
            "XIUt/dASxgE2bQ1TaN81VECLWladj8WadwJBALCxAkcbJ/Lj9NkxsLTfDuztPRkUMzmNpYgIejgo87RefqJElAp0Zr2oN/UzY9" +
            "4r6HzQ1AnOpiXs+FuhuTqlRGECQCA7pQRuZ4LIEn3+YyaOeXiELOItqRgbTf8uC4N0C+98299JUv47p2C5+nMZGUGbTMICgHJtT" +
            "IKkzJz1hWiOLasCQQD2ygyl1zrZpja5euShkP1yx2IfKhOWLm9bvqRLUAZo2JfmuRn1QVwJA7NjaW3sAMc0pwCAtWPAJBJSX9TTSyNr";
    public static void test() throws Exception {
        genKeyPair();
        //加密字符串
        String message = "df723820";
        String messageEn = encrypt(message,keyMap.get(0));
        System.out.println(message + "\t加密后的字符串为:" + messageEn);

        Log.d("hjztest","Aes="+AESEncryptor.encrypt(message));
        Log.d("hjztest","Aes="+AESEncryptor.decrypt("43BED73F6B744CB1A4B6BEB2AF75F457"));

        //解密
        String messageDe = decrypt(messageEn,keyMap.get(1));
        System.out.println("还原后的字符串为:" + messageDe);
//        decode("/4KhZxyREayCrPpETi2XUQ==", PRIVATE_KEY);


    }

    /**
     *
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        String publicKeyString = PUBLIC_KEY;
        String privateKeyString = PRIVATE_KEY;
        keyMap.put(0,publicKeyString);  //0表示公钥
        keyMap.put(1,privateKeyString);  //1表示私钥
    }

    public static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decode(publicKey,0);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeToString(cipher.doFinal(str.getBytes("UTF-8")),0);
        return outStr;
    }

    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串 [-57, 4, -118, 92, 55, -127, 62, 127, 63, -84, -68, 63, -99, -73, -77, 80]
        byte[] inputByte = Base64.decode("/4KhZxyREayCrPpETi2XUQ==".getBytes(),0);
        String s=new String(inputByte,"utf-8");

//        for(int i=0;i<inputByte.length;i++){
//            Log.d("hjztest","outStr="+inputByte[i]);
//            if(inputByte[i]<0){
//                inputByte[i]=(byte)(inputByte[i]+256);
//            }
//        }
        //base64编码的私钥
        byte[] decoded = Base64.decode(privateKey,0);
        Log.d("hjztest","outStr="+decoded.length);
//        for(int i=0;i<decoded.length;i++){
//            Log.d("hjztest","outStr="+decoded[i]);
//            if(decoded[i]<0){
//                decoded[i]=(byte)(decoded[i]+256);
//            }
//        }

        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        Log.d("hjztest","outStr="+outStr);
        return outStr;
    }


//    public static String encode(String content, String key) throws Exception {
//        byte[] raw = key.getBytes("utf-8");
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
//        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//        byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
//        return byteArrToString(encrypted);
//    }
//    public static byte[] string2ByteArr(String str) {
//        byte[] bytes;
//        bytes = new byte[str.length() / 2];
//        Log.d("hjztest","bytes "+bytes.length);
//        for (int i = 0; i < bytes.length; i++) {
//            Log.d("hjztest","bytes i="+i);
//            bytes[i] = (byte) Integer.parseInt(str.substring(2 * i, 2 * i + 2),16);
//        }
//        return bytes;
//    }

//    public static String decode(String content, String key) throws Exception {
//        Log.d("hjztest","content");
//        byte[] arr = "/4KhZxyREayCrPpETi2XUQ==".getBytes("UTF-8");
//        Log.d("hjztest","content2");
//        byte[] raw = key.getBytes("utf-8");
//        Log.d("hjztest","1");
//        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
//        byte[] original = cipher.doFinal(arr);
//        Log.d("hjztest","outStr="+new String(original,"utf-8"));
//        return new String(original,"utf-8");
//    }
//    public static String byteArrToString(byte[] bcd) {
//        StringBuffer s = new StringBuffer(bcd.length * 2);
//        for (int i = 0; i < bcd.length; i++) {
//            s.append(bcdLookup[(bcd[i] >>> 4) & 0x0f]);
//            s.append(bcdLookup[bcd[i] & 0x0f]);
//        }
//        return s.toString();
//    }
}

