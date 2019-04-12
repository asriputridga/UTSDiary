package com.asriputridga.diary2.Model;

public class Diary {
    public static final String TABLE_NAME = "diary";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String DATE = "date";
    public static final String LOCATION = "location";

    private int mId;
    private String mTitle;
    private String mContent;
    private String mDate;
    private String mLocation;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }


    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
