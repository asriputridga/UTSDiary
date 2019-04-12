package com.asriputridga.diary2.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.asriputridga.diary2.Adapter.ViewHolder.DiaryViewHolder;
import com.asriputridga.diary2.Model.Diary;
import com.asriputridga.diary2.R;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryViewHolder> {
    private List<Diary> mList;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private OnFabClickListener mOnFabClickListener;

    public DiaryAdapter(List<Diary> list, Context context, OnItemClickListener onItemClickListener, OnFabClickListener onFabClickListener) {
        mList = list;
        mContext = context;
        mOnItemClickListener = onItemClickListener;
        mOnFabClickListener = onFabClickListener;
    }

    public DiaryAdapter(Context context, OnItemClickListener onItemClickListener, OnFabClickListener onFabClickListener) {
        mContext = context;
        mOnItemClickListener = onItemClickListener;
        mOnFabClickListener = onFabClickListener;
    }

    public void setData(List<Diary> list){
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiaryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.custom_list_diary,
                parent, false), mOnItemClickListener, mOnFabClickListener);
    }

    @Override
    public void onBindViewHolder(DiaryViewHolder holder, int position) {
        holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        if(mList == null){
            return 0;
        }else{
            return mList.size();
        }

    }

    public interface OnItemClickListener{
        void onItemClick(Diary diary);
    }

    public interface OnFabClickListener{
        void onFabCliCk(Diary diary);
    }
}
