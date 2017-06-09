package org.zzy.news.moudle.home.model;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.zzy.aframwork.network.HttpUtil;
import org.zzy.aframwork.network.util.HttpConstant;
import org.zzy.aframwork.network.util.HttpInterface;
import org.zzy.aframwork.network.util.RequestCtx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by admin on 16/12/13.
 */

public class DataProvider {

    public static void getNewsList(HttpInterface.DataCallback callback) {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        String url = org.zzy.news.constant.HttpConstant.GET_NEWS_LIST;
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization","APPCODE 9c6b61ad8e414b88a62fcfab9c8afda7");
        RequestCtx ctx = new RequestCtx.Builder(map)
                .methodAndUrl(HttpConstant.HTTP_METHOD_GET, url)
                .callback(callback)
                .jsonParser(getNewsListJsonParser)
                .headerMap(headers)
                .build();
        try {
            HttpUtil.getInstance().request(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HttpInterface.JsonParser getNewsListJsonParser = new HttpInterface.JsonParser() {
        @Override
        public Object[] parse(String str) throws JSONException {
            List<NewsBean> newsList = new ArrayList<>();
            JSONTokener jsonParser = new JSONTokener(str);
            JSONObject obj = (JSONObject) jsonParser.nextValue();
            int errorCode = obj.getInt(org.zzy.news.constant.HttpConstant.HTTP_ERROR);
            if (errorCode == org.zzy.news.constant.HttpConstant.ERROR_NO_ERROR) {
                JSONObject resultObj = obj.getJSONObject("result");

                /*news list*/
                JSONArray newsArray = resultObj.getJSONArray("data");
                for (int i = 0; i < newsArray.length(); i++) {
                    JSONObject newsObj = newsArray.getJSONObject(i);

                    NewsBean newsBean = new NewsBean();
                    newsBean.setId(newsObj.getString("uniquekey"));
                    newsBean.setTitle(newsObj.getString("title"));
                    newsBean.setDate(newsObj.getString("date"));
                    newsBean.setCategory(newsObj.getString("category"));
                    newsBean.setFrom(newsObj.getString("author_name"));
                    newsBean.setLinkUrl(newsObj.getString("url"));
                    newsBean.setCoverUrl01(newsObj.getString("thumbnail_pic_s"));
                    if(newsObj.has("thumbnail_pic_s02")) newsBean.setCoverUrl02(newsObj.getString("thumbnail_pic_s02"));
                    if(newsObj.has("thumbnail_pic_s03")) newsBean.setCoverUrl03(newsObj.getString("thumbnail_pic_s03"));
                    newsList.add(newsBean);
                }
            } else {
                String msg = obj.getString(org.zzy.news.constant.HttpConstant.HTTP_MESSAGE);
                return new Object[]{HttpConstant.FAIL, HttpUtil.getErrorMsg(msg, errorCode)};
            }
            return new Object[]{HttpConstant.SUCCESS, newsList};
        }
    };
}
