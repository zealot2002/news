package org.zzy.news.moudle.home.model;


import java.util.ArrayList;

/**
 * Created by admin on 16/12/13.
 */

public class NewsBean {
//    "uniquekey":"2854081b8d2e3c8262f676db8aa809b6",
//            "title":"别再误会宋小宝了，赵海燕和他才是真正的夫妻",
//            "date":"2017-06-07 15:00",
//            "category":"头条",
//            "author_name":"太阳辐射",
//            "url":"http://mini.eastday.com/mobile/170607150005719.html",
//            "thumbnail_pic_s":"http://03.imgmini.eastday.com/mobile/20170607/20170607150005_d1c06118155259c9b31bf3782c0e7a16_6_mwpm_03200403.jpeg",
//            "thumbnail_pic_s02":"http://03.imgmini.eastday.com/mobile/20170607/20170607150005_d1c06118155259c9b31bf3782c0e7a16_4_mwpm_03200403.jpeg",
//            "thumbnail_pic_s03":"http://03.imgmini.eastday.com/mobile/20170607/20170607150005_d1c06118155259c9b31bf3782c0e7a16_3_mwpm_03200403.jpeg"

    private String id;
    private String title;
    private String date;
    private String category;
    private String from;
    private String linkUrl;
    private String coverUrl01;
    private String coverUrl02;
    private String coverUrl03;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getCoverUrl01() {
        return coverUrl01;
    }

    public void setCoverUrl01(String coverUrl01) {
        this.coverUrl01 = coverUrl01;
    }

    public String getCoverUrl02() {
        return coverUrl02;
    }

    public void setCoverUrl02(String coverUrl02) {
        this.coverUrl02 = coverUrl02;
    }

    public String getCoverUrl03() {
        return coverUrl03;
    }

    public void setCoverUrl03(String coverUrl03) {
        this.coverUrl03 = coverUrl03;
    }
}
