package com.example.hodaphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText1 , editText2 , editText3 ,editText4 , editText5  ;
    Button button1 ;
    String name , tel_type , currentDate , total_price , installment_value , first_mon , monthly_mon ;
    Intent intent ;
    int f_mon , monthly , price ;
    DbHelper dbHelper ;
    Helper helper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.name);
        editText2 = findViewById(R.id.type);
        editText3 = findViewById(R.id.money);
        editText4 = findViewById(R.id.edittext4);
        editText5 = findViewById(R.id.edittext5);

        helper = new Helper(this);
        dbHelper = new DbHelper(getApplicationContext());

        //Date
        currentDate = helper.getCurrentDate();
        button1 = findViewById(R.id.note_btn);
        button1.setOnClickListener(v -> {

            installment_value = getString(R.string.money);
            String installment_date = getString(R.string.date);
            name = editText1.getText().toString();
            tel_type = editText2.getText().toString();
            first_mon = editText3.getText().toString();
            monthly_mon = editText4.getText().toString();
            total_price = editText5.getText().toString();

            if (name.trim().length() == 0 )
                editText1.setError("مش هينفع تسيب الاسم فاضي يا حوده");
            else if (tel_type.trim().length() == 0 )
                editText2.setError("مش هينفع تسيب نوع التلييفون فاضي يا حوده");
            else if (first_mon.trim().length() == 0 )
                editText3.setError("مش هينفع يا حوده تسيب المقدمة فاضي يا زفت");
            else if ( monthly_mon.trim().length() == 0 )
                editText4.setError("مش هينفع تسيب ده فاضي يا زفت ");
            else if ( total_price.trim().length() == 0 )
                editText5.setError("مش هينفع تسيب سعر التليفون فاضي ");
            else {
                f_mon = Integer.parseInt(first_mon);
                monthly = Integer.parseInt(monthly_mon);
                price = Integer.parseInt(total_price);
                dbHelper.insertuserdata(name, tel_type, f_mon, monthly, price, currentDate ,installment_value , installment_date);
                editText5.setText("");
                editText4.setText("");
                editText3.setText("");
                editText2.setText("");
                editText1.setText("");
                Toast.makeText(getApplicationContext(), "تم اضافة البيانات", Toast.LENGTH_LONG).show();
                intent = new Intent(getApplicationContext() , choice.class ) ;
                startActivity(intent);
            }

        });


    }

}