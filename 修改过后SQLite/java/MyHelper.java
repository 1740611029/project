package com.example.helloworld.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * Sqlite数据库创建
 * SqliteOpenHelper----->帮助类
 * OnCreate()------->创建方法
 * OnUpgrade()------->数据库升级方法
 * OnOpen（）------->打开数据库方法，不一定要重写
 */

/**
 * SQLiteOpenHelper
 * 1.提供了OnCreate（）、OnUpgrade（）等创建数据库更新数据库方法
 * 2.提供了获取数据库对象的函数
 */
public class MyHelper extends SQLiteOpenHelper {
    //AUTOINCREMENT：自增
    private static final String SQL = "CREATE TABLE student (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name1 TEXT," + "phone TEXT,"+"num TEXT,"+"name2 TEXT)";

    /**
     *构造函数
     * @param context 上下文对象
     * @param name 表示创建数据库的名称
     *             还有个工厂游标，这里不需要，把参数删了，然后super（）里的factory位置填null
     * @param version 表示创建数据库版本，要>=1
     */
    public MyHelper(@Nullable Context context, @Nullable String name,int version) {
        super(context, name, null, version);
    }


    /**
     *当数据库创建时回调的函数
     * @param db 数据库对象
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);//创建数据库表的语句SQL，然后执行SQL语句
    }


    /**
     * 当数据库版本更新时回调的函数
     * @param db 数据库对象
     * @param i 数据库旧版本
     * @param i1 数据库新版本
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

}
