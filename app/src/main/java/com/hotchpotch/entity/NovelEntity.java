package com.hotchpotch.entity;

import java.util.List;

/**
 * Created by lxm.
 */

public class NovelEntity {

    private String requestTime;
    private int code;
    private List<NovelItem> data;
    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<NovelItem> getData() {
        return data;
    }

    public void setData(List<NovelItem> data) {
        this.data = data;
    }


    public static class NovelItem {

        private String imageUrl;
        private String bookName;
        private String author;
        private String introduction;
        private int id;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
