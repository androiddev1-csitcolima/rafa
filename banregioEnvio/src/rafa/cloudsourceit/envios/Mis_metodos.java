package rafa.cloudsourceit.envios;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

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
}