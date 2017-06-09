package org.zzy.news.util;

/**
 * Created by zhaoziying on 2017/6/9.
 */

public class RouterUtil {
    public static String getRouteUrl(String target,String url){
        return "zzy://"+target+"?url="+url;
    }
}
