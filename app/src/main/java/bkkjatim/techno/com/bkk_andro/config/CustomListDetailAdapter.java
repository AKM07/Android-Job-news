package bkkjatim.techno.com.bkk_andro.config;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import bkkjatim.techno.com.bkk_andro.News.NewsDetailModel;
import bkkjatim.techno.com.bkk_andro.R;


/**
 * Created by ari on 6/4/15.
 */
public class CustomListDetailAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<NewsDetailModel> newsItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListDetailAdapter(Activity activity, List<NewsDetailModel> newsItems){
        this.activity = activity;
        this.newsItems = newsItems;
    }
    @Override
    public int getCount(){
        return newsItems.size();
    }
    @Override
    public Object getItem(int location){
        return newsItems.get(location);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_detail_row, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.imageShow);
        TextView date = (TextView) convertView.findViewById(R.id.createDate);
        TextView articleTitle = (TextView)convertView.findViewById(R.id.articleTitle);
        TextView department = (TextView)convertView.findViewById(R.id.department);
        TextView articleDetail = (TextView)convertView.findViewById(R.id.articleDetail);
        TextView jobExpireDate = (TextView)convertView.findViewById(R.id.jobExpireDate);
        TextView companyName = (TextView)convertView.findViewById(R.id.companyName);
        TextView companyDescription = (TextView)convertView.findViewById(R.id.companyDescription);
        TextView companyAddress = (TextView)convertView.findViewById(R.id.companyAddress);
        TextView companyWebsite = (TextView)convertView.findViewById(R.id.companyWebsite);
        TextView companyProvince = (TextView)convertView.findViewById(R.id.companyProvince);

        NewsDetailModel n = newsItems.get(position);
        thumbNail.setImageUrl(n.getImageUrl(), imageLoader);
        date.setText(n.getCreatedDate());
        articleTitle.setText(n.getArticleTitle());
        department.setText(n.getDepartment());
        articleDetail.setText(n.getJobDetail());
        jobExpireDate.setText("Batas Akhir lamaran kerja : "+n.getJobExpireDate());
        companyName.setText("Nama Perusahaan : "+n.getCompanyName());
        companyDescription.setText("Detail Perusahaan : "+n.getCompanyDescription());
        companyAddress.setText("Alamat : "+n.getCompanyAddress());
        companyWebsite.setText("Website : "+n.getCompanyWebsite());
        companyProvince.setText("Provinsi : "+n.getCompanyProvince());
        return convertView;
    }
}
