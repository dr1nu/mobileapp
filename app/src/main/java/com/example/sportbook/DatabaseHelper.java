package com.example.sportbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "database";
    static int version = 1;

    public static final String TABLE_USER = "user";
    //user table columns
    public static final String user_1 = "ID";
    public static final String user_2 = "USERNAME";
    public static final String user_3 = "PASSWORD";
    public static final String user_4 = "LOCALITY";
    public static final String user_5 = "SPORT";


    public static final String TABLE_VENUE = "venue";
    //venue table columns
    public static final String ven_1 = "ID";
    public static final String ven_2 = "NAME";
    public static final String ven_3 = "LOCALITY";
    public static final String ven_4 = "SPORT";
    public static final String ven_5= "image";
    private ByteArrayOutputStream byteArrayOutputStream;

    //creating tables

    String createUserTable = "CREATE TABLE if not exists \"user\" (\n" +
            "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +

            "\t\"username\"\tTEXT,\n" +
            "\t\"password\"\tTEXT,\n" +
            "\t\"locality\"\tTEXT,\n" +
            "\t\"sport\"\tTEXT\n" +
            ")";

    String createVenueTable = "CREATE TABLE \"venue\" (\n" +
            "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"name\"\tTEXT,\n" +
            "\t\"sport\"\tTEXT,\n" +
            "\t\"locality\"\tTEXT,\n" +
            "\t\"image\"\tBLOB\n" +
            ")";


    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
        SQLiteDatabase db = this.getWritableDatabase();
    }
// inserting user into database
    public void insertUser(ContentValues contentValues){
        getWritableDatabase().insert("user","",contentValues);

    }

    // inserting venue into database
    public boolean insertVenue(String vn,String sp, String lc,Bitmap image){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap tempImg = image;
        //converting bitmap to bytearray so that it can be stored in db
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        tempImg.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] venueImgInByte = byteArrayOutputStream.toByteArray();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ven_2,vn);
        contentValues.put(ven_3,sp);
        contentValues.put(ven_4,lc);
        contentValues.put(ven_5,venueImgInByte);

        long result = db.insert(TABLE_VENUE, null, contentValues);

        if(result == -1){
            return false;
        }
        else {
            return true;
        }

    }
// checking credentials method
    public boolean isLoginValid(String username, String password){
        String sql= "Select count(*) from user where username='" + username +"' and password= '" + password +"'";
        SQLiteStatement statement= getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        statement.close();

        if (l==1){
            return true;
        }
        else{
            return false;
        }


    }
    public Cursor getVenuebyLoc(String lc){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from " + TABLE_VENUE + " where locality = " + lc, null);
        return cursor;
    }



    @Override
    //executing sql statements to create tables.
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createUserTable);
        db.execSQL(createVenueTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}









