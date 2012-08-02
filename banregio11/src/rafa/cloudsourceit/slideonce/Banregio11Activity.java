package rafa.cloudsourceit.slideonce;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ListView;
import rafa.cloudsourceit.expandListView.Adapter.MiExpandableAdapter;
import rafa.cloudsourceit.slideonce.Mis_metodos;

public class Banregio11Activity extends Activity {
    
	protected ListView lista;
	static String resultado;
    private ArrayList<ArrayList<String>> grupos;
	//private ArrayList<ArrayList<ArrayList<String>>> hijos;
	
	/** Called when the activity is first created. */
    @Override
    
    public void onCreate(Bundle savedInstanceState) {
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.miscuentas);
        
      //Creo una variable para poder usar mis metodos
		Mis_metodos get = new Mis_metodos();
		
		//La URL a donde pedire el JSON
		String url = "http://banregio.cloudsourceithosting.com/CloudWS/coms/COMTDCDETMOV.php";
		
		//Creo y obtengo el JSON
		JSONObject json = null;
		json = get.getJSONfromURL(url);
		
		//Lleno las listas con el contenido del JSON
		//llenarListas(json);
		llenarListas();
		
		//Inicializo el ExpandableList y el Adapter y lo aplico en mi activity
		ExpandableListView l = (ExpandableListView) findViewById(R.id.ExpList);
		MiExpandableAdapter adaptador = new MiExpandableAdapter(this, grupos);
		l.setAdapter(adaptador);
    
    }
    
    //HASTA QUE TENGA EL JSON BIEN LO LLENARE FICTICIAMENTE
    public void llenarListas(){
    	grupos = new ArrayList<ArrayList<String>>();
    	ArrayList<String> hijo = new ArrayList<String>();
    	grupos.add(new ArrayList<String>(){{add("Alias 1");add("1234567890");add("Visa Platino");add("$100.00");add("1234567890123456");add("$10.00");}});
    	grupos.add(new ArrayList<String>(){{add("Alias 2");add("0987654321");add("Visa XD");add("$200.00");add("0987654321098765");add("$20.00");}});
    	grupos.add(new ArrayList<String>(){{add("Alias 3");add("5647382910");add("Visa D=");add("$300.00");add("56473839201564738");add("$0.00");}});
    	
    }
    
    
    /*
    //Metodo para poner los datos y ponerlos en la lista
    public void llenarListas (JSONObject json){
		//Iniciar la lista donde ira el resultado
    	grupos = new ArrayList<String>();
    	hijos = new ArrayList<ArrayList<ArrayList<String>>>();
		
		//Obtengo los resultados y los pongo en la lista
		try{
			JSONArray start = json.getJSONObject("response")
					.getJSONArray("resultset")
					.getJSONObject(0).getJSONObject("query")
					.getJSONArray("data");
			
			System.out.println(start.toString());
						
			for(int i=0;i<start.length();i++){
				JSONObject e = start.getJSONObject(i);
				System.out.println(e.getString("cor_cuenta"));
				grupos.add(e.getString("cor_cuenta"));
				//grupos.add("Grupo 1");
				hijos.add(new ArrayList<ArrayList<String>>());
				hijos.get(i).add(new ArrayList<String>());
				hijos.get(i).get(0).add(e.getString("cor_alias"));	
			}
		}catch(JSONException e){
			Log.e("log_tag", "Error parsing data "+e.toString());
		}
	}
	*/
	
        
}