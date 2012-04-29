package com.testapp.app;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.testapp.app.Gallery.ImageAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import android.content.res.TypedArray;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

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
	Integer[] pics = {
    		R.drawable.antartica1,
    		R.drawable.antartica2,
    		R.drawable.antartica3,
    		R.drawable.antartica4,
    		R.drawable.antartica5,
    		R.drawable.antartica6,
    		R.drawable.antartica7,
    		R.drawable.antartica8,
    		R.drawable.antartica9,
    		R.drawable.antartica10
    };
    //ImageView imageView;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match);
		//et = (TextView) findViewById(R.id.TextView01);
		//et.setText("Coming soon!");
		Gallery ga = (Gallery)findViewById(R.id.Gallery01);
        ga.setAdapter(new ImageAdapter(this));
        
        Gallery gal = (Gallery)findViewById(R.id.Gallery02);
        gal.setAdapter(new ImageAdapter(this));
	}
	
    public class ImageAdapter extends BaseAdapter {

    	private Context ctx;
    	int imageBackground;
    	
    	public ImageAdapter(Context c) {
			ctx = c;
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
			ta.recycle();
		}

		@Override
    	public int getCount() {
    		
    		return pics.length;
    	}

    	@Override
    	public Object getItem(int arg0) {
    		
    		return arg0;
    	}

    	@Override
    	public long getItemId(int arg0) {
    		
    		return arg0;
    	}

    	@Override
    	public View getView(int arg0, View arg1, ViewGroup arg2) {
    		ImageView iv = new ImageView(ctx);
    		iv.setImageResource(pics[arg0]);
    		iv.setScaleType(ImageView.ScaleType.FIT_XY);
    		iv.setLayoutParams(new Gallery.LayoutParams(300,300));
    		iv.setBackgroundResource(imageBackground);
    		return iv;
    	}

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
	
    public void onBackPressed() {

     }
	
}