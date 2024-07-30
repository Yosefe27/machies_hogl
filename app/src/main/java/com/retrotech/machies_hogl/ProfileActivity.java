package com.retrotech.machies_hogl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView user_name,group_name,group_id,user_role, user_id;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        user_name = findViewById(R.id.profile_name);
        group_name = findViewById(R.id.profile_group_name);
        group_id = findViewById(R.id.profile_group_id);
        user_role = findViewById(R.id.profile_user_role);
        user_id = findViewById(R.id.profile_user_id);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
         String str_user_role = preferences.getString("user_role", "");
        String str_user_name = preferences.getString("name", "");
        String str_group_name = preferences.getString("group_name", "");
        String str_group_id = preferences.getString("group_id", "");
        String str_user_id = preferences.getString("id", "");



        user_name.setText(str_user_name);
        group_name.setText(str_group_name);
        group_id.setText(str_group_id);
        user_id.setText(str_user_id);

        if (str_user_role.equals("Ordinary Member")){
            user_role.setText("Ordinary Member");
        } else if(str_user_role.equals("Book Writer")){
            user_role.setText("Book Writer");
        }else {
            user_role.setText("Facilitator");
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}