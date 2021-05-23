package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project.contacts.Contact_Item;
import com.example.project.model.Users;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    //public static Contact_Item contact_item;

    public static Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String myJson = "";
        try {
            myJson = inputStreamToString(getBaseContext().getAssets().open("data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Users users = new Gson().fromJson(myJson, Users.class);

        //contact_item = new Contact_Item(this, users.users);


        Button contacts = findViewById(R.id.contactButton);
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openContacts = new Intent(getBaseContext(),Contacts.class);
                //openContacts.putExtra("adapter",contact_item);
                //openContacts.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(openContacts);
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