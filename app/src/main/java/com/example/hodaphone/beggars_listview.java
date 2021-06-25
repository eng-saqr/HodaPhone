package com.example.hodaphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class beggars_listview extends AppCompatActivity {

    Intent intent ;
    String name ;
    TextView beggars_name ;
    Button button ;
    TextView textView1 , textView2 , textView3 ;
    DbHelper dbHelper ;
    String data [] ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beggars_listview);
        dbHelper = new DbHelper(this);
        textView1 = findViewById(R.id.txt_reason) ;
        textView2 = findViewById(R.id.txt_cost) ;
        textView3 = findViewById(R.id.txt_date) ;
        button = findViewById( R.id.delete ) ;
        intent = getIntent() ;
        name = intent.getStringExtra("name");
        beggars_name = findViewById(R.id.beggars_name);
        beggars_name.setText(name);
        data = dbHelper.getbeggarsdata(name);
        textView1.setText(data[0]);
        textView2.setText(data[1]);
        textView3.setText(data[2]);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.delete_beggars_data( name ) ;
                intent = new Intent(getApplicationContext() , Beggars.class);
                startActivity(intent);
            }
        });


    }
}