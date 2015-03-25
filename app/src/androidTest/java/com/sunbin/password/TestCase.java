package com.sunbin.password;

import android.test.AndroidTestCase;

import com.sunbin.password.db.PasswordSQLiteOpenHelper;

/**
 * Created by dilidilidi on 2015/3/22.
 * Email:93146139@qq.com
 */
public class TestCase extends AndroidTestCase {

    public void test(){
        PasswordSQLiteOpenHelper openHelper=new PasswordSQLiteOpenHelper(getContext());
//        openHelper.getReadableDatabase()
    }


}
