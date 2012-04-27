package com.testapp.app;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ViewClothing extends Activity {
	
	int location;
	DataManipulator dm;
	Hashtable ht;
	static final int DIALOG_ID = 0;
	
	
	List<Hashtable> listHashTable = new ArrayList<Hashtable>();	
	   List<Integer> ids = new ArrayList<Integer>();
	   List<byte[]> list = new ArrayList<byte[]>();
	   List<String> names = new ArrayList<String>();
	   int count;
	   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view);
		
		//reading information passed to this activity
		//Get the intent that started the activity
		Intent i = getIntent();
		
		location = i.getIntExtra("id", -1);
		String intent_id = Integer.toString(location);
		
		
        dm = new DataManipulator(this);
        listHashTable = dm.selectHashtable();
        Hashtable ht = new Hashtable();
        
        for(Hashtable list : listHashTable){
        	String temp_id = (String) list.get("id");
        	if(temp_id.equals(intent_id)){
        		ht = list;
        	}
        }
        String name = (String) ht.get("name");
        byte[] bytes = (byte[]) ht.get("picture");
    	String ht_id = (String) ht.get("id");
    	int id = Integer.parseInt(ht_id);

    	Bitmap largeBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    	Drawable drw = new BitmapDrawable(largeBitmap);
    	
    	ImageView image = (ImageView) findViewById(R.id.imageView1);
    	TextView nameText = (TextView) findViewById(R.id.textView1);
    	TextView idText = (TextView) findViewById(R.id.textView2);
    	image.setImageDrawable(drw);
    	nameText.setText(name);
    	idText.setText(intent_id);
    	
        
	}

	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.view, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.edit: 
			
			Intent editI = new Intent(ViewClothing.this, EditClothing.class);
			editI.putExtra("id", location);
			startActivityForResult(editI, 1);
			
			Toast.makeText(this, "Edit menu", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.delete:
			showDialog(DIALOG_ID);
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	protected final Dialog onCreateDialog(final int id) {
        Dialog dialog = null;
        switch(id) {
            case DIALOG_ID:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to delete?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        	dm.delete(location);
                			Intent launchView = new Intent(ViewClothing.this, TabMenu.class);
                			startActivityForResult(launchView, 1);
                        }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                        }
                });
                AlertDialog alert = builder.create(); 
                dialog = alert;
                break;
             default:
        }
        return dialog;
    }
	
    public void onBackPressed() {
        System.out.println("onBackPressed Called");
        Intent setIntent = new Intent(this, TabMenu.class);
        startActivity(setIntent);
     }
	
	
}