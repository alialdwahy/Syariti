package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class DB_Sqlitecar extends SQLiteOpenHelper {

    private static final String sayarty = "DB_Sqlit";

    public DB_Sqlitecar(@Nullable Context context) {
        super(context , sayarty, null, 2);
    }

    public DB_Sqlitecar(@Nullable Context context,  @Nullable DatabaseErrorHandler errorHandler) {
        super(context, sayarty, null, 2, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DB_Sqlitecar(@Nullable Context context, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, sayarty, 2, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table users1 (id INTEGER PRIMARY KEY AUTOINCREMENT  ,username TEXT,email TEXT,nagionalnum INTEGER,passward INTEGER ,firstname TEXT,lastname TEXT,address TEXT,phone INTEGER,gender TEXt)");
        db.execSQL("Create table cars1 (carnum INTEGER PRIMARY KEY,namecar TEXT,typecar TEXT,modelcar INTEGER,traveleddist INTEGER,vehicleclass TEXT,accidents TEXT,license INTEGER,geartype TEXT,typereqouset TEXT,Image BLOB,prisecar INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users1");
        db.execSQL("drop table if exists cars1");
    }
    public boolean insertData(String username, String email, String nagionalnum, String passward, String firstname, String lastname, String address, String phone, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("nagionalnum", nagionalnum);
        contentValues.put("passward", passward);
        contentValues.put("firstname", firstname);
        contentValues.put("lastname", lastname);
        contentValues.put("address", address);
        contentValues.put("phone", phone);
        contentValues.put("gender", gender);
        long result = db.insert("users1", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }
    public boolean insertData1(String Typecar, String Namecar, String Model, String Traveleddistance, String Carnum, String Carclass, String Accidents, String License, String prisecar, byte[] Image, String geartype, String typereqouset) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues12 = new ContentValues();
        contentValues12.put("typecar", Typecar);
        contentValues12.put("namecar", Namecar);
        contentValues12.put("traveleddist", Traveleddistance);
        contentValues12.put("modelcar ", Model);
        contentValues12.put("carnum", Carnum);
        contentValues12.put("vehicleclass", Carclass);
        contentValues12.put("accidents", Accidents);
        contentValues12.put("license", License);
        contentValues12.put("prisecar", prisecar);
        contentValues12.put("typereqouset", typereqouset);
        contentValues12.put("geartype", geartype);
        contentValues12.put("Image", Image);
        long result = db.insert("cars1", null, contentValues12);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean updateR(String Typeca, String Nameca, String Mode, String Traveleddistanc, String Carno, String Carclas, String Accident, String Licens, String priseca, byte[] Imag, String geartyp, String typereqouse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentVales12 = new ContentValues();
        contentVales12.put("typecar", Typeca);
        contentVales12.put("namecar", Nameca);
        contentVales12.put("traveleddist", Traveleddistanc);
        contentVales12.put("modelcar ", Mode);
        contentVales12.put("vehicleclass", Carclas);
        contentVales12.put("accidents", Accident);
        contentVales12.put("license", Licens);
        contentVales12.put("carnum", Carno);
        contentVales12.put("prisecar", priseca);
        contentVales12.put("typereqouset", typereqouse);
        contentVales12.put("geartype", geartyp);
        contentVales12.put("Image", Imag);
        db.update("cars1", contentVales12, "carnum=?", new String[]{String.valueOf(Carno)});

        return true;
    }

    public Integer deleteR(String Carnu) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(" cars1 ", "carnum=?", new String[]{String.valueOf(Carnu)});

    }

    public boolean checkusernamep(String username) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from user where username =? and passward = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean checkusernamepassward(String username, String passward) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users1 where username =? and passward = ?", new String[]{username, passward});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Cursor getData(String s) {

        SQLiteDatabase DB = this.getReadableDatabase();
        return DB.rawQuery(s, null);
    }

}
