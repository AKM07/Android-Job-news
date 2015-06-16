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

import bkkjatim.techno.com.bkk_andro.News.NewsModel;
import bkkjatim.techno.com.bkk_andro.R;


/**
 * Created by ari on 6/3/15.
 */
public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<NewsModel> newsItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<NewsModel> newsItems){
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
            convertView = inflater.inflate(R.layout.list_row, null);
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.imageShow);
        TextView articleTitle = (TextView)convertView.findViewById(R.id.articleTitle);
        TextView date = (TextView) convertView.findViewById(R.id.createDate);

        NewsModel n = newsItems.get(position);
        thumbNail.setImageUrl(n.getImageUrl(), imageLoader);

        articleTitle.setText(n.getArticleTitle());
        date.setText(n.getArticleDate());
        return convertView;
    }
}
