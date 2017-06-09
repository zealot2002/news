package org.zzy.news.moudle.home.view;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.github.mzule.activityrouter.annotation.Router;

import org.zzy.aframwork.base.BaseActivity;
import org.zzy.news.R;
import org.zzy.news.moudle.home.HomeContract;
import org.zzy.news.moudle.home.model.NewsBean;
import org.zzy.news.moudle.home.presenter.HomePresenter;

import java.util.List;

@Router("category")
public class HomeActivity extends BaseActivity implements HomeContract.View{

    private List<NewsBean> newsList;
    private HomeContract.Presenter presenter;

    private ListView lvNews;
    private NewsListAdapter adapter;
    /*******************************************************************************************************/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new HomePresenter(this);
        presenter.getNewsList();
    }

//    @Override
    public void findViews() {
        setContentView(R.layout.activity_main);
        lvNews = (ListView) findViewById(R.id.lvNews);
        adapter = new NewsListAdapter(this);
        lvNews.setAdapter(adapter);
        adapter.setDataList(newsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void showDisconnect(String s) {
        showLongToast(s);
    }

    @Override
    public void showError(String s) {
        showLongToast(s);
    }

    @Override
    public void updateUI(Object o) {
        newsList = (List<NewsBean>) o;
        findViews();
    }

    @Override
    public void setPresenter(Object o) {

    }
}
