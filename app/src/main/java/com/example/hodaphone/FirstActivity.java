package com.example.hodaphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FirstActivity extends AppCompatActivity {

    Button button1 , button2 , button3 , button4 ;
    Intent intent ;
    String name ;
    TextView textView_name ;

    DbHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        button1 = findViewById(R.id.note_btn);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        dbHelper = new DbHelper(this);
        intent = getIntent() ;
        name = intent.getStringExtra("name");
        textView_name = findViewById(R.id.textview_name) ;
        textView_name.setText(name);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplication(),listview.class);
                intent.putExtra("name", name ) ;
                startActivity(intent);
            }
        });

        // delete
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteuserdata(name);
                intent = new Intent(getApplicationContext() , choice.class);
                startActivity(intent);
            }
        });

        // monthly payment

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),Monthly_Payment_Acivity.class);
                intent.putExtra("name" , name ) ;
                startActivity(intent);
            }
        });
        // show details
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),ShowActivity.class);
                intent.putExtra("name" , name );
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(getApplicationContext() , names.class ) ;
        startActivity(intent);
    }
}