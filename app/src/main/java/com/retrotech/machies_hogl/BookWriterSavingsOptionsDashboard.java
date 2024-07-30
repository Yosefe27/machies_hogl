package com.retrotech.machies_hogl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.retrotech.machies_hogl.adapters.DefaultDashboardAdapter;
import com.retrotech.machies_hogl.models.DefaultDashboardModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BookWriterSavingsOptionsDashboard extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout my_savings_option,group_savings_option,add_savings,edit_savings;
    String group_name;
    String group_id;
    ArrayList<DefaultDashboardModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    DefaultDashboardAdapter defaultDashboardAdapter;

    String str_a, str_name, str_user_role;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookwriter_savings_options);
       FloatingActionButton btn_profile = findViewById(R.id.btn_profile);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Savings");
        toolbar.setSubtitle("Savings Options");
        Bundle bundle = getIntent().getExtras();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        str_user_role = preferences.getString("user_role", "");

        try {
            group_name = bundle.getString(Constants.GROUP_NAME,"Default");
            group_id = bundle.getString(Constants.GROUP_ID,"Default");
        }catch (Exception e){

            Log.e("Error","Attempt to invoke virtual method 'java.lang.String android.os.Bundle.getString(java.lang.String, java.lang.String)' on a null object reference ");
        }

        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btn_profile.setOnClickListener(V ->{
            if(str_user_role.equals("Facilitator")){
                Intent intent = new Intent(getApplicationContext(), FacilitatorProfileActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Intent intent = new Intent(getApplicationContext(), MemberProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        recyclerView = findViewById(R.id.mainRecycler);
        models = (ArrayList<DefaultDashboardModel>) getData();
        defaultDashboardAdapter = new DefaultDashboardAdapter(models,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(BookWriterSavingsOptionsDashboard.this,2);
        defaultDashboardAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildLayoutPosition(v);
                DefaultDashboardModel defaultDashboardModel = models.get(position);
                String card = defaultDashboardModel.getCardNumber();

                switch (card) {
                    case "My Savings":
                Intent mySavings = new Intent(getApplicationContext(), MyTransactionsHistoryActivity.class);
                startActivity(mySavings );
                finish();
                        break;
                    case "Group Savings":
                        finish();
               Intent groupSavings = new Intent(getApplicationContext(), MyGroupTransactionsHistoryActivity.class);
               startActivity(groupSavings);
                        break;
                    case "Add Savings":
                        finish();
                Intent addSavings = new Intent(getApplicationContext(), NewPaymentActivity.class);
                startActivity(addSavings);
                        break;


                }

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(defaultDashboardAdapter);
    }

    private List<DefaultDashboardModel> getData(){
        ArrayList<DefaultDashboardModel> mainModel = new ArrayList<>();
            mainModel.add(new DefaultDashboardModel("My Savings",R.drawable.ic_saving,"My Savings"));
            mainModel.add(new DefaultDashboardModel("Group Savings",R.drawable.ic_group_saving,"Group Savings"));
            mainModel.add(new DefaultDashboardModel("Add Savings",R.drawable.ic_money,"Add Savings"));
      //      mainModel.add(new DefaultDashboardModel("Edit Savings",R.drawable.ic_add_pay,"Edit Savings"));
        return mainModel;
    }
    @Override
    public void onBackPressed() {


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.GROUP_NAME, group_name);
        bundle.putString(Constants.GROUP_ID,group_id);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}
