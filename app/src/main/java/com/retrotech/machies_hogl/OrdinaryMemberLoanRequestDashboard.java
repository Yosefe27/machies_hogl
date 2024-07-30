package com.retrotech.machies_hogl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.retrotech.machies_hogl.adapters.DefaultDashboardAdapter;
import com.retrotech.machies_hogl.models.DefaultDashboardModel;

import java.util.ArrayList;
import java.util.List;

public class OrdinaryMemberLoanRequestDashboard extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<DefaultDashboardModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    DefaultDashboardAdapter defaultDashboardAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordinary_member_loan_dashboard);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Savings");
        toolbar.setSubtitle("Savings Options");

        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.mainRecycler);
        models = (ArrayList<DefaultDashboardModel>) getData();
        defaultDashboardAdapter = new DefaultDashboardAdapter(models,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(OrdinaryMemberLoanRequestDashboard.this,2);
        defaultDashboardAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildLayoutPosition(v);
                DefaultDashboardModel defaultDashboardModel = models.get(position);
                String card = defaultDashboardModel.getCardNumber();

                switch (card) {
                    case "My Loan":
                        Intent myLoan = new Intent(getApplicationContext(), OrdinaryMemberMyLoanActivity.class);
                        startActivity(myLoan );
                        finish();
                        break;
                    case "Group Loan":
                        Intent groupSavings = new Intent(getApplicationContext(), OrdinaryMemberMyGroupLoanActivity.class);
                        startActivity(groupSavings);
                        finish();
                        break;
                    default:
                        break;
                }

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(defaultDashboardAdapter);
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
            Intent intent = new Intent(getApplicationContext(), NewPaymentActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private List<DefaultDashboardModel> getData(){
        ArrayList<DefaultDashboardModel> mainModel = new ArrayList<>();
        mainModel.add(new DefaultDashboardModel("My Loan",R.drawable.ic_saving,"My Loan"));
        mainModel.add(new DefaultDashboardModel("Group Loan",R.drawable.ic_group_saving,"Group Loan"));
             return mainModel;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
