package com.mgnoid.basiclogin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by MG_PARK on 2017-07-11.
 */

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "MEMBERSHIP";
    final static String TABLE = "MEMBER";

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, id TEXT, password TEXT, name TEXT, mail TEXT, phone TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Member mem) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO "+TABLE+" VALUES(null, '" + mem.getId() + "', '" + mem.getPassword() + "', '" + mem.getName() + "', '"+mem.getMail()+"','"+mem.getPhone()+"');");
        db.close();
    }


    public void delete(String id) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
       String tempquery="delete from "+TABLE;
        db.execSQL(tempquery);

        db.close();
    }

    public ArrayList<Member> getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        Member mem=new Member();
        ArrayList<Member> memList=new ArrayList<Member>();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE, null);
        while (cursor.moveToNext()) {

            mem.setId(cursor.getString(1));
            mem.setPassword(cursor.getString(2));
            mem.setName(cursor.getString(3));
            mem.setMail(cursor.getString(4));
            mem.setPhone(cursor.getString(5));

            memList.add(mem);

        }

        return memList;
    }
    public String getResult2() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result="";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE, null);
        while (cursor.moveToNext()) {

            result+=cursor.getString(1);
            result+=cursor.getString(2);
            result+=cursor.getString(3);
            result+=cursor.getString(4);

        }

        return result;
    }


}








