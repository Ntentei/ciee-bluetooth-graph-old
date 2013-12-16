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
        	setTitle("blue tooth adapter is null");
            //finish();
            return;
        }
        
        
        
        else
        {
        	setTitle("blue tooth initiated");
        	
        	if (!mBluetoothAdapter.isEnabled())
        	{
        		setTitle("BT not enabled");
        		
        	    if(!mBluetoothAdapter.isEnabled())
        	    {
        	        Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        	        startActivity(i);
        	    }
        	}
        	
        	{
        		setTitle("BT enabled");
        		
        		/*
        		final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        		    public void onReceive(Context context, Intent intent) {
        		        String action = intent.getAction();
        		        // When discovery finds a device
        		        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
        		            // Get the BluetoothDevice object from the Intent
        		            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        		            // Add the name and address to an array adapter to show in a ListView
        		            setTitle(device.getName());
        		        }
        		    }
        		};

        		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        		registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
        		*/
        	
        		//mBluetoothAdapter.startDiscovery();
        		
        		
        		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        	
        		
        		// If there are paired devices
        		if (pairedDevices.size() > 0) {
        	
        			for (Iterator<BluetoothDevice> iterator = pairedDevices.iterator(); iterator
						.hasNext();) {
        			}
        		}
        		else
        		{
        			setTitle("paried device = 0");
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
