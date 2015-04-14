package com.sunbin.password.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sunbin.password.database.PasswordSQLiteOpenHelper;
import com.sunbin.password.entity.Password;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dilidilidi on 2015/3/22.
 * Email:93146139@qq.com
 * 密码记录DAO层
 */

public class PasswordDao {

    private PasswordSQLiteOpenHelper mOpenHelper;

    public PasswordDao(Context context) {
        mOpenHelper = new PasswordSQLiteOpenHelper(context);
    }

    public void insert(Password password) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("insert into p(cipherText, salt, iv, remark) values(?, ?, ?, ?);", new Object[]{password.getCipherText(), password.getSalt(), password.getIv(), password.getRemark()});
            db.close();
        }
    }

    public void delete(int id) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("delete from p where id = ?;", new Integer[]{id});
            db.close();
        }
    }

    public void update(Password p) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("update p set cipherText = ?, salt = ?, iv = ?, remark = ? where id = ?;", new Object[]{p.getCipherText(), p.getSalt(), p.getIv(), p.getRemark(), p.getId()});
            db.close();
        }
    }

    public List<Password> queryAll() {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from p;", null);
            if (cursor != null && cursor.getCount() > 0) {
                List<Password> passwordList = new ArrayList<>();
                int id;
                String cipherText;
                String salt;
                String iv;
                String remark;
                while (cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    cipherText = cursor.getString(1);
                    salt = cursor.getString(2);
                    iv = cursor.getString(3);
                    remark = cursor.getString(4);
                    passwordList.add(new Password(id, cipherText, salt, iv, remark));
                }
                db.close();
                return passwordList;
            }
            db.close();
        }
        return null;
    }

    public Password queryItem(int id) {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from p where id = ?;", new String[]{id + ""});
            if (cursor != null && cursor.moveToFirst()) {
                id = cursor.getInt(0);
                String cipherText = cursor.getString(1);
                String salt = cursor.getString(2);
                String iv = cursor.getString(3);
                String remark = cursor.getString(4);
                db.close();
                return new Password(id, cipherText, salt, iv, remark);
            }
            db.close();
        }
        return null;
    }

    public Password queryLastItem() {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from p order by id desc limit 0 , 1;", null);
            if (cursor != null && cursor.moveToFirst()) {
                int id = cursor.getInt(0);
                String cipherText = cursor.getString(1);
                String salt = cursor.getString(2);
                String iv = cursor.getString(3);
                String remark = cursor.getString(4);
                db.close();
                return new Password(id, cipherText, salt, iv, remark);
            }
            db.close();
        }
        return null;
    }
}
