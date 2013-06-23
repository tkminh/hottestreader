package com.hotteststudio.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hotteststudio.epubreader.R;
import com.hotteststudio.util.XCommon;

public class BookListAdapter extends BaseAdapter {

	public ArrayList<EpubInfo> arrEpubs;
	public Context mContext;
	public LayoutInflater inflater;
	
	public BookListAdapter(Context _mContext, ArrayList<EpubInfo> arr) {
		mContext = _mContext;
		arrEpubs = arr;
		inflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return arrEpubs.size();
	}

	@Override
	public Object getItem(int position) {
		return arrEpubs.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listitem, null);
			holder = new ViewHolder();
			convertView.setTag(holder);
			holder.imageView = (ImageView)convertView.findViewById(R.id.img_bookList_item_thumnail);
			holder.tvTitle=(TextView)convertView.findViewById(R.id.tv_booklist_item_title);
			holder.tvAuthor=(TextView)convertView.findViewById(R.id.tv_booklist_item_author);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		EpubInfo entry = arrEpubs.get(position);
		try {
			
			String temp = entry.path.substring(entry.path.lastIndexOf("/")+1);
			temp = XCommon.deAccent(temp.replace(" ", ""));
			String fileName = XCommon.getRootPath() + temp.replaceAll(".epub", "")  + "/Images/" + entry.coverImg;
			//String fileName = XCommon.getRootPath() + temp.replaceAll(".epub", "")  + "/" + entry.coverImg;
			Bitmap bitmap = decodeFile(new File(fileName));
			holder.imageView.setImageBitmap(bitmap);
			//holder.imageView.setImageBitmap(BitmapFactory.decodeFile(fileName));
			holder.tvAuthor.setText(entry.author);
			if (entry.title.length()>0) {
				holder.tvTitle.setText(entry.title);
			} else {
				holder.tvTitle.setText(entry.path.lastIndexOf("/")+1);
			}
			
			Log.d("fak","file: " + fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return convertView;
	}

	private static class ViewHolder {
		ImageView imageView;
		TextView tvTitle;
		TextView tvAuthor;
	}
	
	private Bitmap decodeFile(File f){
	    try {
	        //Decode image size
	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        BitmapFactory.decodeStream(new FileInputStream(f),null,o);

	        //The new size we want to scale to
	        final int REQUIRED_SIZE=70;

	        //Find the correct scale value. It should be the power of 2.
	        int scale=1;
	        while(o.outWidth/scale/2>=REQUIRED_SIZE && o.outHeight/scale/2>=REQUIRED_SIZE)
	            scale*=2;

	        //Decode with inSampleSize
	        BitmapFactory.Options o2 = new BitmapFactory.Options();
	        o2.inSampleSize=scale;
	        return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
	    } catch (FileNotFoundException e) {}
	    return null;
	}
}
