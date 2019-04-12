package com.asriputridga.diary2.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.asriputridga.diary2.R;

public class DetailActivity extends AppCompatActivity {
    private TextView mTitle, mContent, mLocDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitle = (TextView)findViewById(R.id.title);
        mContent = (TextView)findViewById(R.id.content);
        mLocDate = (TextView)findViewById(R.id.loc_date);

        //Mendapatkan data dari MainActivity dan ditampilkan dalam TextView;
        mTitle.setText(getIntent().getExtras().getString("title"));
        mContent.setText(getIntent().getExtras().getString("content"));
        mLocDate.setText(getIntent().getExtras().getString("location") + ", "
                + getIntent().getExtras().getString("date"));
    }
}
