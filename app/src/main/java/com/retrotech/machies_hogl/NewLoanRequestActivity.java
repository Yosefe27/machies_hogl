package com.retrotech.machies_hogl;

import static com.retrotech.machies_hogl.Constants.BASE_URL;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.retrotech.machies_hogl.adapters.NewLoanRequestAdapter;
import com.retrotech.machies_hogl.models.NewLoanRequestModel;
import com.retrotech.machies_hogl.utils.Connectivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NewLoanRequestActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText amount,member_id,current_balance,loan_balance,interest_rate;
    TextView select_member,post_loan_request,loan_date;
    String submit_saving_url=BASE_URL+"submit_loan.php";
    String str_a, members_list = BASE_URL + "loan_member_request_dialog.php";
    NewLoanRequestAdapter membersAdapter;
    RequestQueue mRequestQueue;
    ArrayList<NewLoanRequestModel> listMembers = new ArrayList<NewLoanRequestModel>();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_loan_request);

        amount = findViewById(R.id.amount);
        loan_date = findViewById(R.id.date_picker_actions);
        select_member = findViewById(R.id.select_member);
        member_id = findViewById(R.id.member_id);
        member_id.setEnabled(false);
        current_balance = findViewById(R.id.current_balance);
        interest_rate = findViewById(R.id.interest_rate);
        current_balance.setEnabled(false);
        interest_rate.setEnabled(false);
        loan_balance = findViewById(R.id.current_loan);
        loan_balance.setEnabled(false);
        post_loan_request = findViewById(R.id.post_loan_request);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Loan Request Details");
        toolbar.setSubtitle("Loan Request");
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), BookWriterLoanRequestDashboard.class);
                startActivity(intent);
            }
        });

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        loan_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(NewLoanRequestActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = year+"/"+month+"/"+dayOfMonth;
                        loan_date.setText(date);
                    }
                },year, month,day);
                dialog.show();
            }
        });

        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        context = NewLoanRequestActivity.this;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        str_a = preferences.getString("a", "");
        if (isNetworkAvailable()) {
            membersList();
        } else {
            errorDialog("Please Check Your Internet Connection");

        }
        select_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                membersDisplayDialog();
            }
        });
        post_loan_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_amount = amount.getText().toString();
                String str_select_member = select_member.getText().toString();
                String str_contribution_date = loan_date.getText().toString();
                String str_interest_rate = interest_rate.getText().toString();
                String str_member_id = member_id.getText().toString();
                String str_group = preferences.getString("group_id", "");
                if(amount.getText().toString().isEmpty()){
                    errorDialog("Amount should not be empty.");
                }
                else if(select_member.getText().toString().isEmpty()){
                    errorDialog("Member Name should not be empty.");
                }
                else{
                    startSubmission(str_amount,
                            str_select_member,
                            str_member_id,
                            str_contribution_date,
                            str_interest_rate,
                            str_group);
                }
            }
        });
    }
    private void startSubmission(final String amount,String select_member,String member_id,String contribution_date,String interest_rate,String group_id){
        //        signin_progress.setVisibility(View.VISIBLE);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String str_a = preferences.getString("a", "");
        ViewGroup viewGroup = findViewById(android.R.id.content);

        final View dialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog, viewGroup, false);


        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);
        //finally creating the alert dialog and displaying it
        final AlertDialog reportsAlert = builder.create();
        // Let's start with animation work. We just need to create a style and use it here as follow.
        if (reportsAlert.getWindow() != null)
            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;
        reportsAlert.setCancelable(true);
        reportsAlert.setCanceledOnTouchOutside(true);
        reportsAlert.show();
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, submit_saving_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                Log.v("savings_url", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").equals("success")) {// same as if (object.getBoolean("success") == true) {

                        String received_msg = object.getString("msg");

                        reportsAlert.dismiss();
                        errorDialog(object.getString("msg"));
                    } else if (object.getString("status").equals("failed")) {
                        reportsAlert.dismiss();
//                        signin_progress.setVisibility(View.GONE);
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

            //amount
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("amount", amount);
                parms.put("contributor_id", member_id);
                parms.put("full_name", select_member);
                parms.put("loan_date",contribution_date);
                parms.put("interest_rate",interest_rate);
                parms.put("group_id", group_id);
                parms.put("a", str_a);
                return parms;
            }
        };
        stringRequest.setShouldCache(false);
        mRequestQueue.add(stringRequest);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    public void membersList() {

        ViewGroup viewGroup = findViewById(android.R.id.content);

        final View dialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog, viewGroup, false);


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
        StringRequest request = new StringRequest(Request.Method.POST, members_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(),"Response : "+response,Toast.LENGTH_SHORT).show();
//                textView.setText(response.toString());
                Log.v("transactions_response", response);
                try {
                    JSONObject object = new JSONObject(response);
//                    str_my_name = object.getString("full_name");
//                    str_user_role = object.getString("user_role");
//                    str_group_name = object.getString("group_name");
                    JSONArray array = object.getJSONArray("members_list");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject stackObject = array.getJSONObject(i);
//                        JSONObject stackObject2 = array2.getJSONObject(i);

                        // textView.setText(object1.toString());
                        NewLoanRequestModel members = new NewLoanRequestModel(
                                stackObject.getString("full_name"),
                                stackObject.getString("id"),
                                stackObject.getString("gender"),
                                stackObject.getString("admission_date"),
                                stackObject.getString("group_name"),
                                stackObject.getString("total_contribution"),
                                stackObject.getString("social_fund"),
                                stackObject.getString("fines_due"),
                                stackObject.getString("loan_balance"),
                                stackObject.getString("interest_rate")

                        );
                        listMembers.add(members);
                    }


                    reportsAlert.dismiss();



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), " No Internet Connection ", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "authentication error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ServerError) {
                    Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "authentication error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "parse error", Toast.LENGTH_SHORT).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("a", str_a);
//                parms.put("a", access_token);
                return parms;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        request.setShouldCache(false);
        requestQueue.add(request);
    }

    public void membersDisplayDialog() {
        RecyclerView members_recyclerview;
        ViewGroup viewGroup = findViewById(android.R.id.content);

        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_payment_options, viewGroup, false);

        members_recyclerview = dialogView.findViewById(R.id.recyclerview_payment_options);
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

        members_recyclerview.setHasFixedSize(true);
        members_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        members_recyclerview.addItemDecoration(new DividerItemDecoration(getBaseContext(), DividerItemDecoration.HORIZONTAL));
        membersAdapter = new NewLoanRequestAdapter(getBaseContext(), listMembers);
        members_recyclerview.setAdapter(membersAdapter);
        membersAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = members_recyclerview.getChildLayoutPosition(view);
                NewLoanRequestModel members = listMembers.get(position);
                select_member.setText(String.valueOf(members.getMember_name()));
                member_id.setText(String.valueOf(members.getMember_id()));
                current_balance.setText(String.valueOf(members.getTotal_savings()));
                loan_balance.setText(String.valueOf(members.getLoan_due()));
                interest_rate.setText(String.valueOf(members.getInterest_rate()));

                reportsAlert.dismiss();
            }


        });
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
        Intent intent = new Intent(getApplicationContext(), BookWriterLoanRequestDashboard.class);
        startActivity(intent);
    }

}