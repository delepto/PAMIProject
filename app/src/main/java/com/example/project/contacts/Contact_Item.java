package com.example.project.contacts;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.project.Contacts;
import com.example.project.R;
import com.example.project.model.Users;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Queue;

import static com.example.project.R.drawable.anne_peres_plsf6obtgms_unsplash;

public class Contact_Item extends ArrayAdapter<Users.User> {

    private boolean flag;
    private ArrayList<Users.User> list;

    public Contact_Item(@NonNull Context context, ArrayList<Users.User> elements) {
        super(context, elements.size(), elements);
        list = elements;
        flag = false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(list.get(position).active) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_element, parent, false);
            }
            ImageButton delete = (ImageButton) convertView.findViewById(R.id.delete_user);
            if (this.flag) {
                delete.setVisibility(View.VISIBLE);
            } else {
                delete.setVisibility(View.INVISIBLE);
            }
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create(); //Read Update
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    final View customLayout
                            = LayoutInflater.from(getContext())
                            .inflate(
                                    R.layout.delete_dialog,
                                    null);
                    alertDialog.setView(customLayout);
                    TextView confirmation = customLayout.findViewById(R.id.confirmation);
                    String text = "Are you sure you want to delete" + " <b>" + list.get(position).name + "<b>";
                    confirmation.setText(Html.fromHtml(text));

                    Button no = customLayout.findViewById(R.id.no);
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.cancel();
                        }
                    });
                    Button yes = customLayout.findViewById(R.id.yes);
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.get(position).active = false;
                            list.remove(position);
                            alertDialog.cancel();
                            notifyDataSetInvalidated();
                        }
                    });
                    alertDialog.show();  //<-- See This!
                }
            });

            ImageView imageView = convertView.findViewById(R.id.image);
            int id = getContext().getResources().getIdentifier("drawable/" + list.get(position).image, null, getContext().getPackageName());
            imageView.setImageResource(id);
            imageView.setClipToOutline(true);

            TextView name = convertView.findViewById(R.id.name);
            name.setText(list.get(position).name);

            TextView profession = convertView.findViewById(R.id.profession);
            profession.setText(list.get(position).profession);
        }
        return convertView;
    }

    public void updateFlag(boolean flag) {
        this.flag = flag;
    }



}
