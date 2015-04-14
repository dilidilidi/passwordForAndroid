package com.sunbin.password;

import android.test.AndroidTestCase;
import android.util.Base64;
import android.util.Log;

import com.sunbin.password.aes256.Aes256;
import com.sunbin.password.dao.PasswordDao;
import com.sunbin.password.database.PasswordSQLiteOpenHelper;
import com.sunbin.password.entity.Password;

import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by dilidilidi on 2015/3/22.
 * Email:93146139@qq.com
 */
public class TestCase extends AndroidTestCase {
    private static final String TAG = "TestCase";

    public void testEncrypt() throws Exception {
        String text = "Test String";
        String key = "1234567812345678";
        Aes256 aes256 = new Aes256();
        String[] result = aes256.encrypt(text, key);
        Log.i(TAG, "salt:" + result[0]);
        Log.i(TAG, "iv:" + result[1]);
        Log.i(TAG, "cipherText:" + result[2]);
        PasswordDao dao = new PasswordDao(getContext());
        dao.insert(new Password(0, result[2], result[0], result[1], "淘宝"));
    }

    public void testDecrypt() throws Exception {
        PasswordDao dao = new PasswordDao(getContext());
        Password p = dao.queryLastItem();
        //key
        String key = "1234567812345678";
        Aes256 aes256 = new Aes256();
        String result = aes256.decrypt(p.getCipherText(), key, p.getSalt(), p.getIv());
        Log.i(TAG, "plainText:" + result);
    }
}
