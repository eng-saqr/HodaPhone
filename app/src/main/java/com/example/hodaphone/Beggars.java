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
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class Beggars extends AppCompatActivity {
    DbHelper dbHelper ;
    ListView listView ;
    ArrayAdapter arrayAdapter ;
    String arr [];
    SearchView searchView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beggars);
        listView = findViewById(R.id.list_beggars);
        searchView = findViewById(R.id.searchView) ;
        dbHelper = new DbHelper(this);
       arr = dbHelper.names() ;
        List<String> list = Arrays.asList(arr);
        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1 , list){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                view.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextSize(20);
                view.setBackgroundColor(Color.LTGRAY);
                return view ;
            }
        };
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext() , beggars_listview.class);
                intent.putExtra("name" , parent.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Beggars.this.arrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Beggars.this.arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext() , choice.class );
        startActivity(intent);
    }


}