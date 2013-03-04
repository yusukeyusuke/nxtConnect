package com.example.nxtconnect;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class NxtConnectActivity extends Activity implements OnClickListener {

	private final static String TAG = "NXT"; 
	private final static int REQUEST_ENABLE_BLUETOOTH = 1; 
	Button mActionButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nxt_connect);
		mActionButton = (Button)findViewById(R.id.button1);
		mActionButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nxt_connect, menu);
		return true;
	}

	private void toast(String message){
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	
	
	@Override
	public void onClick(View arg0) {
		Log.d(TAG,"onClick!!!");
		BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
		if(bta == null){
			Toast.makeText(this, "BluetoothAdapter not found", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}else{
			if(!bta.isEnabled()){
				toast("Bluetooth is DISABLED!!!");
				Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			    startActivityForResult(enableIntent, REQUEST_ENABLE_BLUETOOTH);
			}
		}
		
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_ENABLE_BLUETOOTH:
			// When the request to enable Bluetooth returns
			if (resultCode == Activity.RESULT_OK) {
				// Bluetooth is now enabled.
				toast("Bluetooth NOW ENABLED!!!");
			} else {
				// User did not enable Bluetooth or an error occured
				Log.d(TAG, "BT not enabled");
				toast("Bluetooth NOT enabled...");
				finish();
			}
		}
	}

}
