package com.testapp.app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class Gallery extends Activity {
	
	TextView textView;
	EditText et;
	ImageAdapter imageAdapter;
	GridView g;
	   
   DataManipulator dm;
   
   List<Integer> ids = new ArrayList<Integer>();
   List<byte[]> list = new ArrayList<byte[]>();
   List<String> names = new ArrayList<String>();
   int count;
   
   

   List<Hashtable> listHashTable = new ArrayList<Hashtable>();
   List<Drawable> images = new ArrayList<Drawable>();
   
	   
   @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        g = (GridView) findViewById(R.id.myGrid);
		et = (EditText) findViewById(R.id.editText1);
        imageAdapter = new ImageAdapter(this);
        g.setAdapter(imageAdapter);
        g.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        	   int location = v.getId();
        	   Toast.makeText(Gallery.this, "" + position + ", " + location, Toast.LENGTH_SHORT).show();
   				Intent launchView = new Intent(Gallery.this, ViewClothing.class);
   			
   				launchView.putExtra("id", location);
   				startActivityForResult(launchView, 1);
           }
        });

        dm = new DataManipulator(this);
        
        
 	   List<Integer> newIds = dm.getIds();
 	   count = newIds.size();

 	   if(!images.isEmpty())
 	   {
 		   for(int i = 0; i < ids.size(); i++)
 		   {
 			   if(!newIds.contains(ids.get(i)))
 			   {
 				   images.remove(i);
 			   }
 		   }
 		   for(int i = 0; i < newIds.size(); i++)
 		   {
 			   if(!ids.contains(newIds.get(i)))
 			   {
 				   Drawable temp = dm.getPicture(i);
 				   if(temp != null){
 					   images.add(temp);
 					   ids.add(newIds.get(i));  
 				   }
 			   }
 		   }
 			imageAdapter.notifyDataSetChanged();
 			g.setAdapter(imageAdapter);
 	   }
        
        
		et.addTextChangedListener(new TextWatcher() {
			public void afterTextChanged(Editable s)
			{
				listHashTable = dm.searchQuery(s.toString());
				if(listHashTable.size() > 0){
					names.clear();
					images.clear();
					ids.clear();
					
					for(Hashtable table : listHashTable){
							byte[] bytes = (byte[]) table.get("picture");
					       	String name = (String) table.get("name");
					       	String temp_id = (String) table.get("id");
					       	int id = Integer.parseInt(temp_id);
					       	int h = 60; // height in pixels
					       	int w = 60; // width in pixels    
					       	Bitmap largeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
					       	Bitmap scaled = Bitmap.createScaledBitmap(largeBitmap, h, w, true);
					       	Drawable drw = new BitmapDrawable(scaled);
					       	images.add(drw);
				        	ids.add(id);
							names.add((String)table.get("name"));
						}

					imageAdapter.notifyDataSetChanged();
					g.setAdapter(imageAdapter);
					}
			}
			public void beforeTextChanged(CharSequence s,
				int start, int count, int after)
			{
			}

			public void onTextChanged(CharSequence s,
				int start, int before, int count)
			{

			}
		});
   }
   
   
   public void onResume() {
	   super.onResume();
	   List<Integer> newIds = dm.getIds();

	   if(!images.isEmpty())
	   {
		   for(int i = 0; i < ids.size(); i++)
		   {
			   if(!newIds.contains(ids.get(i)))
			   {
				   images.remove(i);
			   }
		   }
		   for(int i = 0; i < newIds.size(); i++)
		   {
			   if(!ids.contains(newIds.get(i)))
			   {
				   Drawable temp = dm.getPicture(i);
				   if(temp != null){
					   images.add(temp);
					   ids.add(newIds.get(i));  
				   }
			   }
		   }
			imageAdapter.notifyDataSetChanged();
			g.setAdapter(imageAdapter);
		   
	       count = listHashTable.size();
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
                   //Set the imageViews ID to the images ID from the database
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