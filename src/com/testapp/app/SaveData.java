package com.testapp.app;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class SaveData extends Activity implements OnClickListener {  
    private DataManipulator dh;     
    static final int DIALOG_ID = 0;
    
    byte[] bitMapData;
    
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save);
        View add = findViewById(R.id.Button01add);
        add.setOnClickListener(this);
        View home = findViewById(R.id.Button01home);
        home.setOnClickListener(this);   
        
		Intent i = getIntent();
		
		Bundle extras = getIntent().getExtras();
		bitMapData = extras.getByteArray("picture");

    }
    public void onClick(View v){
        switch(v.getId()){
            case R.id.Button01home:
                Intent i = new Intent(this, TabMenu.class);
                startActivity(i);
            break;
            case R.id.Button01add:
                View editText1 = (EditText) findViewById(R.id.name);
                String myEditText1=((TextView) editText1).getText().toString();


                this.dh = new DataManipulator(this);
                this.dh.insert(myEditText1,bitMapData);
                showDialog(DIALOG_ID);
            break;
        }
    }  
    protected final Dialog onCreateDialog(final int id) {
        Dialog dialog = null;
        switch(id) {
            case DIALOG_ID:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Information saved successfully!").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                                SaveData.this.finish();
                        }
                });
                AlertDialog alert = builder.create(); 
                dialog = alert;
                break;
             default:
        }
        return dialog;
    }
}