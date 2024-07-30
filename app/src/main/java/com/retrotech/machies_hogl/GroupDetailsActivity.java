package com.retrotech.machies_hogl;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class GroupDetailsActivity extends AppCompatActivity {

    TextView group_name_txt,group_id_txt,group_interest_rate_txt,group_date_created_txt;
    TextView cycle_number_txt,first_training_meeting_date_txt,date_savings_started_txt,reinvested_savings_cycle_start_txt;
    TextView registered_members_cycle_start_txt,group_management_spinner_txt;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        toolbar = findViewById(R.id.toolbar);
        group_id_txt = findViewById(R.id.group_id);
        group_name_txt = findViewById(R.id.group_name);
        group_interest_rate_txt = findViewById(R.id.interest_rate);
        group_date_created_txt = findViewById(R.id.date_created);
        cycle_number_txt = findViewById(R.id.cycle_number);
        first_training_meeting_date_txt = findViewById(R.id.first_training_meeting_date);
        date_savings_started_txt = findViewById(R.id.date_savings_started);
        reinvested_savings_cycle_start_txt = findViewById(R.id.reinvested_savings_cycle_start);
        registered_members_cycle_start_txt = findViewById(R.id.registered_members_cycle_start);
        group_management_spinner_txt = findViewById(R.id.group_management_spinner);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
                Intent intent = new Intent(getApplicationContext(), FacilitatorGroupsActivity.class);
                startActivity(intent);
            }
        });

        String intent_group_id = getIntent().getStringExtra("intent_group_id");
        String intent_group_name = getIntent().getStringExtra("intent_group_name");
        String intent_group_date_created = getIntent().getStringExtra("intent_group_date_created");
        String intent_group_annual_interest_rate = getIntent().getStringExtra("intent_group_annual_interest_rate");
        String intent_cycle_number = getIntent().getStringExtra("intent_cycle_number");
        String intent_first_training_meeting_date = getIntent().getStringExtra("intent_first_training_meeting_date");
        String intent_date_savings_started = getIntent().getStringExtra("intent_date_savings_started");
        String intent_reinvested_savings_cycle_start = getIntent().getStringExtra("intent_reinvested_savings_cycle_start");
        String intent_registered_members_cycle_start = getIntent().getStringExtra("registered_members_cycle_start");
        String intent_group_management_spinner = getIntent().getStringExtra("group_management_spinner");


        group_id_txt.setText(intent_group_id);
        group_name_txt.setText(intent_group_name);
        group_date_created_txt.setText(intent_group_date_created);
        group_interest_rate_txt.setText(intent_group_annual_interest_rate);
        cycle_number_txt.setText(intent_cycle_number);
        first_training_meeting_date_txt.setText(intent_first_training_meeting_date);
        date_savings_started_txt.setText(intent_date_savings_started);
        reinvested_savings_cycle_start_txt.setText(intent_reinvested_savings_cycle_start);
        registered_members_cycle_start_txt.setText(intent_registered_members_cycle_start);
        group_management_spinner_txt.setText(intent_group_management_spinner);
        toolbar.setSubtitle(intent_group_name+" GROUP DETAILS");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public void errorDialog(String error_text) {

        ViewGroup viewGroup = findViewById(android.R.id.content);
        final TextView main_text;
        final Button btn_ok;
        final LinearLayout linear_buttons;
        final CardView card_ok;
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_error, viewGroup, false);


        btn_ok = dialogView.findViewById(R.id.btn_ok);
//        card_ok = dialogView.findViewById(R.id.card_ok);
        main_text = dialogView.findViewById(R.id.main_text);
        linear_buttons = dialogView.findViewById(R.id.linear_buttons);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        final AlertDialog reportsAlert = builder.create();
        // Let's start with animation work. We just need to create a style and use it here as follow.
        if (reportsAlert.getWindow() != null)
            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

        reportsAlert.show();
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        btn_ok.setVisibility(View.VISIBLE);
        linear_buttons.setVisibility(View.GONE);
//        To prevent dialog box from getting dismissed on back key pressed use this
        reportsAlert.setCancelable(false);

//        And to prevent dialog box from getting dismissed on outside touch use this
        reportsAlert.setCanceledOnTouchOutside(false);
        main_text.setText(error_text);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportsAlert.dismiss();
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_stuff, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), FacilitatorNewMemberActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(getApplicationContext(),FacilitatorGroupsActivity.class);
        startActivity(intent);
    }
}