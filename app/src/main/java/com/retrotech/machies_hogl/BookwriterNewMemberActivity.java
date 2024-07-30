package com.retrotech.machies_hogl;

import static com.retrotech.machies_hogl.Constants.BASE_URL;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.DatePicker;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookwriterNewMemberActivity extends AppCompatActivity {

    Toolbar toolbar;
    RequestQueue mRequestQueue;
    Button save_member_details;
    TextView groupName,groupID,admissionDate;
    EditText firstName,lastName,userName,passWord,user_password2,gender,ecap_hh_ID,phoneNumber,userRole,singleFSW;
    Spinner spinner_singleFSW,spinner_gender,spinner_userRole;
    String submit_member_url=BASE_URL+"submit_member.php";
    String str_group_name;
    String str_group_id;
    DatePicker admission_date;
    RadioButton male, female,option_yes,option_no,ordinary_member;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookwriter_new_member);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        userName = findViewById(R.id.user_name);
        passWord = findViewById(R.id.user_password);
        user_password2 = findViewById(R.id.user_password2);
        groupName = findViewById(R.id.group_name);
        groupID = findViewById(R.id.group_id);
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
        ordinary_member = (RadioButton) findViewById(R.id.ordinary_member);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        str_group_name = preferences.getString("group_name", "");
        groupName.setText(str_group_name);
        groupName.setEnabled(false);
        groupName.setTextColor(Color.BLACK);
        str_group_id = preferences.getString("group_id", "");
        groupID.setText(str_group_id);
        groupID.setEnabled(false);
        groupID.setTextColor(Color.BLACK);

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        admissionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(BookwriterNewMemberActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validatePhone(phoneNumber.getText().toString())){
                    save_member_details.setEnabled(true);
                }
                else{

                    phoneNumber.setError("Invalid phone number");
                    save_member_details.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
            firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validateName(firstName.getText().toString())){
                    save_member_details.setEnabled(true);
                }
                else{

                    firstName.setError("Name cannot take in numbers or special characters");
                    save_member_details.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validateName(lastName.getText().toString())){
                    save_member_details.setEnabled(true);
                }
                else{

                    lastName.setError("Name cannot take in numbers or  special characters");
                    save_member_details.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                String str_ecap_hh_ID = ecap_hh_ID.getText().toString();
                String str_phoneNumber = phoneNumber.getText().toString();

                String str_gender = null;
                if (male.isChecked()) {
                    str_gender = male.getText().toString();
                } else if (female.isChecked()) {
                    str_gender = female.getText().toString();
                }


                String str_userRole = null;
                if (ordinary_member.isChecked()) {
                    str_userRole = ordinary_member.getText().toString();
                }

                String str_singleFSW = null;
                if (option_yes.isChecked()) {
                    str_singleFSW = option_yes.getText().toString();
                } else if (option_no.isChecked()) {
                    str_singleFSW = option_no.getText().toString();
                }



                if(TextUtils.isEmpty(str_group_name )){
                    groupName.setError("Group name is required");
                    return;
                }
                else if(TextUtils.isEmpty(str_firstName)){
                    firstName.setError("First name is required");
                    return;
                }
                else if(TextUtils.isEmpty(str_lastName)){
                    lastName.setError("Last name is required");
                    return;
                }
                else if(TextUtils.isEmpty(str_userName)){
                    userName.setError("User name is required");
                    return;
                }
                else if(TextUtils.isEmpty(str_passWord)){
                    passWord.setError("Set user password");
                    return;
                }
                else if(TextUtils.isEmpty(str_admissionDate)){
                    admissionDate.setError("Admission date is required");
                    return;
                }
                else if(TextUtils.isEmpty(str_gender)){
                    return;
                }

                else if(TextUtils.isEmpty(str_phoneNumber)){
                    phoneNumber.setError("Phone number");
                    return;
                }
                else if (!passWord.getText().toString().equals(user_password2.getText().toString())) {
                    user_password2.setError("Password Not Matching");
                    return;
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
                        str_singleFSW
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
                        Intent intent = new Intent(getApplicationContext(), MembersActivity.class);
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
        Intent intent = new Intent(getApplicationContext(), BookWriterAdminDashboard.class);
        startActivity(intent);
        finish();
    }
    boolean validatePhone(String input){
        Pattern pattern = Pattern.compile("((07||09)[5-7][0-9]{7})|s*");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    boolean validateName(String input){
        Pattern pattern = Pattern.compile("[A-Za-z\\s\\.\\-]*");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
