package com.example.hodaphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Notes extends AppCompatActivity {

    EditText editText1 , editText2 , editText3 ;
    String name , reason  , price ;
    int money ;
    Intent intent ;
    String currentdate ;
    Helper helper ;
    Button button ;
    DbHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        editText1 = findViewById(R.id.name);
        editText3 = findViewById(R.id.money);
        editText2 = findViewById(R.id.type);
        button = findViewById(R.id.note_btn);
        dbHelper = new DbHelper(this);
        helper = new Helper(this );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText1.getText().toString();
                reason = editText2.getText().toString();
                currentdate = helper.getCurrentDate() ;
                price = editText3.getText().toString();

                if( name.trim().length() == 0 ){
                    editText1.setError("الاسم فارغ");
                }else if( reason.trim().length() == 0 ){
                    editText1.setError("السبب فارغ");
                }else if( price.trim().length() == 0  ){
                    editText1.setError("السعر فارغ");
                }else {
                    money = Integer.parseInt(price);
                    dbHelper.insertnotes(name, reason, money, currentdate);
                    intent = new Intent(getApplicationContext(), Beggars.class);
                    startActivity(intent);

                }
            }
        });

    }
}