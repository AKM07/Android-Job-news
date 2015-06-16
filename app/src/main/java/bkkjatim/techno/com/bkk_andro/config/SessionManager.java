package bkkjatim.techno.com.bkk_andro.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

/**
 * Created by ari on 5/22/15.
 */
public class SessionManager {
    private String TAG = SessionManager.class.getSimpleName();
    SharedPreferences pref;
    Editor editor;
    Context _context;
    int Private_mode = 0;

    private static final String Pref_name = "BKK JATIM";

    private static final  String KEY_IS_LOGGED_IN = "loggedIn";

    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(Pref_name,Private_mode);
        editor = pref.edit();
    }

    public void setLogin(boolean loggedIn){
        editor.putBoolean(KEY_IS_LOGGED_IN,loggedIn);
         editor.commit();
        Log.d(TAG,"Session pengguna diubah");
    }
    public boolean loggedIn(){
        return pref.getBoolean(KEY_IS_LOGGED_IN,false);
    }
}
