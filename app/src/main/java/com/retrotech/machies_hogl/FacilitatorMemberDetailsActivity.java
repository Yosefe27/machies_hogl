package com.retrotech.machies_hogl;

import static com.retrotech.machies_hogl.Constants.BASE_URL;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.retrotech.machies_hogl.utils.Connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FacilitatorMemberDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Toolbar toolbar;
    RequestQueue mRequestQueue;
    TextView save_member_details, groupName, groupID,txt_user_role;
    EditText firstName, lastName, userName, passWord, admissionDate, user_ID, ecap_hh_ID, phoneNumber, userRole, singleFSW;
    Spinner spinner_singleFSW, spinner_gender, spinner_user_role;
    String submit_member_url = BASE_URL + "update_member.php";

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_details);

        setSupportActionBar(toolbar);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        userName = findViewById(R.id.user_name);
        passWord = findViewById(R.id.user_password);
        groupName = findViewById(R.id.group_name);
        groupID = findViewById(R.id.group_id);
        admissionDate = findViewById(R.id.admission_date);
        spinner_gender = findViewById(R.id.gender);
        ecap_hh_ID = findViewById(R.id.ecap_hh_id);
        phoneNumber = findViewById(R.id.phone_number);
        txt_user_role = findViewById(R.id.txt_user_role);
        spinner_user_role = findViewById(R.id.user_role);
        spinner_singleFSW = findViewById(R.id.single_fsw);
        save_member_details = findViewById(R.id.save_member_details);
        user_ID = findViewById(R.id.user_id);

        String intent_group_name = getIntent().getStringExtra(Constants.GROUP_NAME);
        String intent_group_id = getIntent().getStringExtra(Constants.GROUP_ID);
        String intent_first_name = getIntent().getStringExtra(Constants.USER_FIRST_NAME);
        String intent_last_name = getIntent().getStringExtra(Constants.USER_LAST_NAME);
        String intent_user_name = getIntent().getStringExtra(Constants.USER_NAME);
        String intent_password = getIntent().getStringExtra(Constants.USER_PASSWORD);
        String intent_admission_date = getIntent().getStringExtra(Constants.USER_ADMISSION_DATE);
        String intent_gender = getIntent().getStringExtra(Constants.USER_GENDER);
        String intent_ecap_id = getIntent().getStringExtra(Constants.ECAP_ID);
        String intent_phone = getIntent().getStringExtra(Constants.USER_PHONE);
        String intent_user_role = getIntent().getStringExtra(Constants.USER_ROLE);


        String intent_caregiver_status = getIntent().getStringExtra(Constants.CAREGIVER_STATUS);
        String intent_user_ID = getIntent().getStringExtra(Constants.USER_ID);

        groupName.setText(intent_group_name);
        groupID.setText(intent_group_id);
        firstName.setText(intent_first_name);
        lastName.setText(intent_last_name);
        userName.setText(intent_user_name);
        passWord.setText(intent_password);
        admissionDate.setText(intent_admission_date);
        ecap_hh_ID.setText(intent_ecap_id);
        phoneNumber.setText(intent_phone);
        userName.setText(intent_user_name);
        user_ID.setText(intent_user_ID);
        user_ID.setEnabled(false);


        String[] spinnerGender = {intent_gender, "Male", "Female"};
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerGender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(adapter);
        spinner_gender.setOnItemSelectedListener(this);
        String selection = intent_gender;
        int spinnerPosition = adapter.getPosition(selection);
        spinner_gender.setSelection(spinnerPosition);

        String[] spinnerRole = {intent_user_role,"Ordinary Member"};
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerRole);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_user_role.setAdapter(adapter2);
        spinner_user_role.setOnItemSelectedListener(this);
        String selection2;
        selection2 = intent_user_role;
        int spinnerPosition2 = adapter2.getPosition(selection2);
        spinner_user_role.setSelection(spinnerPosition2);
        txt_user_role.setVisibility(View.GONE);
        spinner_user_role.setVisibility(View.GONE);

        String[] spinnerCaregiver = {intent_caregiver_status, "Yes", "No"};
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerCaregiver);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_singleFSW.setAdapter(adapter3);
        spinner_singleFSW.setOnItemSelectedListener(this);
        String selection3 = intent_caregiver_status;
        int spinnerPosition3 = adapter3.getPosition(selection3);
        spinner_singleFSW.setSelection(spinnerPosition3);

        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        save_member_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_group_name = groupName.getText().toString();
                String str_group_id = groupID.getText().toString();
                String str_firstName = firstName.getText().toString();
                String str_lastName = lastName.getText().toString();
                String str_userName = userName.getText().toString();
                String str_passWord = passWord.getText().toString();
                String str_admissionDate = admissionDate.getText().toString();
                String str_gender = spinner_gender.getSelectedItem().toString();
                String str_ecap_hh_ID = ecap_hh_ID.getText().toString();
                String str_phoneNumber = phoneNumber.getText().toString();
                String str_userRole = spinner_user_role.getSelectedItem().toString();
                String str_singleFSW = spinner_singleFSW.getSelectedItem().toString();
                String str_user_id = user_ID.getText().toString();

                if(userName.getText().toString().isEmpty()) {
                    errorDialog("Username Cannot Be Empty");
                }
                else startSubmission(
                        str_group_name,
                        str_group_id,
                        str_firstName,
                        str_lastName,
                        str_userName,
                        str_passWord,
                        str_admissionDate,
                        str_gender,
                        str_ecap_hh_ID,
                        str_phoneNumber,
                        str_userRole,
                        str_singleFSW,
                        str_user_id
                );
            }
        });

    }
    private void startSubmission(final String groupname,
                                 final String groupid,
                                 final String firstname,
                                 final String surname,
                                 final String username,
                                 final String password,
                                 final String admissiondate,
                                 final String gender,
                                 final String ecaphh_id,
                                 final String phonenumber,
                                 final String userrole,
                                 final String singlefsw,
                                 final String id
    )
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String str_a = preferences.getString("a", "");
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog reportsAlert = builder.create();
        if (reportsAlert.getWindow() != null)
            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        reportsAlert.setCancelable(true);
        reportsAlert.setCanceledOnTouchOutside(true);
        reportsAlert.show();
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, submit_member_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("member_url", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").equals("success")) {

                        String received_msg = object.getString("msg");

                        reportsAlert.dismiss();
                        errorDialog(object.getString("msg"));
                        finish();
                        Intent intent = new Intent(getApplicationContext(), BookWriterAdminDashboard.class);
                        startActivity(intent);
                    } else if (object.getString("status").equals("failed")) {
                        reportsAlert.dismiss();
                        errorDialog(object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("group_name", groupname);
                parms.put("group_id",groupid);
                parms.put("firstname", firstname);
                parms.put("lastname", surname);
                parms.put("nrc", username);
                parms.put("password", password);
                parms.put("admission_date", admissiondate);
                parms.put("ecap_hh_id", ecaphh_id);
                parms.put("phone_number", phonenumber);
                parms.put("gender", gender);
                parms.put("user_role", userrole);
                parms.put("single_female_caregiver", singlefsw);
                parms.put("a", str_a);
                parms.put("id",id);
                return parms;
            }
        };
        stringRequest.setShouldCache(false);
        mRequestQueue.add(stringRequest);
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
        Intent intent = new Intent(getApplicationContext(), ViewActualMembersFacilitator.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}