package rafa.cloudsourceit.envios;

import java.util.ArrayList;

import org.json.JSONObject;

import rafa.cloudsourceit.adapters.MiExpandableAdapterEnvios;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

public class MenuEnvioActivity extends Activity {
    /** Called when the activity is first created. */
	protected Button btnenviar;
	protected Button btnpendientes;
	protected Button btnconsulta;
	private ArrayList<ArrayList<String>> grupos;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
    	super.onCreate(savedInstanceState); 
        setContentView(R.layout.menuenvios);
        
        btnenviar = (Button) findViewById(R.id.btnenviar);
        btnpendientes = (Button) findViewById(R.id.btnpendientes);
        btnconsulta = (Button) findViewById(R.id.btnconsulta);
        
        btnenviar.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {				
				//Alineo el presionado al fondo
				RelativeLayout.LayoutParams o = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.altura));
				o.addRule(RelativeLayout.ALIGN_PARENT_TOP, 1);
				btnpendientes.setLayoutParams(o);
				RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.altura));
				p.addRule(RelativeLayout.BELOW, R.id.btnpendientes);
				btnconsulta.setLayoutParams(p);
				RelativeLayout.LayoutParams q = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.altura));
				q.addRule(RelativeLayout.BELOW, R.id.btnconsulta);
				btnenviar.setLayoutParams(q);
				
				//Le pongo el estilo de presionado al... presionado (redundante xD)
				activo(btnenviar, (Drawable) getResources().getDrawable(R.drawable.enviaractivo));
				inactivo(btnpendientes,(Drawable) getResources().getDrawable(R.drawable.pendientesinactivo));
				inactivo(btnconsulta,(Drawable) getResources().getDrawable(R.drawable.consultainactivo));
				
				//Ahora meto el XML debajo (como?)
				ViewGroup parent = (ViewGroup) findViewById(R.id.insertbrainhere);
				View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.enviosbanregio, null);
				
				//Lleno la ExpandableListView de BanRegio
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
				
				//Muestro el xml
				parent.addView(view);
				
				//Inicializo el ExpandableList y el Adapter y lo aplico en mi activity
				ExpandableListView l = (ExpandableListView) findViewById(R.id.explista);
				MiExpandableAdapterEnvios adaptador = new MiExpandableAdapterEnvios(parent.getContext(), grupos);
				l.setAdapter(adaptador);
				
				//Le doy accion al boton de BanRegio
					//Mismo que llenar
				
				//Le doy accion al boton de TEF
					//Esto es de Aaron
				
				//Le doy accion al boton de SPEI
					//Esto es de Aaron
				
			}
		});
         
        btnpendientes.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {				
				//Alineo el presionado al fondo
				RelativeLayout.LayoutParams o = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.altura));
				o.addRule(RelativeLayout.ALIGN_PARENT_TOP, 1);
				btnenviar.setLayoutParams(o);
				RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.altura));
				p.addRule(RelativeLayout.BELOW, R.id.btnenviar);
				btnconsulta.setLayoutParams(p);
				RelativeLayout.LayoutParams q = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.altura));
				q.addRule(RelativeLayout.BELOW, R.id.btnconsulta);
				btnpendientes.setLayoutParams(q);
				
				//Le pongo el estilo de presionado al... presionado (redundante xD)
				activo(btnpendientes, (Drawable) getResources().getDrawable(R.drawable.pendientesactivo));
				inactivo(btnenviar,(Drawable) getResources().getDrawable(R.drawable.enviarinactivo));
				inactivo(btnconsulta,(Drawable) getResources().getDrawable(R.drawable.consultainactivo));
				
			}
		});
        
        btnconsulta.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {				
				//Alineo el presionado al fondo
				RelativeLayout.LayoutParams o = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.altura));
				o.addRule(RelativeLayout.ALIGN_PARENT_TOP, 1);
				btnenviar.setLayoutParams(o);
				RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.altura));
				p.addRule(RelativeLayout.BELOW, R.id.btnenviar);
				btnpendientes.setLayoutParams(p);
				RelativeLayout.LayoutParams q = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.altura));
				q.addRule(RelativeLayout.BELOW, R.id.btnpendientes);
				btnconsulta.setLayoutParams(q);
				
				//Le pongo el estilo de presionado al... presionado (redundante xD)
				activo(btnconsulta, (Drawable) getResources().getDrawable(R.drawable.consultainactivo));
				inactivo(btnpendientes,(Drawable) getResources().getDrawable(R.drawable.pendientesinactivo));
				inactivo(btnenviar,(Drawable) getResources().getDrawable(R.drawable.enviarinactivo));
				
			}
		});
        
    }
    
    public void activo (Button boton, Drawable img){
    	boton.setBackgroundResource(R.drawable.fondo_lista);
		boton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null );
		boton.setTextColor(Color.WHITE);
    }
    public void inactivo (Button boton, Drawable img){
    	boton.setBackgroundResource(R.drawable.btnmenu_inactivo);
		boton.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null );
		boton.setTextColor(Color.GRAY);
    }
  //HASTA QUE TENGA EL JSON BIEN LO LLENARE FICTICIAMENTE
    public void llenarListas(){
    	grupos = new ArrayList<ArrayList<String>>();
    	ArrayList<String> hijo = new ArrayList<String>();
    	grupos.add(new ArrayList<String>(){{add("Alias 1");add("1234567890");add("Visa Platino");add("$100.00");add("Lalala");add("$10.00");add("MRS12345678Z");add("MRS12345678Z");}});
    	grupos.add(new ArrayList<String>(){{add("Alias 2");add("0987654321");add("Visa XD");add("$200.00");add("lelele");add("$20.00");add("MRS12345678X");add("MRS12345678X");}});
    	grupos.add(new ArrayList<String>(){{add("Alias 3");add("5647382910");add("Visa D=");add("$300.00");add("liñlilidsf");add("$0.00");add("MRS12345678Y");add("MRS12345678Y");}});
    	
    }
    
}