package com.demo.androtest;

import java.util.Iterator;
import java.util.Set;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


public class MainActivity extends Activity {
	
	private BluetoothAdapter mBluetoothAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
	
        //final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        
        if (mBluetoothAdapter == null) {
        	setTitle("No bluetooth adaptor on this device");
            return;
        }
        else
        {
        	setTitle("Bluetooth initiated");
        	
        	if (!mBluetoothAdapter.isEnabled())
        	{
        		setTitle("Bluetooth not enabled");
        		
        	    if(!mBluetoothAdapter.isEnabled())
        	    {
        	        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        	        startActivity(i);
        	    }
        	}
        	
        	{
        		setTitle("Bluetooth enabled");
        		       		
        	    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        	        		
        		// If there are paired devices
        		if (pairedDevices.size() > 0) {
        	
        			setTitle("Paired device(s) found");
        			
        		    for (BluetoothDevice device : pairedDevices) {
        		        
        		        setTitle(device.getName() + " | " + device.getAddress());
        		    }
        		}
        		else
        		{
        			setTitle("No paired device(s)");
        		}
        		
        		
        	}
        	
        	
        	
        	
        }
        
       
	}
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

