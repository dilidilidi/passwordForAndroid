package com.sunbin.password.aes256;

import android.util.Base64;
import android.util.Log;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by dilidilidi on 2015/3/22.
 * Email:93146139@qq.com
 * aes256加密类
 */
public class Aes256 {

    private SecureRandom random = new SecureRandom();
    private byte[] salt = new byte[16];
    private byte[] iv = new byte[16];

    public String[] encrypt(String text, String key) {
        String[] result = new String[3];
        random.nextBytes(salt);
        result[0] = Base64.encodeToString(salt, Base64.DEFAULT);
        random.nextBytes(iv);
        result[1] = Base64.encodeToString(iv, Base64.DEFAULT);
        try {
            //cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //keySpec
            PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray(), salt, 1000, 256);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] keyBytes = skf.generateSecret(pbeKeySpec).getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            //IV
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            //INIT
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] cipherText = cipher.doFinal(text.getBytes("UTF-8"));
            result[2] = Base64.encodeToString(cipherText, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String decrypt(String cipherText, String key, String salt, String iv) {
        String plainText = "";
        try {
            //cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //keySpec
            PBEKeySpec pbeKeySpec = new PBEKeySpec(key.toCharArray(), Base64.decode(salt, Base64.DEFAULT), 1000, 256);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] keyBytes = skf.generateSecret(pbeKeySpec).getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            //ivSpec
            IvParameterSpec ivSpec = new IvParameterSpec(Base64.decode(iv, Base64.DEFAULT));
            //init
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            //plainText
            plainText = new String(cipher.doFinal(Base64.decode(cipherText, Base64.DEFAULT)), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plainText;
    }
}