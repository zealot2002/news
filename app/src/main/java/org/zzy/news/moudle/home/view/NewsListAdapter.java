package org.zzy.news.moudle.home.view;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mzule.activityrouter.router.Routers;

import org.zzy.news.R;
import org.zzy.news.moudle.home.model.NewsBean;
import org.zzy.news.util.ImageLoaderUtil;
import org.zzy.news.util.RouterUtil;

import java.util.ArrayList;
import java.util.List;


public class NewsListAdapter<T> extends BaseAdapter {

    private List<NewsBean> dataList = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;
/************************************************************************************************************/
    public NewsListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public void setDataList(List<NewsBean> dataList){
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList == null?0:dataList.size();
    }

    @Override
    public NewsBean getItem(int position) {
        return dataList == null?null:dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.news_item, null);
            holder = new ViewHolder();
            holder.tvTitle = (TextView)convertView.findViewById(R.id.tvTitle);
            holder.tvFrom = (TextView)convertView.findViewById(R.id.tvFrom);
            holder.ivCover = (ImageView)convertView.findViewById(R.id.ivCover);
            holder.rlItem = (RelativeLayout) convertView.findViewById(R.id.rlItem);

            holder.rlItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Routers.open(context, RouterUtil.getRouteUrl("webview",dataList.get(position).getLinkUrl()));
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.tvTitle.setText(dataList.get(position).getTitle());
        holder.tvFrom.setText(dataList.get(position).getFrom());
        ImageLoaderUtil.getInstance().showPic(context, dataList.get(position).getCoverUrl01(),holder.ivCover);
        return convertView;
    }

    public static class ViewHolder {
        public TextView tvTitle,tvFrom;
        public ImageView ivCover;
        public RelativeLayout rlItem;

    }
}
