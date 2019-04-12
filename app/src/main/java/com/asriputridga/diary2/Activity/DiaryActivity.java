package com.asriputridga.diary2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.asriputridga.diary2.Database.DiaryHelper;
import com.asriputridga.diary2.Model.Diary;
import com.asriputridga.diary2.R;
import com.asriputridga.diary2.Utils.TimeHelper;

public class DiaryActivity extends AppCompatActivity {
    private EditText mTitle, mContent, mLocation;
    private String mStringTitle, mStringContent, mStringLocation, mStringDate, mStringTag;
    private int mId;
    private CheckBox mUpdateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Intent intent = new Intent(this, DetailActivity.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Jika respon yang didapat dari MainActivity adalah untuk membuat diary baru
                if (mStringTag.equals("create")) {
                    create();
                    //Respon untuk update diary
                } else if (mStringTag.equals("update")) {
                    update();
                }
                //Mengembalikan nilai RESULT_OK untuk menandakan bahwa proses sukses
                setResult(RESULT_OK);
                finish();
            }
        });
        mUpdateTime = findViewById(R.id.update_time);
        mTitle =  findViewById(R.id.title);
        mContent =  findViewById(R.id.content);
        mLocation =  findViewById(R.id.location);
        mStringTag = getIntent().getExtras().getString("tag");
        //Mengatur tampilan untuk kategori update
        if (mStringTag.equals("update")) {
            getSupportActionBar().setTitle("Edit diary");
            mUpdateTime.setVisibility(CheckBox.VISIBLE);
            mId = getIntent().getExtras().getInt("id");
            Log.d("id", mId + "");
            Diary diary = DiaryHelper.getDiary(getApplicationContext(), mId);
            mStringTitle = diary.getTitle();
            mStringContent = diary.getContent();
            mStringLocation = diary.getLocation();
            mStringDate = diary.getDate();
            mTitle.setText(mStringTitle);
            mContent.setText(mStringContent);
            mLocation.setText(mStringLocation);
        } else {
            getSupportActionBar().setTitle("New diary");
            mUpdateTime.setVisibility(CheckBox.GONE);
        }


    }

    //Mendapatkan data dari edittext, lalu update database
    void update() {
        Diary diary = new Diary();
        diary.setId(mId);
        diary.setTitle(mTitle.getText().toString());
        diary.setContent(mContent.getText().toString());
        diary.setLocation(mLocation.getText().toString());
        if (mUpdateTime.isChecked())
            diary.setDate(TimeHelper.getTimeRightNow());
        else
            diary.setDate(mStringDate);
        DiaryHelper.updateDiary(getApplicationContext(), diary);
    }

    //Mendapatkan data dari edittext, lalu create diary baru
    void create() {
        Diary diary = new Diary();
        diary.setTitle(mTitle.getText().toString());
        diary.setContent(mContent.getText().toString());
        diary.setLocation(mLocation.getText().toString());
        diary.setDate(TimeHelper.getTimeRightNow());
        DiaryHelper.addDiary(getApplicationContext(), diary);
    }

    //Menampilkan actionbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if (mStringTag.equals("update"))
            inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    //Respon untuk actionbar menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            DiaryHelper.deleteAllDiary(getApplicationContext());
            setResult(RESULT_OK);
            finish();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }
        return false;
    }
}
