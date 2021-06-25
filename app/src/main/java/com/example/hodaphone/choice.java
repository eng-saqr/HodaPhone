package com.example.hodaphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class choice extends AppCompatActivity {

    Button insert_btn , show , addbeggars , showbegars ;
    Intent intent ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        insert_btn = findViewById(R.id.insert) ;
        show = findViewById(R.id.show) ;
        addbeggars = findViewById(R.id.addbeggars);
        showbegars = findViewById(R.id.showbeggars);
        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 intent = new Intent(getApplicationContext() , MainActivity.class ) ;
                 startActivity(intent) ;

            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext() , names.class ) ;
                startActivity(intent);
            }
        });

        addbeggars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), Notes.class ) ;
                startActivity(intent);

            }
        });

        showbegars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),Beggars.class);
                startActivity(intent);
            }
        });
    }
}