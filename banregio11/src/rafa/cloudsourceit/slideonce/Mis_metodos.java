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

import android.content.Intent;
import android.util.Log;

public class Mis_metodos {
	
	public JSONObject getJSONfromURL (String url){
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
    	
    	//Convertir a String
    	try {
    		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),8);
    		StringBuilder sb = new StringBuilder();
    		String line = null;
    		while ((line = reader.readLine()) != null) {
    			sb.append(line + "\n");
    		}
    		is.close();
    		result=sb.toString();
    	} catch(Exception e){
    		Log.e("log_tag","Error convirtiendo a string"+e.toString());
    	}
    	
    	//Almacenar el string en un JSONObject
    	try {
    		jArray = new JSONObject(result);
    	} catch(Exception e){
    		Log.e("log_tag","Error convirtiendo a jArray"+e.toString());
    	}
    	return jArray;
    	
    }
	
	public ArrayList<HashMap<String, String>> getList (JSONObject json){
		//Iniciar la lista donde ira el resultado
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		
		//Obtengo los resultados y los pongo en la lista
		try{
			JSONArray start = json.getJSONObject("response")
					.getJSONArray("resultset")
					.getJSONObject(0).getJSONObject("query")
					.getJSONArray("data");
						
			for(int i=0;i<start.length();i++){
				HashMap<String, String> map = new HashMap<String, String>();
				JSONObject e = start.getJSONObject(i);
				map.put("id", String.valueOf(i));
				map.put("cor_cuenta", "Cuenta: " + e.getString("cor_cuenta"));
				map.put("cor_alias", "Alias: " + e.getString("cor_alias"));
				mylist.add(map);	
			}
		}catch(JSONException e){
			Log.e("log_tag", "Error parsing data "+e.toString());
		}
		return mylist;
	}

}
