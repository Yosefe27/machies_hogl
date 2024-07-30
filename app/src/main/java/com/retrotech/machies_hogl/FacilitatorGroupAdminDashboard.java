package com.retrotech.machies_hogl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.retrotech.machies_hogl.adapters.GroupAdminAdapter;
import com.retrotech.machies_hogl.models.GroupAdminModel;


import java.util.ArrayList;
import java.util.List;

public class FacilitatorGroupAdminDashboard extends AppCompatActivity {
    ArrayList<GroupAdminModel> models = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_admin);
        recyclerView = findViewById(R.id.mainAdminRecycler);
        models = (ArrayList<GroupAdminModel>) getData();
        GroupAdminAdapter groupAdminAdapter = new GroupAdminAdapter(models, FacilitatorGroupAdminDashboard.this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(FacilitatorGroupAdminDashboard.this,2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(groupAdminAdapter);

    }
    public List<GroupAdminModel> getData(){
        ArrayList<GroupAdminModel> models = new ArrayList<>();
        models.add(new GroupAdminModel("Create Group",R.drawable.ic_create_group,R.color.container_color,"create group"));
        models.add(new GroupAdminModel("Add Members",R.drawable.ic_add_members,R.color.container_color,"add member"));
        models.add(new GroupAdminModel("View Groups",R.drawable.ic_view_groups,R.color.container_color,"view groups"));
        models.add(new GroupAdminModel("View Members",R.drawable.ic_members,R.color.container_color,"view members"));
        return models;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putString(Constants.GROUP_NAME, group_name);
//        bundle.putString(Constants.GROUP_ID,group_id);
//        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}