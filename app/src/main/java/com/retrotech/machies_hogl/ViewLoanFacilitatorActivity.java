package com.retrotech.machies_hogl;

import static com.retrotech.machies_hogl.Constants.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.retrotech.machies_hogl.adapters.ViewLoanFacilitatorAdapter;
import com.retrotech.machies_hogl.models.ViewGroupSavingFacilitatorModel;
import com.retrotech.machies_hogl.utils.Connectivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewLoanFacilitatorActivity extends AppCompatActivity {
    RecyclerView members_recyclerview;
    RequestQueue mRequestQueue;
    ArrayList<ViewGroupSavingFacilitatorModel> listMembers = new ArrayList<ViewGroupSavingFacilitatorModel>();
    Context context;
    String str_a, members_list = BASE_URL + "view_group_total_loans_facilitator.php";
    String str_user_role,str_my_name,str_group_name;
    ViewLoanFacilitatorAdapter viewGroupSavingFacilitatorAdapter;
    Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_loan_facilitator);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Group Saving Contribution");
        toolbar.setSubtitle("Group Saving Contribution");


        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        members_recyclerview = findViewById(R.id.members_recyclerview);

        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        context = ViewLoanFacilitatorActivity.this;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        str_a = preferences.getString("a", "");
        if (isNetworkAvailable()) {
            membersList();
        } else {
            errorDialog("Please Check Your Internet Connection");

        }



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
                Log.v("members_list", response);
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
                        ViewGroupSavingFacilitatorModel savings = new  ViewGroupSavingFacilitatorModel(
                                stackObject.getString(Constants.GROUP_NAME),
                                stackObject.getString(Constants.GROUP_ID),
                                stackObject.getString("total_loans")


                        );
                        listMembers.add(savings);
                    }

                    if (listMembers.size() <= 0) {
                        members_recyclerview.setVisibility(View.GONE);
//                        no_transactions_txt.setVisibility(View.VISIBLE);
                        reportsAlert.dismiss();
                    } else {
                        members_recyclerview.setHasFixedSize(true);
                        members_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        members_recyclerview.addItemDecoration(new DividerItemDecoration(getBaseContext(), DividerItemDecoration.HORIZONTAL));
                        viewGroupSavingFacilitatorAdapter = new ViewLoanFacilitatorAdapter(listMembers,getBaseContext());
                        members_recyclerview.setAdapter(viewGroupSavingFacilitatorAdapter);
                        viewGroupSavingFacilitatorAdapter.setClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                int position = members_recyclerview.getChildLayoutPosition(view);
//                                Members members = listMembers.get(position);
//
//
//                                Intent intent = new Intent(getApplicationContext(), FacilitatorMemberDetailsActivity.class);
//                                intent.putExtra(Constants.GROUP_ID, members.getGroup_id());
//                                intent.putExtra(Constants.GROUP_NAME, members.getGroup_name());
//                                intent.putExtra(Constants.USER_FIRST_NAME, members.getFirstname());
//                                intent.putExtra(Constants.USER_LAST_NAME, members.getLastname());
//                                intent.putExtra(Constants.USER_NAME, members.getNrc());
//                                intent.putExtra(Constants.USER_PASSWORD, members.getPassword());
//                                intent.putExtra(Constants.USER_ADMISSION_DATE, members.getAdmission_date());
//                                intent.putExtra(Constants.USER_GENDER, members.getGender());
//                                intent.putExtra(Constants.ECAP_ID, members.getEcap_hh_id());
//                                intent.putExtra(Constants.USER_PHONE, members.getPhone_number());
//                                intent.putExtra(Constants.USER_ROLE, members.getUser_role());
//                                intent.putExtra(Constants.CAREGIVER_STATUS, members.getSingle_female_caregiver());
//                                intent.putExtra(Constants.USER_ID, members.getId());
//
//
//                                startActivity(intent);

                            }


                        });
                        reportsAlert.dismiss();

                    }

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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}