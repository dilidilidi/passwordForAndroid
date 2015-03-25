package com.sunbin.password.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sunbin.password.db.PasswordSQLiteOpenHelper;
import com.sunbin.password.entity.Password;

import java.util.ArrayList;
import java.util.List;

public class PasswordDao {

    private PasswordSQLiteOpenHelper mOpenHelper;

    public PasswordDao(Context context) {
        mOpenHelper = new PasswordSQLiteOpenHelper(context);
    }

    public void insert(Password password) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("insert into password(password, comment) values(?, ?);", new Object[]{password.getPassword(), password.getComment()});
            db.close();
        }
    }

    public void delete(int id) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("delete from password where id = ?;", new Integer[]{id});
            db.close();
        }
    }

    public void update(int id, String password, String comment) {
        SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("update password set password = ?, comment = ? where id = ?;", new Object[]{password, comment, id});
            db.close();
        }
    }

    public List<Password> queryAll() {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        if (db.isOpen()) {

            Cursor cursor = db.rawQuery("select id, password, comment from password;", null);

            if (cursor != null && cursor.getCount() > 0) {
                List<Password> personList = new ArrayList<>();
                int id;
                String password;
                String comment;
                while (cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    password = cursor.getString(1);
                    comment = cursor.getString(2);
                    personList.add(new Password(id, password, comment));
                }

                db.close();
                return personList;
            }
            db.close();
        }
        return null;
    }

    public Password queryItem(int id) {
        SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select id, password, comment from password where id = ?;", new String[]{id + ""});
            if (cursor != null && cursor.moveToFirst()) {
                id = cursor.getInt(0);
                String password = cursor.getString(1);
                String comment = cursor.getString(2);
                db.close();
                return new Password(id, password, comment);
            }
            db.close();
        }
        return null;
    }
}