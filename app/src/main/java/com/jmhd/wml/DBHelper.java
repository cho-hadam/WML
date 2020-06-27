package com.jmhd.wml;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //새로운 테이블 생성
        //테이블 명 : DIARY
        // 날짜 - date - TEXT  / 제목 - title - TEXT / 내용 - content - TEXT / 사진 - picture - BLOB
        db.execSQL("CREATE TABLE DIARY (date TEXT, title TEXT, content TEXT, picture BLOB);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(String date, String title, String content, byte[] picture){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO DIARY VALUES(" + date + ", " + title + ", " + content + ", " + picture);
        db.close();
    }


    public void update(String date, String title, String content){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE DIARY SET title = " + title + ", content = " + content + " WHERE date = " + date + ";");
        db.close();
    }
    public void delete(String date){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM DIARY WHERE date = " +date +";");
        db.close();
    }

}
