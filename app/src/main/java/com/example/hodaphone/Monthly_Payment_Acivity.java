package com.example.hodaphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Monthly_Payment_Acivity extends AppCompatActivity {

    EditText value  ;
    Button button1 ;
    DbHelper dbHelper ;
    Helper helper ;
    String name , amount ;
    String currentDate ;
    int installment_value;
    Intent intent ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_payment_acivity);

        value = findViewById(R.id.amount);
        button1 = findViewById(R.id.note_btn);
        dbHelper = new DbHelper(this);
        helper = new Helper(this);
        intent = getIntent();
        name = intent.getStringExtra("name");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amount = value.getText().toString() ;
                if ( amount.trim().length() == 0 ) {
                    value.setError("القسط فارغ");
                }else {
                    installment_value = Integer.parseInt(amount);
                    currentDate = helper.getCurrentDate();
                    dbHelper.updateuserinstallment(name, String.valueOf(installment_value));
                    dbHelper.updateuserdate(name, currentDate);
                    intent = new Intent(getApplicationContext(), FirstActivity.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                }

            }
        });


    }
}