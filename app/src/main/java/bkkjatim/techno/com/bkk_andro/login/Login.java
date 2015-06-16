package bkkjatim.techno.com.bkk_andro.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import bkkjatim.techno.com.bkk_andro.News.News;
import bkkjatim.techno.com.bkk_andro.R;
import bkkjatim.techno.com.bkk_andro.config.SessionManager;


/**
 * Created by ari on 5/4/15.
 */
public class Login extends Activity {
    private Button btnAlumni;
    private Button btnSekolah;
    private Button btnPerusahaan;
    SessionManager sessionManager;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        btnAlumni = (Button) findViewById(R.id.btn_alumni);
        btnPerusahaan = (Button) findViewById(R.id.btn_perusahaan);
        btnSekolah = (Button) findViewById(R.id.btn_sekolah);
        sessionManager = new SessionManager(getApplicationContext());
        if (sessionManager.loggedIn()){
            Intent intent = new Intent(Login.this,News.class);
            startActivity(intent);
            finish();
        }
        btnAlumni.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        Login_alumni.class);
                startActivity(i);
                finish();
            }
        });
        btnPerusahaan.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        Login_perusahaan.class);
                startActivity(i);
                finish();
            }
        });
        btnSekolah.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        Login_sekolah.class);
                startActivity(i);
                finish();
            }
        });





    }

}
