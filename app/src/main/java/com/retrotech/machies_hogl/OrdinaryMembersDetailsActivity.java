package com.retrotech.machies_hogl;

import static com.retrotech.machies_hogl.Constants.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.retrotech.machies_hogl.adapters.MembersAdapter;
import com.retrotech.machies_hogl.models.Members;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrdinaryMembersDetailsActivity extends AppCompatActivity {
    RecyclerView members_recyclerview;
    RequestQueue mRequestQueue;
    ArrayList<Members> listMembers = new ArrayList<>();
    Context context;
    String str_a, members_list = BASE_URL+"list_of_group_members.php";
    MembersAdapter membersAdapter;
    TextView user_name,group_name,group_id,user_role, user_id, user_gender,user_phone,ecap_id,admission_date;
    TextView full_name_txt,nrc_txt,email_txt,address_txt,group_id_txt;
    ImageView chairperson_approval_img,treasurer_approval_img,secretary_approval_img,prof_img_big;
    CircleImageView prof_img_small;

    String intent_chairperson_approval = "0";
    String intent_treasurer_approval = "0";
    String intent_secretary_approval = "0",img_name;


    Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordinary_members_details);
        toolbar = findViewById(R.id.toolbar);
        group_id_txt = findViewById(R.id.group_id);

        user_name = findViewById(R.id.profile_name);
        group_name = findViewById(R.id.profile_group_name);
        user_gender = findViewById(R.id.user_gender);
        user_phone = findViewById(R.id.user_phone);
        group_id = findViewById(R.id.profile_group_id);
        user_role = findViewById(R.id.profile_user_role);
        user_id = findViewById(R.id.profile_user_id);
        ecap_id = findViewById(R.id.ecap_hh_id);
        admission_date = findViewById(R.id.admission_date);



//        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                finish();
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
//                Intent intent = new Intent(getApplicationContext(), OrdinaryMembersActivity.class);
//                startActivity(intent);
//            }
//        });

        String intent_group_name = getIntent().getStringExtra(Constants.GROUP_NAME);
        String intent_group_id = getIntent().getStringExtra(Constants.GROUP_ID);
        String intent_first_name = getIntent().getStringExtra(Constants.USER_FIRST_NAME);
        String intent_last_name = getIntent().getStringExtra(Constants.USER_LAST_NAME);
        String intent_admission_date = getIntent().getStringExtra(Constants.USER_ADMISSION_DATE);
        String intent_gender = getIntent().getStringExtra(Constants.USER_GENDER);
        String intent_ecap_id = getIntent().getStringExtra(Constants.ECAP_ID);
        String intent_phone = getIntent().getStringExtra(Constants.USER_PHONE);
        String intent_user_role = getIntent().getStringExtra(Constants.USER_ROLE);
        String intent_caregiver_status = getIntent().getStringExtra(Constants.CAREGIVER_STATUS);
        String intent_user_ID = getIntent().getStringExtra(Constants.USER_ID);

        user_name.setText(intent_first_name+" "+intent_last_name);
        group_name.setText(intent_group_name);
        group_id.setText(intent_group_id);
        user_id.setText(intent_user_ID);
        user_phone.setText(intent_phone);
        user_role.setText(intent_user_role);
        ecap_id.setText(intent_ecap_id);
        user_gender.setText(intent_gender);
        admission_date.setText(intent_admission_date);





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
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(getApplicationContext(),OrdinaryMembersActivity.class);

        startActivity(intent);
    }
}