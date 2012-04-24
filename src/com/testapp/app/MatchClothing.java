package com.testapp.app;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.testapp.app.Gallery.ImageAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchClothing extends Activity
{
//	private DataManipulator dm;
//	private GridView grid;
	private TextView et;
//
//
//	ArrayList<String> names = new ArrayList<String>();
//	ArrayList<Integer> ids = new ArrayList<Integer>();
//	ArrayList<Drawable> images = new ArrayList<Drawable>();
//	
//	List<Hashtable> dataHashTable = new ArrayList<Hashtable>();
//	List<Hashtable> hashtable = null;
//	ImageAdapter imageAdapter;
//
//	int textlength=0;
//
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match);
		et = (TextView) findViewById(R.id.TextView01);
		et.setText("Coming soon!");
	}
	

//		dm = new DataManipulator(this);
//
//		grid = (GridView) findViewById(R.id.myGrid);
//		et = (EditText) findViewById(R.id.EditText01);
//		
//
//		hashtable = dm.selectHashtable();
//		
//		for(Hashtable table : hashtable){
//			byte[] bytes = (byte[]) table.get("picture");
//	       	//String name = (String) row.get("name");
//	       	String temp_id = (String) table.get("id");
//	       	int id = Integer.parseInt(temp_id);
//	       	int h = 60; // height in pixels
//	       	int w = 60; // width in pixels    
//	       	Bitmap largeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//	       	Bitmap scaled = Bitmap.createScaledBitmap(largeBitmap, h, w, true);
//	       	Drawable drw = new BitmapDrawable(scaled);
//	       	images.add(drw);
//        	ids.add(id);
//			names.add((String)table.get("name"));
//		}
//
//		grid.setAdapter(new ImageAdapter(this));
//
//		et.addTextChangedListener(new TextWatcher() {
//				public void afterTextChanged(Editable s)
//				{
//				}
//				public void beforeTextChanged(CharSequence s,
//					int start, int count, int after)
//				{
//				}
//
//				public void onTextChanged(CharSequence s,
//					int start, int before, int count)
//				{
//					names.clear();
//					images.clear();
//					hashtable = dm.selectHashtable();
//
//					for(Hashtable table : hashtable){
//						if (s.length() <= 0 && 
//								((String)table.get("name")).indexOf((String)s) != 0) {
//							byte[] bytes = (byte[]) table.get("picture");
//					       	//String name = (String) row.get("name");
//					       	String temp_id = (String) table.get("id");
//					       	int id = Integer.parseInt(temp_id);
//					       	int h = 60; // height in pixels
//					       	int w = 60; // width in pixels    
//					       	Bitmap largeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//					       	Bitmap scaled = Bitmap.createScaledBitmap(largeBitmap, h, w, true);
//					       	Drawable drw = new BitmapDrawable(scaled);
//					       	images.add(drw);
//				        	ids.add(id);
//							names.add((String)table.get("name"));
//						}
//					}
//					
//					imageAdapter.notifyDataSetChanged();
//				}
//			});
//	}
//
//	public class ImageAdapter extends BaseAdapter {
//        public ImageAdapter(Context c) {
//                mContext = c;
//        }
//        public int getCount() {
//                return images.size();
//        }
//        public Object getItem(int position) {
//                return position;
//        }
//        public long getItemId(int position) {
//                return position;
//        }
//        public View getView(int position, View convertView, ViewGroup parent) {
//           ImageView imageView;
//           if(!images.isEmpty()){
//                if (convertView == null) {
//                   imageView = new ImageView(mContext);
//                   imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
//                   imageView.setAdjustViewBounds(false);
//                   imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//                   imageView.setPadding(0, 0, 0, 0);
//                   //Set the imageViews ID to the images ID from the database
//                   int location = ids.get(position);
//                   imageView.setId(location);
//                } else {
//                   imageView = (ImageView) convertView;
//                }
//
//                imageView.setImageDrawable(images.get(position));
//            }
//           else{
//        	   imageView = null;
//           }
//            return imageView;
//        	
//        }
//        private Context mContext;
//    }
}