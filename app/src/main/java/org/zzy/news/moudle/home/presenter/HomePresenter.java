package org.zzy.news.moudle.home.presenter;


import android.support.annotation.NonNull;

import org.zzy.aframwork.network.util.HttpConstant;
import org.zzy.aframwork.network.util.HttpInterface;
import org.zzy.aframwork.util.CommonUtil;
import org.zzy.news.R;
import org.zzy.news.moudle.home.HomeContract;
import org.zzy.news.moudle.home.model.DataProvider;

import static org.zzy.news.moudle.application.MyApplication.context;

/**
 * Created by admin on 16/11/7.
 */
public class HomePresenter implements HomeContract.Presenter{
    private HomeContract.View view;
/*****************************************************************************************************/

    public HomePresenter(@NonNull HomeContract.View view) {
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getNewsList() {
//        if (!CommonUtil.isNetWorkConnected(context)) {
//            view.showDisconnect(context.getString(R.string.pls_check_your_network));
//            return;
//        }
//        view.showLoading();
                DataProvider.getNewsList(new HttpInterface.DataCallback() {
                    @Override
                    public void requestCallback(int result, Object data, Object tagData) {
                        view.closeLoading();
                        if (result == HttpConstant.SUCCESS){
                            view.updateUI(data);
                        } else {
                            view.showError(data.toString());
                        }
                    }
                });
//            }
//        });
    }



}
