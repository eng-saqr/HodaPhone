package com.example.hodaphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class A extends ArrayAdapter<InstallmentandDate> {

    Context context ;
    int resource ;
    ArrayList<InstallmentandDate> arrayList ;
    LayoutInflater layoutInflater ;

    public A( Context context, int resource,  ArrayList<InstallmentandDate> arrayList) {
        super(context, resource, arrayList);
        this.context = context ;
        this.resource = resource ;
        this.arrayList = arrayList ;
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = layoutInflater.inflate(resource,null);
        InstallmentandDate installmentandDate = arrayList.get(position);
        if(installmentandDate != null ){
            TextView textView1 = convertView.findViewById(R.id.maha1);
            TextView textView2 = convertView.findViewById(R.id.maha2);
            textView1.setText(installmentandDate.getInstallment());
            textView2.setText(installmentandDate.getDate());
        }
        return convertView ;
        
    }
}
