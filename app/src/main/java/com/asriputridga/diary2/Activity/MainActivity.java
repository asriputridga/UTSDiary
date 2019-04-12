package com.asriputridga.diary2.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.asriputridga.diary2.Adapter.DiaryAdapter;
import com.asriputridga.diary2.Database.DiaryHelper;
import com.asriputridga.diary2.Model.Diary;
import com.asriputridga.diary2.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DiaryAdapter.OnItemClickListener, DiaryAdapter.OnFabClickListener {

    private RecyclerView mRecyclerView;
    private DiaryAdapter mDiaryAdapter;
    private List<Diary> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Menggunakan for result karena membutuhkan feedback dari DiaryActivity
                Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
                intent.putExtra("tag", "create");
                startActivityForResult(intent, 100);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mDiaryAdapter = new DiaryAdapter(MainActivity.this, this, this);
        mRecyclerView.setAdapter(mDiaryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //Update list dengan asynctask agar tidak mengganggu thread utama
        new LoadDatabase().execute();
    }

    //Sebagai respon dari DiaryActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==100){
            if(resultCode==RESULT_OK){
                new LoadDatabase().execute();
            }

        }
    }

    //Respon dari fab button yang terdapat dalam item dari recyclerview
    @Override
    public void onFabCliCk(Diary diary) {
        Intent intent = new Intent(MainActivity.this, DiaryActivity.class);
        intent.putExtra("tag", "update");
        intent.putExtra("id", diary.getId());
        startActivityForResult(intent, 100);
    }

    //Respon dari item yang terdapat dalam item dari recyclerview
    @Override
    public void onItemClick(Diary diary) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

        intent.putExtra("title", diary.getTitle());
        intent.putExtra("content", diary.getContent());
        intent.putExtra("location", diary.getLocation());
        intent.putExtra("date", diary.getDate());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_delete){
            DiaryHelper.deleteAllDiary(getApplicationContext());
            mDiaryAdapter.setData(null);
            return true;
        }
        return false;
    }

    //Class untuk update data list
    class LoadDatabase extends AsyncTask<Void, Void ,List<Diary>> {

        @Override
        protected List<Diary> doInBackground(Void... params) {
            return DiaryHelper.getDiaries(MainActivity.this);
        }

        @Override
        protected void onPostExecute(List<Diary> diaries) {
            mDiaryAdapter.setData(diaries);
        }
    }
}
