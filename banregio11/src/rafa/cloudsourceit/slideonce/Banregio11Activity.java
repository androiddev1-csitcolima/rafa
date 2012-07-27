package rafa.cloudsourceit.slideonce;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Banregio11Activity extends Activity {
    
	protected Button btnPrueba;
	protected TextView txtPrueba;
	protected TextView lista2;
	protected ListView lista;
	static String resultado;
	
	/** Called when the activity is first created. */
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.saldosymovimientos_once);
        
        btnPrueba = (Button)findViewById(R.id.btn_prueba);
        txtPrueba = (TextView)findViewById(R.id.txt_prueba);
        
        btnPrueba.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String url = "http://api.geonames.org/earthquakesJSON?north=44.1&south=-9.9&east=-22.4&west=55.2&username=demo";
				JSONObject json = null;
				
				ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
				json = getJSONfromURL(url);
				
				try{
					JSONArray earthquakes = json.getJSONArray("earthquakes");
					for(int i=0;i<earthquakes.length();i++){
						HashMap<String, String> map = new HashMap<String, String>();
						JSONObject e = earthquakes.getJSONObject(i);
						map.put("id", String.valueOf(i));
						map.put("name", "Earthquake name:" + e.getString("eqid"));
						map.put("magnitude", "Magnitude" + e.getString("magnitude"));
						mylist.add(map);
					}
				}catch(JSONException e){
					Log.e("log_tag", "Error parsing data "+e.toString());
				}
				
				
				
				txtPrueba.setText(resultado);
				
			}
		});
        
    }
    
    public static JSONObject getJSONfromURL (String url){
    	//Iniciar
    	InputStream is = null;
    	String result = "";
    	JSONObject jArray = null;
    	
    	//http post
    	try {
    		HttpClient httpclient = new DefaultHttpClient();
    		HttpPost httppost = new HttpPost(url);
    		HttpResponse response = httpclient.execute(httppost);
    		HttpEntity entity = response.getEntity();
    		is = entity.getContent();
    	} catch (Exception e) {
    		Log.e("log_tag", "Error in http connection "+e.toString());
    	}
    	
    	//convertir a string
    	try {
    		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),8);
    		StringBuilder sb = new StringBuilder();
    		String line = null;
    		while ((line = reader.readLine()) != null) {
    			sb.append(line + "\n");
    		}
    		is.close();
    		result=sb.toString();
    		resultado = sb.toString();
    	} catch(Exception e){
    		Log.e("log_tag","Error converting result"+e.toString());
    	}
    	
    	//almacenar el string en un JSONObject
    	try {
    		jArray = new JSONObject(result);
    	} catch(Exception e){
    		Log.e("log_tag","Error converting result"+e.toString());
    	}
    	return jArray;
    	
    }
    
    
}