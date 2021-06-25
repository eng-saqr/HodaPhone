package com.example.hodaphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class listview extends AppCompatActivity {


    InstallmentandDate installmentandDate ;
    ArrayList<InstallmentandDate> arrayList ;
    ListView listView ;
    Intent intent ;
    String name ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        DbHelper dbHelper = new DbHelper(this);
        arrayList = new ArrayList<>();
        listView = findViewById(R.id.listview) ;
        intent = getIntent() ;
        name = intent.getStringExtra("name") ;
        String arr1[] = dbHelper.getinstallment(name).split("#");
        String arr2[] = dbHelper.getainstallmentdate(name).split("#");
        for(int i = 0 ; i < arr1.length ; i ++ ){
            installmentandDate = new InstallmentandDate(arr1[i],arr2[i]);
            arrayList.add(installmentandDate);
        }

        A a = new A( this , R.layout.layouthelper , arrayList);
        listView.setAdapter(a);

    }
}