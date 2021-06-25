package com.example.hodaphone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper  extends SQLiteOpenHelper {


    public DbHelper(@Nullable Context context) {
        super(context, "clients.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table client (id  integer primary key autoincrement ,name text , tel_type text , f_money integer , monthly integer , price integer , date text , installment_value text , recent_money integer , installmentdate text)");
        db.execSQL("create table note (id integer primary key autoincrement , name text , operation_type text ,cost integer ,  date text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists client ");
        db.execSQL("drop table if exists note ");
    }

    public boolean insertuserdata(String name , String tel_type , int f_money , int monthly , int price , String date , String installment_value , String installmentdate) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int recent_money = price - f_money ;
        contentValues.put("name", name);
        contentValues.put("tel_type", tel_type);
        contentValues.put("f_money", f_money);
        contentValues.put("monthly", monthly);
        contentValues.put("price", price);
        contentValues.put("date", date);
        contentValues.put("installment_value", installment_value);
        contentValues.put("recent_money", recent_money);
        contentValues.put("installmentdate", installmentdate);

        long result = sqLiteDatabase.insert("client", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
        public boolean  deleteuserdata( String name ){
            SQLiteDatabase sqLiteDatabase1= this.getWritableDatabase();
            name = name + "%";
            long result = sqLiteDatabase1.delete("client","name like ? " ,new String[]{ name } );
            if(result == -1)
                return false;
            else
                return true;
        }

        public CharSequence[] getuserdata(String name){
            String [] array = new String[9];
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from client where name like ? ",new String[]{name});
            cursor.moveToFirst();
            array[0] = cursor.getString(1);
            array[1] = cursor.getString(2);
            array[2] = cursor.getString(3);
            array[3] = cursor.getString(4);
            array[4] = cursor.getString(5);
            array[5] = cursor.getString(6);
            array[6] = cursor.getString(7);
            array[7] = cursor.getString(8);

            return array ;


        }

        public CharSequence[] getallname(){


            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select name from client ",null);
            cursor.moveToFirst();
           int length =  cursor.getCount();
            String [] names = new String[length];
            int count =0 ;
            while (cursor.isAfterLast() == false){
                names[count] = cursor.getString(0);
                count = count+1 ;
                cursor.moveToNext();

            }
            return  names;
        }

        public String updateuserinstallment(String name, String installment_value ){

            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            ContentValues contentValues = new ContentValues();
            Cursor cursor = sqLiteDatabase.rawQuery("select installment_value from client where name = ?", new String[]{name});
            cursor.moveToFirst();
            String value ;
            int recent_money ;
            value = cursor.getString(0);
            value = value + "#" + installment_value ;

            contentValues.put( "installment_value" , value );

            sqLiteDatabase.update("client",contentValues,"name = ?", new String[]{name});

            cursor = sqLiteDatabase.rawQuery("select recent_money from client where name = ?", new String[]{name});
            cursor.moveToFirst();
            recent_money = cursor.getInt(0) - Integer.parseInt(installment_value);
            contentValues.put( "recent_money" , recent_money );
            sqLiteDatabase.update("client",contentValues,"name = ?", new String[]{name});

            return value ;

        }
         public String updateuserdate(String name , String date){

                    SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
                    ContentValues contentValues = new ContentValues();
                    Cursor cursor = sqLiteDatabase.rawQuery("select installmentdate from client where name = ?", new String[]{name});
                    cursor.moveToFirst();
                    String date_value ;
                    date_value = cursor.getString(0);
                    date_value = date_value + "#" + date ;

                    contentValues.put( "installmentdate" , date_value );

                    sqLiteDatabase.update("client",contentValues,"name = ?", new String[]{name});

                    return  date_value ;

                }


        public int retuenrecentmoney(String name){
            int recent_money;
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select recent_money from client where name = ? ", new String[]{name});
            cursor.moveToFirst();
            recent_money = Integer.parseInt(cursor.getString(0));
            return recent_money;

        }

        public String getinstallment(String name){
            String installment_value ;
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select installment_value from client where name = ? " , new String[]{name});
            cursor.moveToFirst();
            installment_value = cursor.getString(0) ;
            return installment_value ;

        }

    public String getainstallmentdate(String name){
        String installment_date ;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select installmentdate from client where name = ? " , new String[]{name});
        cursor.moveToFirst();
        installment_date = cursor.getString(0) ;
        return installment_date ;

    }

    public boolean insertnotes( String name  ,  String operation_type  , int cost ,String date ){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put( "name" , name );
        contentValues.put( "operation_type" , operation_type );
        contentValues.put( "cost" , cost );
        contentValues.put( "date" , date );
        sqLiteDatabase.insert("note" , null , contentValues );


        return true ;
    }

    public String[] names(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select name from note",null);
        cursor.moveToFirst();
        String arr [] = new String[cursor.getCount()] ;
        int count = 0 ;
        while (cursor.isAfterLast() != true ){
            arr[count] = cursor.getString(0);
            cursor.moveToNext();
            count ++ ;
        }

        return arr ;
    }

    public String[] getbeggarsdata(String name){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from note where name = ? " , new String[]{name});
        cursor.moveToFirst();
        String beggarsdata [] = new String[3] ;
        beggarsdata[2] = cursor.getString(4) ;
        beggarsdata[1]= cursor.getString(3) ;
        beggarsdata[0] = cursor.getString(2) ;

        return beggarsdata ;
    }

    public void delete_beggars_data(String name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("note" ," name = ? " , new String[]{name} );
    }





}
