package com.testapp.app;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener {

	ImageView iv;
	ImageButton ib;
	Button b;
	Intent i;
	final static int cameraData = 0;
	Bitmap bmp;
	DataManipulator dm;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialize();
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		bmp = BitmapFactory.decodeStream(is);

		this.dm = new DataManipulator(this);

	}
	
	private void initialize() {
		iv = (ImageView) findViewById (R.id.ivReturnedPic);
		ib = (ImageButton) findViewById (R.id.ibTakePic);
		b = (Button) findViewById (R.id.bDone);
		ib.setOnClickListener(this);
		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bDone:
			i = new Intent(this, TabMenu.class);
			startActivity(i);
			break;
		case R.id.ibTakePic:
			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, cameraData);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {

			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			iv.setImageBitmap(bmp);

			Bitmap bitmap = bmp;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] bitMapData = stream.toByteArray();
            i = new Intent(this, SaveData.class);
            i.putExtra("picture", bitMapData);
            startActivity(i);
		}
	}

}
