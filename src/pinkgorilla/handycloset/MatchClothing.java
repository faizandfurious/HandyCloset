package pinkgorilla.handycloset;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import pinkgorilla.handycloset.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchClothing extends Activity
{
	private DataManipulator dm;
//	private GridView grid;
	private TextView et;
	private boolean topLocked = false;
	private boolean bottomLocked = false;
	private Button topLock;
	private Button bottomLock;
	private ImageView topLeftArrowImageView;
	private ImageView topRightArrowImageView;
	private int topSelectedImagePosition = 0;
	private int bottomSelectedImagePosition = 0;
	private ImageView bottomLeftArrowImageView;
	private ImageView bottomRightArrowImageView;
	private TopAdapter topImageAdapter;
	private BottomAdapter bottomImageAdapter;
	private ExtendedGallery topGal;
	private ExtendedGallery bottomGal;

	
   List<Integer> topIds = new ArrayList<Integer>();
   List<byte[]> topList = new ArrayList<byte[]>();
   List<String> topNames = new ArrayList<String>();
   
   List<Integer> bottomIds = new ArrayList<Integer>();
   List<byte[]> bottomList = new ArrayList<byte[]>();
   List<String> bottomNames = new ArrayList<String>();
	
   List<Hashtable> topListHashTable = new ArrayList<Hashtable>();
   List<Drawable> topImages = new ArrayList<Drawable>();
   List<Hashtable> bottomListHashTable = new ArrayList<Hashtable>();
   List<Drawable> bottomImages = new ArrayList<Drawable>();
   

	
    //ImageView imageView;
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match);

        setupUI();
        getData();
	}
	
	private void getData(){
		 dm = new DataManipulator(this);
		 topListHashTable = dm.selectTops();
	        
	        for(Hashtable row : topListHashTable){
	        	byte[] bytes = (byte[]) row.get("picture");
	        	//String name = (String) row.get("name");
	        	String temp_id = (String) row.get("id");
	        	int id = Integer.parseInt(temp_id);
	        	int h = 360; // height in pixels
	        	int w = 360; // width in pixels    
	        	Bitmap largeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	        	Bitmap scaled = Bitmap.createScaledBitmap(largeBitmap, h, w, true);
	        	Drawable drw = new BitmapDrawable(scaled);
	        	topImages.add(drw);
	        	topIds.add(id);
	        }
	        
	        bottomListHashTable = dm.selectBottoms();

	        for(Hashtable row : bottomListHashTable){
	        	byte[] bytes = (byte[]) row.get("picture");
	        	//String name = (String) row.get("name");
	        	String temp_id = (String) row.get("id");
	        	int id = Integer.parseInt(temp_id);
	        	int h = 360; // height in pixels
	        	int w = 360; // width in pixels    
	        	Bitmap largeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	        	Bitmap scaled = Bitmap.createScaledBitmap(largeBitmap, h, w, true);
	        	Drawable drw = new BitmapDrawable(scaled);
	        	bottomImages.add(drw);
	        	bottomIds.add(id);
	        }
	}
	
	private void setupUI(){
		
		topImageAdapter = new TopAdapter(this);
		bottomImageAdapter = new BottomAdapter(this);
		topGal = (ExtendedGallery) findViewById(R.id.Gallery01);
        topGal.setScrollingEnabled(true); // disable scrolling
        topGal.setAdapter(topImageAdapter);
        
        bottomGal = (ExtendedGallery) findViewById(R.id.Gallery02);
        bottomGal.setScrollingEnabled(true); // enable scrolling
        bottomGal.setAdapter(bottomImageAdapter);
        
        
        //Set up lock/unlock abilities
        topLock = (Button)findViewById(R.id.lockbutton01);
        bottomLock = (Button)findViewById(R.id.lockbutton02);
        
        topLock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(topLocked){
					topLock.setBackgroundResource(R.drawable.unlock);
					topGal.setScrollingEnabled(true); // enable scrolling
				}
				else{
					topLock.setBackgroundResource(R.drawable.lock);
					topGal.setScrollingEnabled(false); // disable scrolling
					
				}
				topLocked = !topLocked;
				
			}
		});
        
        bottomLock.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(bottomLocked){
					bottomLock.setBackgroundResource(R.drawable.unlock);
					bottomGal.setScrollingEnabled(true); // enable scrolling
				}
				else{
					bottomLock.setBackgroundResource(R.drawable.lock);
					bottomGal.setScrollingEnabled(false); // disable scrolling
					
				}
				bottomLocked = !bottomLocked;
				
			}
		});
        
        //Create controls for top gallery
        topLeftArrowImageView = (ImageView) findViewById(R.id.left_arrow_imageview01);
		topRightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview01);
		

		topLeftArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!topLocked && topSelectedImagePosition > 0) {
					--topSelectedImagePosition;

				}

				topGal.setSelection(topSelectedImagePosition, false);
			}
		});

		topRightArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!topLocked && topSelectedImagePosition < topImages.size() - 1) {
					++topSelectedImagePosition;

				}

				topGal.setSelection(topSelectedImagePosition, false);

			}
		});
		
		//Create controls for bottom gallery
        bottomLeftArrowImageView = (ImageView) findViewById(R.id.left_arrow_imageview02);
        bottomRightArrowImageView = (ImageView) findViewById(R.id.right_arrow_imageview02);

        bottomLeftArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!bottomLocked && bottomSelectedImagePosition > 0) {
					--bottomSelectedImagePosition;

				}

				bottomGal.setSelection(bottomSelectedImagePosition, false);
			}
		});

        bottomRightArrowImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!bottomLocked && bottomSelectedImagePosition < bottomImages.size() - 1) {
					++bottomSelectedImagePosition;

				}

				bottomGal.setSelection(bottomSelectedImagePosition, false);

			}
		});
        
	}
	
	
	
	
	
	   public void onResume() {
		   super.onResume();
		   List<Integer> newTopIds = dm.getTopIds();
		   List<Integer> newBottomIds = dm.getBottomIds();

		   if(!topImages.isEmpty())
		   {
			   for(int i = 0; i < topIds.size(); i++)
			   {
				   if(!newTopIds.contains(topIds.get(i)))
				   {
					   topImages.remove(i);
				   }
			   }
			   for(int i = 0; i < newTopIds.size(); i++)
			   {
				   if(!topIds.contains(newTopIds.get(i)))
				   {
					   Drawable temp = dm.getPicture(newTopIds.get(i));
					   if(temp != null){
						   topImages.add(temp);
						   topIds.add(newTopIds.get(i));  
					   }
				   }
			   }
				topImageAdapter.notifyDataSetChanged();
				topGal.setAdapter(topImageAdapter);
			   
		   }
		   
		   if(!bottomImages.isEmpty())
		   {
			   for(int i = 0; i < bottomIds.size(); i++)
			   {
				   if(!newBottomIds.contains(bottomIds.get(i)))
				   {
					   bottomImages.remove(i);
				   }
			   }
			   for(int i = 0; i < newBottomIds.size(); i++)
			   {
				   if(!bottomIds.contains(newBottomIds.get(i)))
				   {
					   Drawable temp = dm.getPicture(newBottomIds.get(i));
					   if(temp != null){
						   bottomImages.add(temp);
						   bottomIds.add(newBottomIds.get(i));  
					   }
				   }
			   }
				bottomImageAdapter.notifyDataSetChanged();
				bottomGal.setAdapter(bottomImageAdapter);
			   
		   }
	   }
	   
	   @Override
	   protected void onPause() {
	   super.onPause();

	   unbindDrawables(findViewById(R.id.Gallery01));
	   unbindDrawables(findViewById(R.id.Gallery02));
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

	    	   // AdapterViews, ListViews and potentially other ViewGroups don't support the removeAllViews operation

	    	   }
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
		//Return 1 if empty, to show default picture.
    	public int getCount() {
    		
    		return Math.max(1, topImages.size());
    	}

    	@Override
    	public Object getItem(int arg0) {
    		
    		return arg0;
    	}

    	@Override
    	public long getItemId(int arg0) {
    		
    		return arg0;
    	}

    	
//    	Use this to connect to database
      public View getView(int position, View convertView, ViewGroup parent) {
      ImageView imageView = new ImageView(ctx);
      if(!topImages.isEmpty()){
           if (convertView == null) {
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
    
    public class BottomAdapter extends BaseAdapter {

    	private Context ctx;
    	int imageBackground;
    	
    	public BottomAdapter(Context c) {
			ctx = c;
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
			ta.recycle();
		}

		@Override
		//Return 1 if empty, to show default picture.
    	public int getCount() {
    		
    		return Math.max(1, bottomImages.size());
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
    	      ImageView imageView = new ImageView(ctx);
    	      if(!bottomImages.isEmpty()){
    	           if (convertView == null) {

    	              //Set the imageViews ID to the images ID from the database
    	              int location = bottomIds.get(position);
    	              imageView.setId(location);
    	           } else {
    	              imageView = (ImageView) convertView;
    	           }

    	           imageView.setImageDrawable(bottomImages.get(position));
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