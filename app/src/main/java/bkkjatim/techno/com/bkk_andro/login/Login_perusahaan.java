package bkkjatim.techno.com.bkk_andro.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bkkjatim.techno.com.bkk_andro.News.News;
import bkkjatim.techno.com.bkk_andro.R;
import bkkjatim.techno.com.bkk_andro.config.JsonParser;


/**
 * Created by ari on 5/4/15.
 */
public class Login_perusahaan extends Activity implements OnClickListener{
    private Button btn_back;
    private EditText txtemail;
    private EditText pass;
    private Button btnLogin;
    private ProgressDialog progressDialog;
    JsonParser jsonParser = new JsonParser();

    private static String url = "http://192.168.1.110/bkk_andro/index.php/login";
    private static final String TAG_SUCCES = "success";
    private static final String TAG_MESSAGE = "message";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_perusahaan_layout);

        txtemail = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.email_sign_in_button);
        btn_back = (Button) findViewById(R.id.btn_back);
        btnLogin.setOnClickListener(this);

        btn_back.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        Login.class);
                startActivity(i);
                finish();
            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.email_sign_in_button:
                new AttemptLogin().execute();
            default:
                break;
        }
    }
    class AttemptLogin extends AsyncTask<String, String, String>{
        boolean failure = false;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(Login_perusahaan.this);
            progressDialog.setMessage("Tunggu Sebentar...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }
        @Override
        protected String doInBackground(String... args) {
            int success;
            String email = txtemail.getText().toString();
            String password = pass.getText().toString();
            try {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("EMAIL", email));
                params.add(new BasicNameValuePair("PASSWORD", password));
                Log.d("request!", "starting");

                JSONObject json = jsonParser.makeHttpRequest(
                        url, "POST", params);

                Log.d("Login attempt", json.toString());
                success = json.getInt(TAG_SUCCES);
                if (success == 1) {
                    Log.d("Login Sukses", json.toString());

                    Intent ii = new Intent(Login_perusahaan.this,News.class);
                    startActivity(ii);

                    finish();

                    return json.getString(TAG_MESSAGE);
                }else{

                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        protected void onPostExecute(String message) {

            progressDialog.dismiss();
            if (message != null){
                Toast.makeText(Login_perusahaan.this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
