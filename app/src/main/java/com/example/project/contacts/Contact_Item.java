package com.example.project.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.project.R;

import java.util.ArrayList;

public class Contact_Item extends ArrayAdapter<String> {


    public Contact_Item(@NonNull Context context, ArrayList<String> elements) {
        super(context, elements.size(), elements);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_element, parent, false);
        }
        return convertView;
    }

}
