package com.retrotech.machies_hogl;

import static com.retrotech.machies_hogl.Constants.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.retrotech.machies_hogl.adapters.FacilitatorRegisterAdapter;
import com.retrotech.machies_hogl.models.FacilitatorRegisterModel;
import com.retrotech.machies_hogl.utils.Connectivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FacilitatorRegisterActivity extends AppCompatActivity {
    RequestQueue mRequestQueue;
    String str_a, groups_list = BASE_URL + "view_member_register_view.php";
    RecyclerView recyclerview;
    ArrayList<FacilitatorRegisterModel> listGroups = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilitator_register);
        recyclerview = findViewById(R.id.view_members_facilitator);

        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        str_a = preferences.getString("a", "");
        if (isNetworkAvailable()) {
            groupsList();
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

    public void groupsList() {
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
        StringRequest request = new StringRequest(Request.Method.POST, groups_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(),"Response : "+response,Toast.LENGTH_SHORT).show();
//                textView.setText(response.toString());
                Log.v("members_list", response);
                try {
                    JSONObject object = new JSONObject(response);

                    JSONArray array = object.getJSONArray("members_list");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject stackObject = array.getJSONObject(i);
//                        JSONObject stackObject2 = array2.getJSONObject(i);

                        // textView.setText(object1.toString());
                        FacilitatorRegisterModel groups = new FacilitatorRegisterModel (
                                stackObject.getString("group_id"),
                                stackObject.getString("group_name"),
                                stackObject.getString("register")

                        );
                        listGroups.add(groups);
                    }

                    if (listGroups.size() <= 0) {
                        recyclerview.setVisibility(View.GONE);
                        reportsAlert.dismiss();
                    } else {
                        recyclerview.setHasFixedSize(true);
                        recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        recyclerview.addItemDecoration(new DividerItemDecoration(getBaseContext(), DividerItemDecoration.HORIZONTAL));
                        FacilitatorRegisterAdapter facilitatorRegisterAdapter = new  FacilitatorRegisterAdapter(listGroups,getBaseContext());
                        recyclerview.setAdapter(facilitatorRegisterAdapter);
//                        facilitatorViewGroupMembersAdapter.setClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                int position = recyclerview.getChildLayoutPosition(view);
//                                FacilitatorViewMembersModel groups = listGroups.get(position);
//
//                                Intent intent = new Intent(getApplicationContext(), ViewActualMembersFacilitator.class);
////                                intent.putExtra("intent_group_id", groups.getId());
////                                intent.putExtra("intent_group_name", groups.getGroup_name());
////                                intent.putExtra("intent_group_date_created", groups.getDate_created());
////                                intent.putExtra("intent_group_annual_interest_rate", groups.getAnnual_interest_rate());
////                                intent.putExtra("intent_cycle_number", groups.getCycle_number());
////                                intent.putExtra("intent_first_training_meeting_date", groups.getFirst_training_meeting_date());
////                                intent.putExtra("intent_date_savings_started", groups.getDate_savings_started());
////                                intent.putExtra("intent_reinvested_savings_cycle_start", groups.getReinvested_savings_cycle_start());
////                                intent.putExtra("registered_members_cycle_start", groups.getRegistered_members_cycle_start());
////                               // intent.putExtra("group_management_spinner", groups.getGroup_management_spinner());
////                                intent.putExtra("intent_group_status", groups.getStatus());
//
//                                Bundle bundle = new Bundle();
//
//                                bundle.putString(Constants.GROUP_ID, groups.getId());
//
//                                intent.putExtras(bundle);
//                                startActivity(intent);
//
//                            }
//
//
//                        });
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