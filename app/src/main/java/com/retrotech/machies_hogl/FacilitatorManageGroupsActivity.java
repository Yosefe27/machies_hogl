package com.retrotech.machies_hogl;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class FacilitatorManageGroupsActivity extends AppCompatActivity{
    Toolbar toolbar;
    LinearLayout facilitator_manage_groups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        setContentView(R.layout.activity_facilitator_manage_groups);
        facilitator_manage_groups = findViewById(R.id.linear_facilitator_add_group);
    }
}
