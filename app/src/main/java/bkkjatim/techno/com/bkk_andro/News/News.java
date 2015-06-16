package bkkjatim.techno.com.bkk_andro.News;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import bkkjatim.techno.com.bkk_andro.R;
import bkkjatim.techno.com.bkk_andro.config.AppController;
import bkkjatim.techno.com.bkk_andro.config.CustomListAdapter;
import bkkjatim.techno.com.bkk_andro.config.SessionManager;
import bkkjatim.techno.com.bkk_andro.login.Login;
import bkkjatim.techno.com.bkk_andro.profile.Profile;

/**
 * Created by ari on 5/21/15.
 */
public class News extends ActionBarActivity implements SwipeRefreshLayout.OnRefreshListener{

    private static String url = "http://192.168.1.110/bkk_andro/index.php/news";

    private SwipeRefreshLayout swipeRefreshLayout;
    private static final String TAG = News.class.getSimpleName();
    private static final String ID = "ID";
    private static final String imageUrl = "GAMBAR_KECIL";
    private static final String createdDate = "CREATED_DATE";
    private static final String articleTitle = "JUDUL_LOWONGAN";
    private ProgressDialog progressDialog;
    private List<NewsModel> newsList = new ArrayList<NewsModel>();
    private ListView listView;
    private CustomListAdapter adapter;
    private String id;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(new ListClickHandler());
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.container);
        adapter = new CustomListAdapter(this,newsList);
        listView.setAdapter(adapter);
        sessionManager = new SessionManager(getApplicationContext());
        if (!sessionManager.loggedIn()){
            userLogout();
        }
        swipeRefreshLayout.setColorSchemeResources(R.color.sw2);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
                fetchNews();
            }
        });
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

    }
    public void onRefresh() {
        fetchNews();
    }
    private void fetchNews(){
        swipeRefreshLayout.setRefreshing(true);
        JsonArrayRequest newsReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0){
                            Log.d(TAG, response.toString());
                            hidePDialog();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject obj = response.getJSONObject(i);
                                    NewsModel newsModel = new NewsModel();
                                    id = obj.getString(ID);
                                    newsModel.setArticleDate(obj.getString(createdDate));
                                    newsModel.setArticleTitle(obj.getString(articleTitle));
                                    newsModel.setImageUrl(obj.getString(imageUrl));

                                    newsList.add(newsModel);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipeRefreshLayout.setRefreshing(false);
                hidePDialog();
            }
        });
        AppController.getInstance().addToRequestQueue(newsReq);
    }
    public class ListClickHandler implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            Intent intent = new Intent(News.this, NewsDetail.class);
            intent.putExtra("selected-item", id);
            startActivity(intent);
        }
    }
    private void hidePDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_logout:
                userLogout();
                return true;
            case R.id.action_profile:
                userProfile();
                return true;
            case R.id.action_search:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void userProfile(){
        Intent intent = new Intent(News.this, Profile.class);
        startActivity(intent);
    }
    private void userLogout(){
        sessionManager.setLogin(false);
        Intent intent = new Intent(News.this, Login.class);
        startActivity(intent);
        finish();
    }

}