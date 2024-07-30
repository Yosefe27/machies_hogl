package com.retrotech.machies_hogl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.retrotech.machies_hogl.adapters.DefaultDashboardAdapter;
import com.retrotech.machies_hogl.models.DefaultDashboardModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BookWriterFinesDashboard extends AppCompatActivity {
    Toolbar toolbar;
    ArrayList<DefaultDashboardModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    DefaultDashboardAdapter defaultDashboardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_writer_fines_dashboard);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Fines");
        toolbar.setSubtitle("Fines Options");
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton btn_profile = findViewById(R.id.btn_profile);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String str_user_role = preferences.getString("user_role", "");
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

        Bundle bundle = getIntent().getExtras();

        recyclerView = findViewById(R.id.mainRecycler);
        models = (ArrayList<DefaultDashboardModel>) getData();
        defaultDashboardAdapter = new DefaultDashboardAdapter(models,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(BookWriterFinesDashboard.this,2);
        defaultDashboardAdapter.setClickListener(v -> {
            int position = recyclerView.getChildLayoutPosition(v);
            DefaultDashboardModel defaultDashboardModel = models.get(position);
            String card = defaultDashboardModel.getCardNumber();

            switch (card) {
                case "View Fines":
                    finish();
                    Intent viewFines = new Intent(getApplicationContext(), ViewTotalFineBookWriterActivity.class);
                    startActivity(viewFines);
                    break;
                case "Charge Fine":
                    finish();
                    Intent groupSavings = new Intent(getApplicationContext(), ChargeFineActivity.class);
                    startActivity(groupSavings);
                    break;
                case "Post Fine":
                    finish();
                    Intent postFine = new Intent(getApplicationContext(), PostFineActivity.class);
                    startActivity(postFine);
                    break;
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
//            Intent intent = new Intent(getApplicationContext(), NewPaymentActivity.class);
//            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private List<DefaultDashboardModel> getData(){
        ArrayList<DefaultDashboardModel> mainModel = new ArrayList<>();
        mainModel.add(new DefaultDashboardModel("View Fines",R.drawable.ic_view_groups,"View Fines"));
        mainModel.add(new DefaultDashboardModel("Charge Fine",R.drawable.ic_money,"Charge Fine"));
        mainModel.add(new DefaultDashboardModel("Post Fine",R.drawable.ic_add_pay,"Post Fine"));
        return mainModel;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
