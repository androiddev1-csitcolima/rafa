package rafa.cloudsourceit.slideonce;

import java.util.ArrayList;

import org.json.JSONObject;

import rafa.cloudsourceit.expandListView.Adapter.SaldobloqueadoAdapter;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class Saldo_bloqueado extends Activity {
	
	private ArrayList<ArrayList<String>> grupos;
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.saldo_bloqueado); 
	     
	     Button menubloq = (Button) findViewById(R.id.btnmovimientos);
    	 menubloq.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent myintent = new Intent(Saldo_bloqueado.this, Mismovimientos.class);
					//Por si tengo que enviar algun valor
					myintent.putExtra("saldo_bloqueado", "si");
					//myintent.putExtra("nombre_variable2", variable2);
					try{
						Saldo_bloqueado.this.startActivity(myintent);
					}catch (ActivityNotFoundException e){
						Log.e("log_tag","No la encontro"+e.toString());
					}
											
				}
			});
    	 
    	 Button menumiscuentas = (Button) findViewById(R.id.btnmenu_mis_cuentas);
    	 menumiscuentas.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent myintent = new Intent(Saldo_bloqueado.this, Banregio11Activity.class);
					//Por si tengo que enviar algun valor
					//myintent.putExtra("nombre_variable2", variable2);
					try{
						Saldo_bloqueado.this.startActivity(myintent);
					}catch (ActivityNotFoundException e){
						Log.e("log_tag","No la encontro"+e.toString());
					}
											
				}
			});
	     
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
		 ListView l = (ListView) findViewById(R.id.list);
		 SaldobloqueadoAdapter adaptador = new SaldobloqueadoAdapter(this, grupos);
		 l.setAdapter(adaptador);
	          
	 }
	
	//Por el momento las lleno asi
		public void llenarListas(){
	    	grupos = new ArrayList<ArrayList<String>>();
	    	ArrayList<String> hijo = new ArrayList<String>();
	    	grupos.add(new ArrayList<String>(){{add("24-abr");add("Lalala");add("$300.00");}});
	    	grupos.add(new ArrayList<String>(){{add("23-abr");add("Lelele");add("$200.00");}});
	    	grupos.add(new ArrayList<String>(){{add("22-abr");add("Lilili");add("$100.00");}});
	    	
	    }

}