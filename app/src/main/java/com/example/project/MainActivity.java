package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toolbar;

import com.example.project.model.Users;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    //public static Contact_Item contact_item;

    public static Bundle bundle = new Bundle();

    final TrendsFragment trendsFragment = new TrendsFragment();
    final FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment active = trendsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScrollView scrollView = findViewById(R.id.scroll_view);

        String myJson = "";
        try {
            myJson = inputStreamToString(getBaseContext().getAssets().open("data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Users users = new Gson().fromJson(myJson, Users.class);

        //contact_item = new Contact_Item(this, users.users);


        Button contacts = findViewById(R.id.contactButton);
        //BottomNavigationView bottomnav = reportFullyDrawn() =findViewById(R.id.)
        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openContacts = new Intent(getBaseContext(), Contacts.class);
                //openContacts.putExtra("adapter",contact_item);
                //openContacts.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(openContacts);
            }
        });

        fragmentManager.beginTransaction().add(R.id.fragment_container,trendsFragment, "2").hide(trendsFragment).commit();




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                LinearLayout toolbarHome = findViewById(R.id.toolbar1);
                LinearLayout toolbarTrends = findViewById(R.id.toolbar2);
                switch (item.getItemId()){
                    case R.id.home:
                        fragmentManager.beginTransaction().hide(active).commit();
                        toolbarHome.setVisibility(View.VISIBLE);
                        toolbarTrends.setVisibility(View.GONE);
                        contacts.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.VISIBLE);
                        return true;
                    case R.id.trends:
                        fragmentManager.beginTransaction().show(trendsFragment).commit();
                        active = trendsFragment;
                        toolbarHome.setVisibility(View.GONE);
                        toolbarTrends.setVisibility(View.VISIBLE);
                        contacts.setVisibility(View.GONE);
                        scrollView.setVisibility(View.GONE);
                        return true;
                }
                return true;
            }
        });

        Button today = findViewById(R.id.today);
        //today.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.MULTIPLY );
        Button week = findViewById(R.id.week);
        Button month = findViewById(R.id.month);
       // today.setBackgroundResource(R.drawable.button_background_white);
       // today.setBackground(R.drawable.button_background_white);

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today.setBackgroundResource(R.drawable.button_background_white);
                today.setTextColor(getResources().getColor(R.color.main_color));
                week.setBackgroundResource(R.drawable.button_background);
                week.setTextColor(getResources().getColor(R.color.white_transparent));
                month.setBackgroundResource(R.drawable.button_background);
                month.setTextColor(getResources().getColor(R.color.white_transparent));
                //today.setBackgroundResource(R.drawable.button_background_white);
               // today.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP );
                //today.setBackground(getDrawable(R.drawable.button_background_white));
                //week.getBackground().setColorFilter(getResources().getColor(R.color.main_color), PorterDuff.Mode.SRC_ATOP );
               // month.getBackground().setColorFilter(getResources().getColor(R.color.main_color), PorterDuff.Mode.SRC_ATOP );
            }
        });

        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today.setBackgroundResource(R.drawable.button_background);
                today.setTextColor(getResources().getColor(R.color.white_transparent));
                week.setBackgroundResource(R.drawable.button_background_white);
                week.setTextColor(getResources().getColor(R.color.main_color));
                month.setBackgroundResource(R.drawable.button_background);
                month.setTextColor(getResources().getColor(R.color.white_transparent));
            }
        });

        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                today.setBackgroundResource(R.drawable.button_background);
                today.setTextColor(getResources().getColor(R.color.white_transparent));
                week.setBackgroundResource(R.drawable.button_background);
                week.setTextColor(getResources().getColor(R.color.white_transparent));
                month.setBackgroundResource(R.drawable.button_background_white);
                month.setTextColor(getResources().getColor(R.color.main_color));
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

    public void foo() {}
}