package com.asriputridga.diary2.Adapter.ViewHolder;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.asriputridga.diary2.Adapter.DiaryAdapter;
import com.asriputridga.diary2.Database.DiaryHelper;
import com.asriputridga.diary2.Model.Diary;
import com.asriputridga.diary2.R;

import static android.app.Activity.RESULT_OK;

public class DiaryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mTitle, mContent, mLocDate;
    private int mId;
    private FloatingActionButton mFab, mFab2;
    private DiaryAdapter.OnItemClickListener mOnItemClickListener;
    private DiaryAdapter.OnFabClickListener mOnFabClickListener;
    private Diary mDiary;

    public DiaryViewHolder(View itemView, DiaryAdapter.OnItemClickListener onItemClickListener,
                           DiaryAdapter.OnFabClickListener onFabClickListener) {
        super(itemView);
        mOnItemClickListener = onItemClickListener;
        mOnFabClickListener = onFabClickListener;
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mContent = (TextView) itemView.findViewById(R.id.content);
        mLocDate = (TextView) itemView.findViewById(R.id.loc_date);
        mFab = (FloatingActionButton) itemView.findViewById(R.id.fab);
        mFab.setOnClickListener(this);
//        mFab2 = (FloatingActionButton) itemView.findViewById(R.id.fab2);
//        mFab2.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }

    public void setData(Diary diary) {
        mDiary = diary;
        mTitle.setText(diary.getTitle());
        mContent.setText((diary.getContent().length() < 75) ? diary.getContent():diary.getContent().substring(0,74) + "...");
        mLocDate.setText(diary.getLocation() + ", " + diary.getDate());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            mOnFabClickListener.onFabCliCk(mDiary);}
//        } else if(v.getId() == R.id.fab2){
//
//            //DiaryHelper.deleteDiary(getApplicationContext(), mId);
//        }
        else {
            mOnItemClickListener.onItemClick(mDiary);
        }
    }
}
