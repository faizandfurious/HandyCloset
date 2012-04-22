package com.testapp.app;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;


public class EditClothing extends Activity implements OnClickListener{
	
	
	int id;
	DataManipulator dm;

	   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		
		//reading information passed to this activity
		//Get the intent that started the activity
		Intent i = getIntent();
		
		id = i.getIntExtra("id", -1);
		String intent_id = Integer.toString(id);
		
		
        dm = new DataManipulator(this);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save);
        View add = findViewById(R.id.Button01add);
        add.setOnClickListener(this);
        View home = findViewById(R.id.Button01home);
        home.setOnClickListener(this);        
        
    }
	
    public void onClick(View v){
        switch(v.getId()){
            case R.id.Button01home:
                Intent home = new Intent(this, ViewClothing.class);
   				home.putExtra("id", id);
   				startActivityForResult(home, 1);
   				
            break;
            case R.id.Button01add:
                View editText1 = (EditText) findViewById(R.id.name);
                String myEditText1=((TextView) editText1).getText().toString();
                dm.update(id, myEditText1);
                Intent added = new Intent(this, ViewClothing.class);
                added.putExtra("id", id);
   				startActivityForResult(added, 1);
        }
    }
}
