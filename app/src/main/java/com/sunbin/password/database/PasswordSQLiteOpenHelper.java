package com.sunbin.password.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dilidilidi on 2015/3/22.
 * Email:93146139@qq.com
 * SQLite数据库帮助类
 */
public class PasswordSQLiteOpenHelper extends SQLiteOpenHelper {

    /**
     * 构造函数
     *
     * @param context
     */
    public PasswordSQLiteOpenHelper(Context context) {
        super(context, "sqlite.db3", null, 1);
    }

    /**
     * 数据库创建后会调用此函数，利用此函数初始化一些表
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE [p] ([id] INTEGER PRIMARY KEY,[cipherText] VARCHAR(1024) NOT NULL,[salt] VARCHAR(1024) NOT NULL,[iv] VARCHAR(1024) NOT NULL,[remark] VARCHAR(24) NOT NULL);");
    }

    /**
     * 数据库的版本号更新时调用此函数（版本为SQLiteOpenHelper抽象类中的构造函数中version参数）
     * 利用此函数进行表更新，例如表增加了字段，被删除等等
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
