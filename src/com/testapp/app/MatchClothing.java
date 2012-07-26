package com.testapp.app;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchClothing extends Activity
{
//	private DataManipulator dm;
//	private GridView grid;
	private TextView et;

	
   List<Integer> topIds = new ArrayList<Integer>();
   List<byte[]> topList = new ArrayList<byte[]>();
   List<String> topNames = new ArrayList<String>();
	
   List<Hashtable> topListHashTable = new ArrayList<Hashtable>();
   List<Drawable> topImages = new ArrayList<Drawable>();
   
   
	Integer[] pics = {
    		R.drawable.clothes1,
    		R.drawable.clothes2,
    		R.drawable.clothes3,
    		R.drawable.clothes4,
    		R.drawable.clothes5,
    		R.drawable.clothes6,
    		R.drawable.clothes7,
    		R.drawable.clothes8,
    		R.drawable.clothes9,
    		R.drawable.clothes10
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
    public class TopAdapter extends BaseAdapter {

    	private Context ctx;
    	int imageBackground;
    	
    	public TopAdapter(Context c) {
			ctx = c;
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
			ta.recycle();
		}

		@Override
    	public int getCount() {
    		
    		return topImages.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = null;
            if(!topImages.isEmpty()){
                 if (convertView == null) {
                    imageView = new ImageView(ctx);
                    imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
                    imageView.setAdjustViewBounds(false);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    imageView.setPadding(0, 0, 0, 0);

                    //Set the imageViews ID to the images ID from the database

                    int location = topIds.get(position);
                    imageView.setId(location);
                 } else {
                    imageView = (ImageView) convertView;
                 }

                 imageView.setImageDrawable(topImages.get(position));
             }
            else{
         	   Drawable d = ctx.getResources().getDrawable(R.drawable.add_how);
         	   imageView.setImageDrawable(d);
            }
             return imageView;
         	
         }

    }

	
    public void onBackPressed() {
    	Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(intent);
     }
	
}