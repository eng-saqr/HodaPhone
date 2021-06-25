package com.example.hodaphone;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Helper {

    boolean temp = true ;

    public Helper(Context context) {

    }

    public boolean check_temp(String arr[] , String name ){
        for(int i = 0 ; i < arr.length ; i++ ) {
            if (arr[i].equals(name)) {
                temp = false;
                break;
            }
        }


        return temp ;
    }

    public String getCurrentDate (){
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        return currentDate ;
    }
}
