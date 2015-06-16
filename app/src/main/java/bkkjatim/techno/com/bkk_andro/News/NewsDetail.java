package bkkjatim.techno.com.bkk_andro.News;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
import bkkjatim.techno.com.bkk_andro.config.CustomListDetailAdapter;


/**
 * Created by ari on 6/3/15.
 */
public class NewsDetail extends ActionBarActivity {
    private static final String url = "http://192.168.1.110/bkk_andro/index.php/news/findNewsById/";
    private static final String TAG = NewsDetail.class.getSimpleName();
    private static final String ID = "ID";
    private static final String imageUrl = "GAMBAR_BESAR";
    private static final String createdDate = "CREATED_DATE";
    private static final String articleTitle = "JUDUL_LOWONGAN";
    private static final String companyDescription = "DESKRIPSI_PERUSAHAAN";
    private static final String companyAddress = "ALAMAT";
    private static final String companyWebsite = "WEBSITE";
    private static final String companyProvince = "PROPENSI";
    private static final String jobDetail = "INFORMASI_LOWONGAN";
    private static final String jobDateExpire = "BATAS_LOWONGAN";
    private static final String companyName = "NAMA_PERUSAHAAN";
    private static final String department = "PROGRAM_KEAHLIAN";
    private ProgressDialog progressDialog;
    private List<NewsDetailModel> newsList = new ArrayList<NewsDetailModel>();
    private ListView listView;
    private CustomListDetailAdapter adapter;
    String id;
    String UrlWithId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.list);
        adapter = new CustomListDetailAdapter(this, newsList);
        listView.setAdapter(adapter);
        Intent intent = getIntent();
        String item = intent.getStringExtra("selected-item");
        UrlWithId = url+item;
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        JsonArrayRequest newsDetailReq = new JsonArrayRequest(UrlWithId,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                id = obj.getString(ID);
                                NewsDetailModel newsDetailModel = new NewsDetailModel();
                                newsDetailModel.setCreatedDate(obj.getString(createdDate));
                                newsDetailModel.setArticleTitle(obj.getString(articleTitle));
                                newsDetailModel.setDepartment(obj.getString(department));
                                newsDetailModel.setImageUrl(obj.getString(imageUrl));
                                newsDetailModel.setJobDetail(obj.getString(jobDetail));
                                newsDetailModel.setJobExpireDate(obj.getString(jobDateExpire));
                                newsDetailModel.setCompanyName(obj.getString(companyName));
                                newsDetailModel.setCompanyDescription(obj.getString(companyDescription));
                                newsDetailModel.setCompanyAddress(obj.getString(companyAddress));
                                newsDetailModel.setCompanyWebsite(obj.getString(companyWebsite));
                                newsDetailModel.setCompanyProvince(obj.getString(companyProvince));

                                newsList.add(newsDetailModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });
        AppController.getInstance().addToRequestQueue(newsDetailReq);
    }
    private void hidePDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
