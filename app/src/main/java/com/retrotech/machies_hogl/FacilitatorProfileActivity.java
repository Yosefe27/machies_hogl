package com.retrotech.machies_hogl;

import static com.retrotech.machies_hogl.Constants.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import android.view.Menu;
import android.view.MenuItem;
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
import com.retrotech.machies_hogl.adapters.FacilitatorProfileAdapter;
import com.retrotech.machies_hogl.models.FacilitatorProfileModel;
import com.retrotech.machies_hogl.utils.Connectivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FacilitatorProfileActivity extends AppCompatActivity {
    DrawerLayout drawer;
    NavigationView navigationView;
    RecyclerView members_recyclerview;
    RequestQueue mRequestQueue;
    BottomNavigationView bottomNavigationView;

    ArrayList<FacilitatorProfileModel> listMembers = new ArrayList<>();
    Context context;
    String str_a, members_list = BASE_URL + "view_facilitator_totals.php";

    Toolbar toolbar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilitator_profile);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic__menu);

        members_recyclerview = findViewById(R.id.members_recyclerview);
        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        context = getApplicationContext();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        str_a = preferences.getString("a", "");
        if (isNetworkAvailable()) {
            membersList();
        } else {
            errorDialog("Please Check Your Internet Connection");
        }

        drawer = findViewById(R.id.DrawerLayout);
        navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_services:
                        Intent i = new Intent(FacilitatorProfileActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();

                    default:
                        break;
                }
                item.setChecked(true);
                drawer.closeDrawers();
                return true;
            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_services:
                        Intent intent = new Intent(FacilitatorProfileActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        return true;

                }
                return false;

            }
        });

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog reportsAlert = builder.create();
        if (reportsAlert.getWindow() != null)
            reportsAlert.getWindow().getAttributes().windowAnimations = R.style.SlidingDialogAnimation;

        reportsAlert.show();
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        StringRequest request = new StringRequest(Request.Method.POST, members_list, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("members_list", response);
                try {
                    JSONObject object = new JSONObject(response);

                    JSONArray array = object.getJSONArray("members_list");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject stackObject = array.getJSONObject(i);

                        FacilitatorProfileModel members = new   FacilitatorProfileModel (
                                stackObject.getString("full_name"),
                                stackObject.getString("id"),
                                stackObject.getString("gender"),
                                stackObject.getString("admission_date"),
                                stackObject.getString("group_name"),
                                stackObject.getString("total_groups"),
                                stackObject.getString("total_members"),
                                stackObject.getString("total_contribution")

                        );
                        listMembers.add(members);
                    }

                    if (listMembers.size() <= 0) {
                        members_recyclerview.setVisibility(View.GONE);
                        reportsAlert.dismiss();
                    } else {
                        members_recyclerview.setHasFixedSize(true);
                        members_recyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        members_recyclerview.addItemDecoration(new DividerItemDecoration(getBaseContext(), DividerItemDecoration.HORIZONTAL));
                        FacilitatorProfileAdapter  memberProfileAdapter = new FacilitatorProfileAdapter(getBaseContext(),listMembers);
                        members_recyclerview.setAdapter(memberProfileAdapter);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.member_profile,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_logout:
                dialogLogout();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public void dialogLogout() {

        ViewGroup viewGroup = findViewById(android.R.id.content);
        TextView btn_yes, btn_no;
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_logout, viewGroup, false);
        btn_yes = dialogView.findViewById(R.id.btn_yes);
        btn_no = dialogView.findViewById(R.id.btn_no);

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
        reportsAlert.getWindow().setBackgroundDrawableResource(android.R.color.white);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportsAlert.dismiss();
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("pref_login_status", "0");
                editor.apply();
                reportsAlert.dismiss();
                finish();
                Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(getApplicationContext(), BookWriterSocialDashboard.class);
//        startActivity(intent);
//        finish();
    }
}