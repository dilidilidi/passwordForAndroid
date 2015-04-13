package com.sunbin.password;

import android.test.AndroidTestCase;
import android.util.Base64;
import android.util.Log;

import com.sunbin.password.aes256.Aes256;
import com.sunbin.password.dao.PasswordDao;
import com.sunbin.password.db.PasswordSQLiteOpenHelper;
import com.sunbin.password.entity.Password;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by dilidilidi on 2015/3/22.
 * Email:93146139@qq.com
 */
public class TestCase extends AndroidTestCase {
    private static final String TAG = "TestCase";

    public void test() {
        PasswordSQLiteOpenHelper openHelper = new PasswordSQLiteOpenHelper(getContext());
        openHelper.getReadableDatabase();
    }

    public void testInsert() {
        PasswordDao dao = new PasswordDao(getContext());
        dao.insert(new Password(0, "wangwu1", "aaa", "wangwucommit1"));
    }

    public void testUpdate() {
        PasswordDao dao = new PasswordDao(getContext());
        dao.update(1, "fengjie", "aaa", "fengjiecomment");
    }

    public void testQueryAll() {
        PasswordDao dao = new PasswordDao(getContext());
        List<Password> passwords = dao.queryAll();
        for (Password password : passwords) {
            Log.i(TAG, password.toString());
        }
    }

    public void testQueryItem() {
        PasswordDao dao = new PasswordDao(getContext());
        Password password = dao.queryItem(1);
        Log.i(TAG, password.toString());
    }

    public void testDelete() {
        PasswordDao dao = new PasswordDao(getContext());
        Log.i(TAG, "testDelete is ok");
        //dao.delete(9);
    }

    public void testEncrypt() throws Exception {
        try {
            String data = "Test String";
            //key
            String key = "1234567812345678";
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(key.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(md.digest(), "AES");
            //cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //IV
            SecureRandom random = new SecureRandom();
            byte[] iv = new byte[16];
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            //INIT
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
            Log.i(TAG, Base64.encodeToString(iv, Base64.DEFAULT));
            Log.i(TAG, Base64.encodeToString(encrypted, Base64.DEFAULT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testDecrypt() throws Exception {
        try {
            //key
            String key = "1234567812345678";
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(key.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(md.digest(), "AES");
            //cipherText
            byte[] cipherText = Base64.decode("CEnLlHSscXNKFD0TarkYvQ==", Base64.DEFAULT);
            //iv
            byte[] iv = Base64.decode("lhYPGO8glUrTR/XuDA0Kiw==", Base64.DEFAULT);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            //cipher
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //init
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            //plainText
            String plainText = new String(cipher.doFinal(cipherText), "UTF-8");
            Log.i(TAG, plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
