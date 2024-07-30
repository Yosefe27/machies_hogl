package com.retrotech.machies_hogl;

import static com.retrotech.machies_hogl.Constants.BASE_URL;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

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

public class BookWriterSavingEdit extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Toolbar toolbar;
    RequestQueue mRequestQueue;
    TextView save_member_details, groupName, groupID,txt_user_role;
    EditText tran_type,tran_amount,tran_month,tran_ref,entry_id,contributor_id,full_name;
    Spinner spinner_singleFSW, spinner_gender, spinner_user_role;
    String submit_member_url = BASE_URL + "update_savings.php";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_writer_saving_edit);


        setSupportActionBar(toolbar);
        tran_type = findViewById(R.id.tran_type);
        tran_amount = findViewById(R.id.tran_amount);
        tran_month = findViewById(R.id.tran_month);
        tran_ref = findViewById(R.id.tran_ref);
        entry_id = findViewById(R.id.entry_id);
        contributor_id = findViewById(R.id.contributor_id);
        full_name =  findViewById(R.id.full_name);
        save_member_details = findViewById(R.id.save_member_details);

        String intent_tran_type = getIntent().getStringExtra("tran_type");
        String intent_tran_amount= getIntent().getStringExtra("tran_amount");
        String intent_tran_month = getIntent().getStringExtra("tran_month");
        String intent_tran_ref = getIntent().getStringExtra("tran_ref");
        String intent_entry_id = getIntent().getStringExtra("entry_id");
        String intent_contributor_id = getIntent().getStringExtra("contributor_id");
        String intent_full_name = getIntent().getStringExtra("full_name");

        tran_type.setText(intent_tran_type);
        tran_amount.setText(intent_tran_amount);

        tran_month.setText(intent_tran_month);
        tran_month.setEnabled(false);

        tran_ref.setText(intent_tran_ref);
        entry_id.setText(intent_entry_id);

        contributor_id.setText(intent_contributor_id);
        contributor_id.setEnabled(false);

        full_name.setText(intent_full_name);


        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        save_member_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str_entry_id = entry_id.getText().toString();
                String str_tran_amount = tran_amount.getText().toString();
                String str_full_name = full_name.getText().toString();
                String str_contributor_id = contributor_id.getText().toString();
                String str_tran_month = tran_month.getText().toString();
                String str_tran_type = tran_type.getText().toString();
                String str_tran_ref = tran_ref.getText().toString();

                if(tran_amount.getText().toString().isEmpty()) {
                    errorDialog("Username Cannot Be Empty");
                }
                else startSubmission(
                        str_entry_id,
                        str_tran_amount,
                        str_full_name,
                        str_contributor_id,
                        str_tran_month,
                        str_tran_type,
                        str_tran_ref

                );
            }
        });

    }
    private void startSubmission(

                                final String entry_id,
                                final String tran_amount,
                                final String full_name,
                                final String contributor_id,
                                final String tran_month,
                                final String tran_type,
                                final String tran_ref

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
                Log.v("transactions_response", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").equals("success")) {

                        String received_msg = object.getString("msg");

                        reportsAlert.dismiss();
                        errorDialog(object.getString("msg"));
                        finish();
                        Intent intent = new Intent(getApplicationContext(), BookWriterSavingsOptionsDashboard.class);
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
                parms.put("entry_id", entry_id);
                parms.put("amount", tran_amount);
                parms.put("full_name", full_name);
                parms.put("contributor_id",contributor_id);
                parms.put("month_contributed_for",tran_month);
                parms.put("payment_mode", tran_type);
                parms.put("payment_ref_number", tran_ref);
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
        Intent intent = new Intent(getApplicationContext(), MyGroupTransactionsHistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}