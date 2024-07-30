package com.retrotech.machies_hogl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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

import static com.retrotech.machies_hogl.Constants.BASE_URL;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    CheckBox checkBox;
    TextView signin;
    TextView signup;
    ProgressBar signin_progress;
    RequestQueue mRequestQueue;
    String login_url = BASE_URL + "login_url.php",str_device_id,login_status;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.login_password);
        checkBox = findViewById(R.id.stay_logged_in);
        signin = findViewById(R.id.btn_login);
        signup = findViewById(R.id.sign_up_text);

        mRequestQueue = Connectivity.getInstance(this).getRequestQueue();
        try {
            final TelephonyManager tm = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
            str_device_id = "jgjggj";//tm.getDeviceId();
//                tmSerial = "" + tm.getSimSerialNumber();
//                androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
//                UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
//                deviceId = deviceUuid.toString();
        } catch (SecurityException e) {}
//        checkAndRequestPermissions();
        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                char[] chars = {'\'', '"', '\\', '*', '(', ')', '<', '>', '?', '!', ' ', '+', '-', ';', ','};
                for (int i = start; i < end; i++) {
                    if (new String(chars).contains(String.valueOf(source.charAt(i)))) {
                        return "";
                    }
                }
                return null;
            }
        };
        email.setFilters(new InputFilter[]{filter});
        password.setFilters(new InputFilter[]{filter});

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    //deleteCache(context);
                    if (checkBox.isChecked()) {
                        login_status = "1";
                    } else {
                        login_status = "0";
                    }
                    String str_email_address = email.getText().toString();
                    String str_password = password.getText().toString();
                    if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                        errorDialog("Please Ensure All Fields are Filled");
                    } else {
                        startLogin(str_email_address, str_password,str_device_id);
                    }


                } else {
//                   finish();
//                   Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                   startActivity(intent);
                    errorDialog("Please Check Your Internet Connection");

                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


    private void startLogin(
            final String email_address,
            final String password,
            final String device_id) {
//        signin_progress.setVisibility(View.VISIBLE);
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                Log.v("login_url", response);
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("status").equals("success")) {// same as if (object.getBoolean("success") == true) {
                        String name = object.getString("name");
                        String group_id = object.getString("group_id");
                        String id = object.getString("id");
                        String user_role = object.getString("user_role");
                        String group_name = object.getString("group_name");
                        String received_a = object.getString("a");

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = preferences.edit();

                        editor.putString("pref_login_status",login_status);
                        editor.putString("login_status", login_status);
                        editor.putString("name", name);
                        editor.putString(Constants.GROUP_ID, group_id);
                        editor.putString("id", id);
                        editor.putString("user_role", user_role);
                        editor.putString(Constants.GROUP_NAME, group_name);
                        editor.putString("a", received_a);


                        editor.apply();
                        reportsAlert.dismiss();
                        String str_user_role = preferences.getString("user_role", "");
                        if(str_user_role.equals("Book Writer")||str_user_role.equals("Ordinary Member")){
                            finish();
                            Intent intent = new Intent(getApplicationContext(), MemberProfileActivity.class);
                            startActivity(intent);
                        } else  {
                            finish();
                            Intent intent = new Intent(getApplicationContext(), FacilitatorProfileActivity.class);
                            startActivity(intent);
                        }
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

            //            first_name, surname, nrc, phone, email, pas, dob
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parms = new HashMap<String, String>();
                parms.put("email", email_address);
                parms.put("password", password);
                parms.put("device_id", str_device_id);

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

}