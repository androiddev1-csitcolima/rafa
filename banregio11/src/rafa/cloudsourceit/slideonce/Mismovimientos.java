package rafa.cloudsourceit.slideonce;

import java.util.ArrayList;

import org.json.JSONObject;

import rafa.cloudsourceit.expandListView.Adapter.MiExpandableAdapter;
import rafa.cloudsourceit.expandListView.Adapter.MismovimientosAdapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Mismovimientos extends Activity {
	
	protected Button btnbloqueados;
	private ArrayList<ArrayList<String>> grupos;
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	     setContentView(R.layout.movimientos);
	     
	     //Por si envie parametros
	     Intent i = getIntent();
	     System.out.println(i.getStringExtra("saldo_bloqueado"));
	     if (i.getStringExtra("saldo_bloqueado").equals("no")){
	    	 LinearLayout menu1 = (LinearLayout) findViewById(R.id.menu1);
	    	 LinearLayout menu2 = (LinearLayout) findViewById(R.id.menu2);
	    	 menu1.setVisibility(View.GONE);
	    	 menu2.setVisibility(View.VISIBLE);
	     } else if (i.getStringExtra("saldo_bloqueado").equals("si")){
	    	 LinearLayout menu1 = (LinearLayout) findViewById(R.id.menu1);
	    	 LinearLayout menu2 = (LinearLayout) findViewById(R.id.menu2);
	    	 menu1.setVisibility(View.VISIBLE);
	    	 menu2.setVisibility(View.GONE);
	    	 Button menubloq = (Button) findViewById(R.id.btnsaldo_bloqueado);
	    	 menubloq.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent myintent = new Intent(Mismovimientos.this, Saldo_bloqueado.class);
						//Por si tengo que enviar algun valor
						//myintent.putExtra("nombre_variable2", variable2);
						try{
							Mismovimientos.this.startActivity(myintent);
						}catch (ActivityNotFoundException e){
							Log.e("log_tag","No la encontro"+e.toString());
						}
												
					}
				});
	    }
	    //variable2 = i.getStringExtra("nombre_variable2");
	     
	     Button menumiscuentas = (Button) findViewById(R.id.btnmenu_mis_cuentas);
    	 menumiscuentas.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent myintent = new Intent(Mismovimientos.this, Banregio11Activity.class);
					//Por si tengo que enviar algun valor
					//myintent.putExtra("nombre_variable2", variable2);
					try{
						Mismovimientos.this.startActivity(myintent);
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
	  	MismovimientosAdapter adaptador = new MismovimientosAdapter(this, grupos);
	  	l.setAdapter(adaptador);
	    
	}
	
	//Por el momento las lleno asi
	public void llenarListas(){
    	grupos = new ArrayList<ArrayList<String>>();
    	ArrayList<String> hijo = new ArrayList<String>();
    	grupos.add(new ArrayList<String>(){{add("Retiro");add("24-abr");add("$200.00");add("$0.00");add("$1000.00");}});
    	grupos.add(new ArrayList<String>(){{add("Deposito");add("23-abr");add("$0.00");add("$500.00");add("$1200.00");}});
    	grupos.add(new ArrayList<String>(){{add("23-abr");add("22-abr");add("$0.00");add("$200.00");add("$1000.00");}});
    	
    }

}
