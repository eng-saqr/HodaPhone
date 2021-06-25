package com.example.hodaphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    EditText editText ;
    DbHelper dbHelper ;
    String name ;
    Intent intent ;
    int recent_money ;
    TextView editText1 , editText2 , editText3 , editText4 , editText5 , editText6 , editText7 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        editText1 = findViewById(R.id.name) ;
        editText2 = findViewById(R.id.phone_type) ;
        editText3 = findViewById(R.id.money) ;
        editText4 = findViewById(R.id.edittext4) ;
        editText5 = findViewById(R.id.edittext5) ;
        editText6 = findViewById(R.id.edittext6) ;
        editText7 = findViewById(R.id.edittext7) ;


        dbHelper = new DbHelper(this);

        intent = getIntent() ;
        name = intent.getStringExtra("name" ) ;
        String [] data = new String[8];
        data = (String[]) dbHelper.getuserdata(name);
        editText1.setText(data[0]);
        editText2.setText(data[1]);
        editText3.setText(data[2]);
        editText4.setText(data[3]);
        editText5.setText(data[4]);
        editText6.setText(data[5]);
        editText7.setText(data[7]);



    }
}