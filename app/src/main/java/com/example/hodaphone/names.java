package com.example.hodaphone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class names extends AppCompatActivity {
    SearchView searchView ;
    ArrayAdapter arrayAdapterv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_names);
        searchView = findViewById(R.id.searchView);
        DbHelper dbHelper = new DbHelper(this);
        ListView listView =findViewById(R.id.list_view);
        String names [] = (String[]) dbHelper.getallname();
        List<String> list = Arrays.asList(names);
        arrayAdapterv = new ArrayAdapter(this, android.R.layout.simple_list_item_1,list){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view =  super.getView(position, convertView, parent);
                view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                view.setBackgroundColor(getResources().getColor(R.color.sky));
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(20);


                return view;
            }
        };
        listView.setAdapter(arrayAdapterv);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(  getApplicationContext() , FirstActivity.class );
                intent.putExtra("name" , parent.getItemAtPosition(position).toString() ) ;
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                names.this.arrayAdapterv.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                names.this.arrayAdapterv.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext() , choice.class ) ;
        startActivity(intent);
    }


}