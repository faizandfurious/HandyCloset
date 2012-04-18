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
import android.content.Intent;
import android.util.Log;

public class Gallery extends Activity {
	
	TextView textView;
	   
   DataManipulator dm;
   
   List<Integer> ids = new ArrayList<Integer>();
   List<byte[]> list = new ArrayList<byte[]>();
   List<String> names = new ArrayList<String>();
   
   

   List<Hashtable> listHashTable = new ArrayList<Hashtable>();
   List<Drawable> images = new ArrayList<Drawable>();
   
	   
   @Override
   public void onCreate(Bundle savedInstanceState) {
        
	   super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        GridView g = (GridView) findViewById(R.id.myGrid);
        
        g.setAdapter(new ImageAdapter(this));
        
        g.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        	   
        	viewClothes(v);
        	   
        	   // int location = v.getId();
        	   
        	   // Toast.makeText(Gallery.this, "" + position + ", " + location, Toast.LENGTH_SHORT).show();
           }

           private void viewClothes(View v) {
			// TODO Auto-generated method stub
			int position = v.getId();
			
			Intent launchView = new Intent(Gallery.this, ViewClothing.class);
			
			launchView.putExtra("id", position);
			startActivityForResult(launchView, 1);
           }
        });
        
        
        dm = new DataManipulator(this);
        listHashTable = dm.selectHashtable();
        
        for(Hashtable row : listHashTable){
        	byte[] bytes = (byte[]) row.get("picture");
        	//String name = (String) row.get("name");
        	String temp_id = (String) row.get("id");
        	int id = Integer.parseInt(temp_id);
        	int h = 60; // height in pixels
        	int w = 60; // width in pixels    
        	Bitmap largeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        	Bitmap scaled = Bitmap.createScaledBitmap(largeBitmap, h, w, true);
        	Drawable drw = new BitmapDrawable(scaled);
        	images.add(drw);
        	ids.add(id);
        	//names.add(name);
        }
   }
   
   
   public void onStart() {
	   super.onStart();
//       listHashTable = dm.selectHashtable();
//       for(Hashtable row : listHashTable){
//       	byte[] bytes = (byte[]) row.get("picture");
//       	ByteArrayInputStream is = new ByteArrayInputStream(bytes);
//    	int h = 60; // height in pixels
//    	int w = 60; // width in pixels    
//    	Bitmap largeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//    	Bitmap scaled = Bitmap.createScaledBitmap(largeBitmap, h, w, true);
//    	Drawable drw = new BitmapDrawable(scaled);
//       	for(int i = 0; i < images.size(); i++)
//       	{
//       		if(images.get(i).equals(drw))
//       		{
//       				
//       		}
//       		else{
//       			images.add(drw);
//       		}
//       	}
//       }
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
                   int location = ids.get(position);
                   imageView.setId(location);
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
    
   @Override
   protected void onActivityResult (int requestCode, int resultCode, Intent data) {
   	super.onActivityResult(requestCode, resultCode, data);
   	if(resultCode==RESULT_OK && requestCode==1) {
   		int id = data.getExtras().getInt("returnInt");
   		textView.setText(id);
   	}
   	
	}
}