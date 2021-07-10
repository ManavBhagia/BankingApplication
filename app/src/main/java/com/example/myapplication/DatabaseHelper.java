package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9908908001,'Manav',8700.00,'manav@gmail.com','XXXXXXXXXXXX3100','ABC09976543')");
        db.execSQL("insert into user_table values(2555567890,'Pratham',7902.86,'pratham@gmail.com','XXXXXXXXXXXX2100','MCA94765432')");
        db.execSQL("insert into user_table values(9834567890,'Soumya',2409.57,'soumya@gmail.com','XXXXXXXXXXXX3401','CAB87654621')");
        db.execSQL("insert into user_table values(8762933090,'Bhoomi',1800.01,'bhoomi@gmail.com','XXXXXXXXXXXX4523','ABC76542210')");
        db.execSQL("insert into user_table values(7800298098,'Anushka',5601.95,'anushka@gmail.com','XXXXXXXXXXXX1543','BCA65412109')");
        db.execSQL("insert into user_table values(8763874638,'Atharva',845.18,'atharva@gmail.com','XXXXXXXXXXXX3452','AMD54325098')");
        db.execSQL("insert into user_table values(7898890900,'Dikshit',7936.00,'db@gmail.com','XXXXXXXXXXXX5875','ABC44210987')");
        db.execSQL("insert into user_table values(8901234567,'Devam',997.92,'devam@gmail.com','XXXXXXXXXXXX6522','BCA32109876')");
        db.execSQL("insert into user_table values(9289298389,'Saloni',6298.46,'saloni@gmail.com','XXXXXXXXXXXX5556','CAB21098765')");
        db.execSQL("insert into user_table values(1268768787,'Jai',583.90,'jaiveeru@gmail.com','XXXXXXXXXXXX7890','DCC10987654')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
