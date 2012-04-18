package com.testapp.app;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class Gallery extends Activity implements View.OnClickListener{
	
	ImageView imageView;
	
	
	   
   DataManipulator dm;
   
   List<byte[]> list = new ArrayList<byte[]>();
   
   
   List<Hashtable> dataHashTable = new ArrayList<Hashtable>();
   List<Hashtable> hashtable = null;
   
	   
   @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        
        GridView g = (GridView) findViewById(R.id.myGrid);
        g.setAdapter(new ImageAdapter(this));
        
        imageView = (ImageView)findViewById(R.id.imageView1);
        g.setOnItemClickListener(new OnItemClickListener() {
        	@Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
              Toast.makeText(getBaseContext(), "" + position, Toast.LENGTH_SHORT).show();
              	imageView.setImageResource(mThumbIds[position]);
           }
        });

        dm = new DataManipulator(this);
        hashtable = dm.selectHashtable();
        
        for(Hashtable table : hashtable){
        	byte[] bytes = (byte[]) table.get("Picture");
        	list.add(bytes);
        }
   }
   public class ImageAdapter extends BaseAdapter {
	   
	   private Context ctx;
	   int imageBackground;
	   
        public ImageAdapter(Context c) {
        	ctx = c;
        	TypedArray ta = obtainStyledAttributes(R.styleable.Gallery);
            imageBackground = ta.getResourceId(R.styleable.Gallery_android_galleryItemBackground, 1);
            ta.recycle();
        }
        
        @Override
        public int getCount() {
                return mThumbIds.length;
        }
        
        @Override
        public Object getItem(int position) {
                return position;
        }
        
        @Override
        public long getItemId(int position) {
                return position;
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if (convertView == null) {
                   imageView = new ImageView(mContext);
                   imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
                   imageView.setAdjustViewBounds(false);
                   imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                   imageView.setPadding(0, 0, 0, 0);
                } else {
                   imageView = (ImageView) convertView;
                }
                imageView.setImageResource(mThumbIds[position]);
//                InputStream is = new ByteArrayInputStream(list.get(position));
//                Bitmap bmp = BitmapFactory.decodeStream(is);
//                imageView.setImageBitmap(bmp);
                return imageView;
        }
        private Context mContext;
    }

}