package com.hotteststudio.model;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hotteststudio.epubreader.R;

public class BookChapterAdapter extends BaseAdapter {
	public ArrayList<BookChapter> arrBookChapter;
	public Context mContext;
	public LayoutInflater inflater;
	
	public BookChapterAdapter(Context _mContext, ArrayList<BookChapter> arr) {
		mContext = _mContext;
		arrBookChapter = arr;
		inflater = LayoutInflater.from(mContext);
		Log.d("chap size", ">> " + arrBookChapter.size());
	}
	
	@Override
	public int getCount() {
		return arrBookChapter.size();
	}

	@Override
	public Object getItem(int position) {
		return arrBookChapter.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listchapter, null);
			holder = new ViewHolder();
			convertView.setTag(holder);
			holder.tvChapterTitle=(TextView)convertView.findViewById(R.id.tv_chapter_title);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		BookChapter entry = arrBookChapter.get(position);
		if (entry != null) {
			holder.tvChapterTitle.setText(entry.chapTitle);
		}
		return convertView;
	}
	
	private static class ViewHolder {
		TextView tvChapterTitle;
	}
}
