package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.project.contacts.Contact_Item;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class Contacts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ListView listView = findViewById(R.id.list);
        ArrayList list = new ArrayList( Arrays.asList( new String[]{"abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss"} ) );

        Contact_Item contact_item = new Contact_Item(this, list);

        listView.setAdapter(contact_item);

        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}