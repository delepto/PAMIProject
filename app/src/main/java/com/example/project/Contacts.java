package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.project.contacts.Contact_Item;
import com.example.project.model.Users;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Contacts extends AppCompatActivity {

    private boolean editFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        /*
        String data = importJson();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        Log.i("in",json);
        */

        String myJson = "";
        try {
           myJson = inputStreamToString(getBaseContext().getAssets().open("data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Users users = new Gson().fromJson(myJson, Users.class);


        //Contact_Item contact_item = (Contact_Item) getIntent().getSerializableExtra("adapter");
        //Contact_Item contact_item = MainActivity.contact_item;

        //Users users = (Users) getIntent().getSerializableExtra("users");
        ListView listView = findViewById(R.id.list);
       // ArrayList list = new ArrayList( Arrays.asList( new String[]{"abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss","abc", "def","ss"} ) );

        Contact_Item contact_item = new Contact_Item(this, users.users);

        listView.setAdapter(contact_item);
        ImageButton editButton = (ImageButton) findViewById(R.id.edit);

        ExtendedFloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        TextView title = findViewById(R.id.title);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editFlag = !editFlag;
                if(editFlag){
                    contact_item.updateFlag(true);
                    contact_item.notifyDataSetChanged();
                    fab.setVisibility(View.INVISIBLE);
                    editButton.setBackground(getDrawable(R.drawable.ic_baseline_cancel_white_24));
                    title.setText("Remove Contacts");
                    title.setTextSize(25);
                }else{
                    contact_item.updateFlag(false);
                    contact_item.notifyDataSetChanged();
                    fab.setVisibility(View.VISIBLE);
                    editButton.setBackground(getDrawable(R.drawable.ic_baseline_edit_24));
                    title.setText("Contacts");
                    title.setTextSize(30);
                }
            }
        });
    }

    public String inputStreamToString(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes, 0, bytes.length);
            String json = new String(bytes);
            return json;
        } catch (IOException e) {
            return null;
        }
    }

}