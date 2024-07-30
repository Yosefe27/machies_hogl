package com.retrotech.machies_hogl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.retrotech.machies_hogl.adapters.DefaultDashboardAdapter;
import com.retrotech.machies_hogl.models.DefaultDashboardModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BookWriterSocialDashboard extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout my_savings_option,group_savings_option,add_savings,edit_savings;
    String group_name;
    String group_id;
    ArrayList<DefaultDashboardModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    DefaultDashboardAdapter defaultDashboardAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_writer_social_dashboard);
        
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
        recyclerView = findViewById(R.id.mainRecycler);
        models = (ArrayList<DefaultDashboardModel>) getData();
        defaultDashboardAdapter = new DefaultDashboardAdapter(models,getBaseContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(BookWriterSocialDashboard.this,2);
        defaultDashboardAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildLayoutPosition(v);
                DefaultDashboardModel defaultDashboardModel = models.get(position);
                String card = defaultDashboardModel.getCardNumber();

                switch (card) {
                    case "View Social Funds":
                        finish();
                        Intent my_social_funds = new Intent(getApplicationContext(), ViewSocialFundsForMembersActivity.class);
                        startActivity(my_social_funds);
                        break;
                    case "Add Social Funds":
                        finish();
                        Intent addSavings = new Intent(getApplicationContext(), NewSocialFundActivity.class);
                        startActivity(addSavings);
                        break;
                    case "Edit Social Funds":
                        finish();
                        Intent manageSavings = new Intent(getApplicationContext(), TotalSocialFundsCollectedActivity.class);
                        startActivity(manageSavings);
                        break;
                    case "Perform Disbursement":
                        finish();
                        Intent disbursement = new Intent(getApplicationContext(), SocialFundDisbursementActivity.class);
                        startActivity(disbursement );
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
        mainModel.add(new DefaultDashboardModel("Add Social Funds",R.drawable.ic_money,"Add Social Funds"));
        mainModel.add(new DefaultDashboardModel("View Social Funds",R.drawable.ic_view_groups,"View Social Funds"));
        mainModel.add(new DefaultDashboardModel("Add Disbursement",R.drawable.ic_disbursement,"Perform Disbursement"));
        mainModel.add(new DefaultDashboardModel("Social Totals",R.drawable.ic__summarize,"Edit Social Funds"));
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
