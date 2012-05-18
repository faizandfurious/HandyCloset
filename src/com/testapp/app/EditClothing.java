package com.testapp.app;

import java.util.Hashtable;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class EditClothing extends Activity implements OnClickListener{
	
	
	int id;
	DataManipulator dm;
    List<Hashtable> listHashTable;
    Hashtable ht;
    private Spinner spinner1;
	   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//reading information passed to this activity
		//Get the intent that started the activity
		Intent i = getIntent();
		
		id = i.getIntExtra("id", -1);
		String intent_id = Integer.toString(id);
		
		
        dm = new DataManipulator(this);

        listHashTable = dm.selectHashtable();
        Hashtable ht = new Hashtable();
        
        for(Hashtable list : listHashTable){
        	String temp_id = (String) list.get("id");
        	if(temp_id.equals(intent_id)){
        		ht = list;
        	}
        }
        
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
                spinner1 = (Spinner) findViewById(R.id.spinner1);
                String myDescription = spinner1.getSelectedItem().toString();
                
                String myEditText1=((TextView) editText1).getText().toString();
                this.dm = new DataManipulator(this);
                dm.update(id, myEditText1,myDescription,0);
                Intent added = new Intent(this, ViewClothing.class);
                added.putExtra("id", id);
   				startActivityForResult(added, 1);

        }
    }
    @Override
    public void onBackPressed() {
       System.out.println("onBackPressed Called");
       Intent home = new Intent(this, ViewClothing.class);
       home.putExtra("id", id);
       startActivityForResult(home, 1);
    }

}
