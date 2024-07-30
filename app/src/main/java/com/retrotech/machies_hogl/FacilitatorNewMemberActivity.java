package com.retrotech.machies_hogl;

import static com.retrotech.machies_hogl.Constants.BASE_URL;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FacilitatorNewMemberActivity extends AppCompatActivity {
    Toolbar toolbar;
    RequestQueue mRequestQueue;
    TextView save_member_details,groupName,admissionDate;
    EditText firstName,lastName,userName,passWord,group_id,ecap_hh_ID,phoneNumber,userRole,singleFSW;
    Spinner spinner_singleFSW,spinner_gender,spinner_userRole;
    String submit_member_url=BASE_URL+"submit_member.php";

    RadioButton male, female,option_yes,option_no,book_writer,ordinary_member;
    String selectedSuperStar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilitator_add_new_member);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        userName = findViewById(R.id.user_name);
        passWord = findViewById(R.id.user_password);
        groupName = findViewById(R.id.group_name);
        group_id = findViewById(R.id.group_id);
        admissionDate = findViewById(R.id.admission_date);
        spinner_gender = findViewById(R.id.gender);
        ecap_hh_ID = findViewById(R.id.ecap_hh_id);
        phoneNumber = findViewById(R.id.phone_number);
        spinner_userRole = findViewById(R.id.user_role);
        spinner_singleFSW = findViewById(R.id.single_fsw);
        save_member_details = findViewById(R.id.save_member_details);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        option_yes = (RadioButton) findViewById(R.id.option_yes);
        option_no = (RadioButton) findViewById(R.id.option_no);
        book_writer = (RadioButton) findViewById(R.id.book_writer);
        ordinary_member = (RadioButton) findViewById(R.id.ordinary_member);

        Bundle bundle = getIntent().getExtras();
        String str_group_name = bundle.getString(Constants.GROUP_NAME, "Default");
        String str_group_id = bundle.getString(Constants.GROUP_ID, "Default");
        groupName.setText(str_group_name);
        group_id.setText(str_group_id);
        groupName.setEnabled(false);
        group_id.setEnabled(false);
        groupName.setTextColor(Color.BLACK);
        group_id.setTextColor(Color.BLACK);

        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        admissionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(FacilitatorNewMemberActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = year+"/"+month+"/"+dayOfMonth;
                        admissionDate.setText(date);
                    }
                },year, month,day);
                dialog.show();
            }
        });
        save_member_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_group_name = groupName.getText().toString();
                String str_firstName = firstName.getText().toString();
                String str_lastName = lastName.getText().toString();
                String str_userName = userName.getText().toString();
                String str_passWord = passWord.getText().toString();
                String str_admissionDate = admissionDate.getText().toString();

                String str_gender = null;
                if (male.isChecked()) {
                    str_gender = male.getText().toString();
                } else if (female.isChecked()) {
                    str_gender = female.getText().toString();
                }

                String str_ecap_hh_ID = ecap_hh_ID.getText().toString();
                String str_phoneNumber = phoneNumber.getText().toString();

                String str_userRole = null;
                if (book_writer.isChecked()) {
                    str_userRole = book_writer.getText().toString();
                } else if (ordinary_member.isChecked()) {
                    str_userRole = ordinary_member.getText().toString();
                }

                String str_singleFSW = null;
                if (option_yes.isChecked()) {
                    str_singleFSW  = option_yes.getText().toString();
                } else if (option_no.isChecked()) {
                    str_singleFSW  = option_no.getText().toString();
                }

                if(userName.getText().toString().isEmpty()) {
                    errorDialog("Username Cannot Be Empty");
                }
                else startSubmission(
                        str_group_name,
                        str_firstName,
                        str_lastName,
                        str_userName,
                        str_passWord,
                        str_admissionDate,
                        str_gender,
                        str_ecap_hh_ID,
                        str_phoneNumber,
                        str_userRole,
                        str_singleFSW
                );
                returnToFacilitatorAdminGroups();
            }
        });

    }

    private void startSubmission(final String groupname,
                                 final String firstname,
                                 final String surname,
                                 final String username,
                                 final String password,
                                 final String admissiondate,
                                 final String gender,
                                 final String ecaphh_id,
                                 final String phonenumber,
                                 final String userrole,
                                 final String singlefsw
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
     //   reportsAlert.show();
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, submit_member_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("member_url", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").equals("success")) {

                        String received_msg = object.getString("msg");

//                        reportsAlert.dismiss();
//                        errorDialog(object.getString("msg"));
                    } else if (object.getString("status").equals("failed")) {
//                        reportsAlert.dismiss();
//                        errorDialog(object.getString("msg"));
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
        Intent intent = new Intent(getApplicationContext(), FacilitatorGroupAdminDashboard.class);
        startActivity(intent);

    }
    public void returnToFacilitatorAdminGroups(){
        Intent intent = new Intent(getApplicationContext(), FacilitatorViewGroupsActivity.class);
        startActivity(intent);
        finish();
    }
}