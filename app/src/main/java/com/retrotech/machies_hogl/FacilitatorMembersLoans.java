package com.retrotech.machies_hogl;

import static com.retrotech.machies_hogl.Constants.BASE_URL;

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
import android.widget.ImageView;
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
import com.retrotech.machies_hogl.adapters.MembersAdapter;
import com.retrotech.machies_hogl.models.Members;
import com.retrotech.machies_hogl.utils.Connectivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FacilitatorMembersLoans extends AppCompatActivity {
    RecyclerView members_recyclerview;
    RequestQueue mRequestQueue;
    ArrayList<Members> listMembers = new ArrayList<>();
    Context context;
    String str_a, members_list = BASE_URL + "list_of_group_members_facilitator.php";
    String membership_response = BASE_URL + "membership_response.php";
    String str_user_role,str_my_name,str_group_name;
    MembersAdapter membersAdapter;
    ImageView members_approvals;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Group Members");
        toolbar.setSubtitle("Group Members Loans");
        toolbar.setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.slide_out_right);
                Intent intent = new Intent(getApplicationContext(), FacilitatorGroupLoans.class);
                startActivity(intent);
            }
        });
        members_recyclerview = findViewById(R.id.members_recyclerview);

        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        context = FacilitatorMembersLoans.this;
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
                Log.v("transactions_response", response);
                try {
                    JSONObject object = new JSONObject(response);
                    str_my_name = object.getString("full_name");
                    str_user_role = object.getString("user_role");
                    str_group_name = object.getString("group_name");
                    JSONArray array = object.getJSONArray("group_members");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject stackObject = array.getJSONObject(i);
//                        JSONObject stackObject2 = array2.getJSONObject(i);

                        // textView.setText(object1.toString());
                        Members members = new Members(
                                stackObject.getString(Constants.GROUP_NAME),
                                stackObject.getString(Constants.GROUP_ID),
                                stackObject.getString(Constants.USER_FIRST_NAME),
                                stackObject.getString(Constants.USER_LAST_NAME),
                                stackObject.getString(Constants.USER_NAME),
                                stackObject.getString(Constants.USER_PASSWORD),
                                stackObject.getString(Constants.USER_ADMISSION_DATE),
                                stackObject.getString(Constants.USER_GENDER),
                                stackObject.getString(Constants.ECAP_ID),
                                stackObject.getString(Constants.USER_PHONE),
                                stackObject.getString(Constants.USER_ROLE),
                                stackObject.getString(Constants.CAREGIVER_STATUS),
                                stackObject.getString(Constants.USER_ID)
                        );
                        listMembers.add(members);
                    }
                    if(str_user_role.equals("2") || str_user_role.equals("3") || str_user_role.equals("4")){
//                        members_approvals.setVisibility(View.VISIBLE);
                    }else{
//                        members_approvals.setVisibility(View.GONE);
                    }
                    if (listMembers.size() <= 0) {
                        members_recyclerview.setVisibility(View.GONE);
//                        no_transactions_txt.setVisibility(View.VISIBLE);
                        reportsAlert.dismiss();
                    } else {
                        members_recyclerview.setHasFixedSize(true);
                        members_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        members_recyclerview.addItemDecoration(new DividerItemDecoration(getBaseContext(), DividerItemDecoration.HORIZONTAL));
                        membersAdapter = new MembersAdapter(getBaseContext(), listMembers);
                        members_recyclerview.setAdapter(membersAdapter);
                        membersAdapter.setClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int position = members_recyclerview.getChildLayoutPosition(view);
                                Members members = listMembers.get(position);


                                Intent intent = new Intent(getApplicationContext(), MembersDetailsActivity.class);
                                intent.putExtra("intent_full_name", members.getFirstname() + " " + members.getLastname());
//                                intent.putExtra("intent_email", members.getEmail());
                                intent.putExtra("intent_nrc", members.getNrc());
//                                intent.putExtra("intent_address", members.getAddress());
                                intent.putExtra("intent_group_id", members.getGroup_id());
//                                intent.putExtra("intent_chairperson_approval", members.getChairperson_approval());
//                                intent.putExtra("intent_treasurer_approval", members.getTreasurer_approval());
//                                intent.putExtra("intent_secretary_approval", members.getSecretary_approval());
                                startActivity(intent);

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
    /*private void msgDialog(String msg,
                           final String id,
                           final String d_fullname,
                           final String d_email,
                           final String d_nrc,
                           final String d_address,
                           final String d_group_id,
                           final String d_chairperson_approval,
                           final String d_treasurer_approval,
                           final String d_secretary_approval) {
        LinearLayout yes, view, no;
        TextView message;
        ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_response, viewGroup, false);
        yes = dialogView.findViewById(R.id.yes);
        view = dialogView.findViewById(R.id.view);
        no = dialogView.findViewById(R.id.no);
        message = dialogView.findViewById(R.id.msg);
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
        message.setText(msg);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                membershipResponse("1",id);
                reportsAlert.dismiss();
//                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                SharedPreferences.Editor editor = preferences.edit();
//                editor.putString("login_status", "0");
//                editor.apply();
//                finish();
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportsAlert.dismiss();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("intent_full_name", d_fullname);
                intent.putExtra("intent_email", d_email);
                intent.putExtra("intent_nrc", d_nrc);
                intent.putExtra("intent_address", d_address);
                intent.putExtra("intent_group_id", d_group_id);
                intent.putExtra("intent_chairperson_approval", d_chairperson_approval);
                intent.putExtra("intent_treasurer_approval", d_treasurer_approval);
                intent.putExtra("intent_secretary_approval", d_secretary_approval);
                startActivity(intent);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                membershipResponse("2",id);
                reportsAlert.dismiss();
            }
        });
    }
*/
    private void membershipResponse(
            final String response,
            final String requestor_id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, membership_response, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                //Log.v("login_url",response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").equals("success")) {// same as if (object.getBoolean("success") == true) {

                        errorDialog(object.getString("msg"));
                    } else if (object.getString("status").equals("failed")) {

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

            //            first_name, surname, nrc, phone, email, pas, dob
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("requestor_id", requestor_id);
                parms.put("response", response);
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
        finish();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}


