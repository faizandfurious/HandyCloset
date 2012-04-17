package com.testapp.app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class Gallery extends Activity {
	
	
	   
   DataManipulator dm;
   
   List<byte[]> list = new ArrayList<byte[]>();
   
   
   List<Hashtable> dataHashTable = new ArrayList<Hashtable>();
   List<Hashtable> hashtable = null;
   List<Drawable> images = new ArrayList<Drawable>();
   
	   
   @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        GridView g = (GridView) findViewById(R.id.myGrid);
        g.setAdapter(new ImageAdapter(this));
        g.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
              Toast.makeText(Gallery.this, "" + position, Toast.LENGTH_SHORT).show();
           }
        });

        dm = new DataManipulator(this);
        hashtable = dm.selectHashtable();
        
        for(Hashtable table : hashtable){
        	byte[] bytes = (byte[]) table.get("picture");
        	int h = 60; // height in pixels
        	int w = 60; // width in pixels    
        	Bitmap largeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        	Bitmap scaled = Bitmap.createScaledBitmap(largeBitmap, h, w, true);
        	Drawable drw = new BitmapDrawable(scaled);
        	images.add(drw);
        }
   }
   
   public void onResume() {
	   super.onResume();
       hashtable = dm.selectHashtable();
       for(Hashtable table : hashtable){
       	byte[] bytes = (byte[]) table.get("picture");
       	ByteArrayInputStream is = new ByteArrayInputStream(bytes);
    	int h = 60; // height in pixels
    	int w = 60; // width in pixels    
    	Bitmap largeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    	Bitmap scaled = Bitmap.createScaledBitmap(largeBitmap, h, w, true);
    	Drawable drw = new BitmapDrawable(scaled);
       	for(int i = 0; i < images.size(); i++)
       	{
       		if(images.get(i).equals(drw))
       		{
       				
       		}
       		else{
       			images.add(drw);
       		}
       	}
       }
   }
   
   @Override
   protected void onPause() {
   super.onPause();

   unbindDrawables(findViewById(R.id.myGrid));
   System.gc();
   }

   private void unbindDrawables(View view) {
       if (view.getBackground() != null) {
       view.getBackground().setCallback(null);
       }
       if (view instanceof ViewGroup) {
           for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
           unbindDrawables(((ViewGroup) view).getChildAt(i));
           }
       try {
           ((ViewGroup) view).removeAllViews();
    	   }
    	   catch (UnsupportedOperationException mayHappen) {
    	   // AdapterViews, ListViews and potentially other ViewGroups donÕt support the removeAllViews operation
    	   }
       }
   }
   
   public class ImageAdapter extends BaseAdapter {
        public ImageAdapter(Context c) {
                mContext = c;
        }
        public int getCount() {
                return images.size();
        }
        public Object getItem(int position) {
                return position;
        }
        public long getItemId(int position) {
                return position;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
           ImageView imageView;
           if(!images.isEmpty()){
                if (convertView == null) {
                   imageView = new ImageView(mContext);
                   imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
                   imageView.setAdjustViewBounds(false);
                   imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                   imageView.setPadding(0, 0, 0, 0);
                } else {
                   imageView = (ImageView) convertView;
                }

                	imageView.setImageDrawable(images.get(position));
            }
           else{
        	   imageView = null;
           }
            return imageView;
        	
        }
        private Context mContext;
    }
}