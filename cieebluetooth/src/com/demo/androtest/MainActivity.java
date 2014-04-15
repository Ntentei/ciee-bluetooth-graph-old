package com.demo.androtest;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.demo.androtest.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


public class MainActivity extends Activity {
	
	private BluetoothAdapter mBluetoothAdapter;
	TextView text;
	ScrollView scroll;
	String xData;
	String xxxData;
	
	
	private static int countLines(String str){
		   String[] lines = str.split("\r\n|\r|\n");
		   return  lines.length;
		}

	
	protected void updateConsole(String newLine) {
		String aText = (String) text.getText();
    	
    	int lineNumber = countLines(aText);
    	if(lineNumber >= 150)
    		aText = aText.substring(aText.indexOf('\n')+1);
    	
    	aText = aText + "\n" + newLine;
        text.setText(aText);
		scroll.fullScroll(View.FOCUS_DOWN);  
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		scroll = (ScrollView) findViewById(R.id.scroll1);
		text = (TextView) findViewById(R.id.textView1);
	
        //final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        
        if (mBluetoothAdapter == null) {
        	Log.e("STATE", "NO BLUETOOTH DEVICE ON-BOARD");
        	updateConsole( "NO BLUETOOTH DEVICE ON-BOARD") ;
            return;
        }
        else
        {

        	Log.e("STATE", "Bluetooth initiated");
        	updateConsole("Bluetooth initiated") ;
        	
        	if (!mBluetoothAdapter.isEnabled())
        	{
        		Log.e("STATE", "Bluetooth not enabled");
        		updateConsole("Bluetooth not enabled") ;
        		
        	    if(!mBluetoothAdapter.isEnabled())
        	    {
        	        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        	        startActivity(i);
        	    }
        	}
        	
        	{

        		Log.e("STATE", "Bluetooth enabled");
        		updateConsole("Bluetooth enabled");
        		       		
        	    Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        	        		
        		// If there are paired devices
        		if (pairedDevices.size() > 0) {
        	
        			Log.e("STATE", "Paired device(s) found");
        			updateConsole("Paired device(s) found");
        			
        			BluetoothDevice xDevice = null;
        			
        		    for (BluetoothDevice device : pairedDevices) {
        		        
        		        setTitle(device.getName() + " | " + device.getAddress());
        		        xDevice = device;
        		    }
        		    
        		    BluetoothSocket btSocket = null;
					try {
						btSocket = xDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
					} catch (IOException e) {
						e.printStackTrace();
					}

					Log.e("STATE", "Socket created");
					updateConsole("Socket created");
        		    
        		    try {
						btSocket.connect();
					} catch (IOException e) {
						e.printStackTrace();
					}
        		    
        		    Log.e("STATE", "Socket connected");
        		    updateConsole("Socket connected");
        		    
        		    InputStream input = null;
					try {
						input = btSocket.getInputStream();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					Log.e("STATE", "InputStream acquired");
					updateConsole("InputStream acquired");
					
		            int read = 0;
		            byte[] singleChar = new byte[1];
		            
		            
		            do
		            {
		                try
		                {
		                	read = input.read(singleChar, 0, 1);
		                	String data = new String(singleChar, 0, read);
		                	
		                	Log.e("METADATA", data);
		                	updateConsole(data);
		                	
		                	if(data.equals("~"))
		                	{
		                		xData = "";
		                		
		                		Log.e("XXX", "**************BEGIN****************");
		                		
			                	read = input.read(singleChar, 0, 1);
			                	data = new String(singleChar, 0, read);
			                	xData += data;
			                	
			                	read = input.read(singleChar, 0, 1);
			                	data = new String(singleChar, 0, read);
			                	xData += data;
			                	
			                	read = input.read(singleChar, 0, 1);
			                	data = new String(singleChar, 0, read);
			                	xData += data;
			                	
			                	read = input.read(singleChar, 0, 1);
			                	data = new String(singleChar, 0, read);
			                	xData += data;
			                	
			                	read = input.read(singleChar, 0, 1);
			                	data = new String(singleChar, 0, read);
			                	xData += data;
			                	
			                	read = input.read(singleChar, 0, 1);
			                	data = new String(singleChar, 0, read);
			                	xData += data;
			                	
			                	read = input.read(singleChar, 0, 1);
			                	data = new String(singleChar, 0, read);
			                	xData += data;
			                	
			                	read = input.read(singleChar, 0, 1);
			                	data = new String(singleChar, 0, read);
			                	xData += data;
		                		
			                	read = input.read(singleChar, 0, 1);
			                	data = new String(singleChar, 0, read);
			                	xData += data;
			                	
			                	xxxData = xData;
			                	
					            //send data to database
					            Thread thread = new Thread()
					            {
					                @Override
					                public void run() {
					                    
							            Log.e("MSG", "Start HTTP GET in a new thread");
							            updateConsole("Sending to Cloud Server...");						            
					                	
										HttpClient client = new DefaultHttpClient();  
										String getURL = "http://192.168.1.104/newevent.php?deviceid=6635&value=" + xxxData;
										HttpGet get = new HttpGet(getURL);
										try {
											HttpResponse responseGet = client.execute(get);
										    HttpEntity resEntityGet = responseGet.getEntity();  
										    if (resEntityGet != null) {  
										        String response = EntityUtils.toString(resEntityGet);
										        Log.e("GET RESPONSE", response);
										    }
										} catch (ClientProtocolException e) {
											e.printStackTrace();
										} catch (IOException e) {
											e.printStackTrace();
										}
					                }
					            };
					            
					            thread.start();
			                	
					            //GUI update
			                	Log.e("DATA", xData); //sample output: 01810xxxx
			                	updateConsole(xData + "<--------------- Data");
			                	
			                	Log.e("XXX", "**************END******************");
			                				                	

		                	}

		                }
		                catch(Exception ex)
		                {
		                    read = -1;
		                }
		            }
		            while (read > 0);
		         
        		}
        		else
        		{
        			Log.e("STATE", "No paired device(s)");
        			updateConsole("No paired device(s)");
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

