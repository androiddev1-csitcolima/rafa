package rafa.cloudsourceit.expandListView.Adapter;

import java.util.ArrayList;

import rafa.cloudsourceit.slideonce.Mismovimientos;
import rafa.cloudsourceit.slideonce.R;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MiExpandableAdapter extends BaseExpandableListAdapter {
   	 
    	//Inicializo mis variables	
		private ArrayList<ArrayList<String>> groups; //La lista que tiene los grupos
        //private ArrayList<ArrayList<ArrayList<ArrayList<String>>>> children;
    	private Context context; //El context para modificar la activity
    	private boolean bloqueado; //Para saber si tengo saldo bloqueado o no
 
    	//Mando a llamarlo y recibo el context que es la activity y la lista que tiene los elementos
    	public MiExpandableAdapter(Context context, ArrayList<ArrayList<String>> groups) {
    		//Tomo los valores que me pasaron y los pongo en mis variables
            this.context = context;
            this.groups = groups;
            //this.children = children;
        }
 
    	//Por si algun item no debiera mostrar los hijos, pero todos deben asi que devuelvo true
    	//@Override
        public boolean areAllItemsEnabled()
        {
            return true;
        }
 
 
        //Obtengo el ArrayList con los valores del hijo, si fuera un solo valor podria pedir un String en lugar de ArrayList
        //@Override
        public ArrayList<ArrayList<String>> getChild(int groupPosition, int childPosition) {
        	ArrayList<ArrayList<String>> vector = new ArrayList<ArrayList<String>>();       	
        	vector.add(new ArrayList<String>());
        	vector.get(0).add(groups.get(groupPosition).get(4));
        	vector.get(0).add(groups.get(groupPosition).get(5));
            return vector;           
        }
 
        //Obtengo la posicion del hijo
        //@Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
 
        //Meto el xml del hijo
        //@Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,View convertView, ViewGroup parent) {
        	
        	//Obtengo los valores
        	String clabe = (String) ((ArrayList<ArrayList<String>>)getChild(groupPosition, childPosition)).get(0).get(0);
        	String saldobloq = (String) ((ArrayList<ArrayList<String>>)getChild(groupPosition, childPosition)).get(0).get(1);
        	
        	//Si no hay un view ya hecho lo creo (para eso crea un espacio y lo llena con el xml del hijo)
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expandlist_child_item, null);
            }
 
            //Mando a llamar los elementos del xml y los lleno con los valores
            TextView clabetxt = (TextView) convertView.findViewById(R.id.tvChild);
            TextView saldobloqtxt = (TextView) convertView.findViewById(R.id.tvsaldo_bloqueado);
            clabetxt.setText(clabe);
            saldobloqtxt.setText(saldobloq);
            
            //Aqui le doy a los botones la capacidad de ser presionados
            Button btnirmovimientos = (Button) convertView.findViewById(R.id.btn_ir_movimientos);
            //Veo si tengo saldo bloqueado, de esto dependera cual menu aparecerá en Mismovimientos            
            if (saldobloq.equals("$0.00")){
            	btnirmovimientos.setTag("0");
	    	} else {
	    		btnirmovimientos.setTag("1");
	    	}
            btnirmovimientos.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					
					//La nueva activity que mandare a llamar cuando presione el boton
					Intent myintent = new Intent(context, Mismovimientos.class);
					
					//Envio el valor de si tengo saldo bloqueado o no
					if (v.getTag().equals("0")){
						myintent.putExtra("saldo_bloqueado", "no");
					}else {
						myintent.putExtra("saldo_bloqueado", "si");
					}
					
					//Busco la activity y si no la encuentro no exploto
					try{
						context.startActivity(myintent);
					}catch (ActivityNotFoundException e){
						Log.e("log_tag","No la encontro"+e.toString());
					}	
				}
			});
            
            //Devuelvo la vista para que la coloque donde debe (en el grupo correspondiente)
            return convertView;
        }
 
        //Obtengo cantidad de hijos que tiene el grupo (en mi caso como solo tengo un hijo siempre devuelvo 1)
        //@Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }
 
        //Ahora obtengo el ArrayList con los valores del grupo (si fuera un solo valor podria pedir que fuera String)
        //@Override
        public ArrayList<ArrayList<String>> getGroup(int groupPosition) {
        	ArrayList<ArrayList<String>> vector = new ArrayList<ArrayList<String>>();
        	vector.add(new ArrayList<String>());
        	vector.get(0).add(groups.get(groupPosition).get(0));
        	vector.get(0).add(groups.get(groupPosition).get(1));
        	vector.get(0).add(groups.get(groupPosition).get(2));
        	vector.get(0).add(groups.get(groupPosition).get(3));
            return vector;
        }
 
        //Cuento cuantos grupos tengo
        //@Override
        public int getGroupCount() {
            return groups.size();
        }
 
        //Devuelvo la posicion del grupo
        //@Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
 
        //Aqui creo la vista del grupo
        //@Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
 
        	//Obtengo los valores para el grupo
        	String alias = (String) ((ArrayList<ArrayList<String>>)getGroup(groupPosition)).get(0).get(0);
        	String cuenta = (String) ((ArrayList<ArrayList<String>>)getGroup(groupPosition)).get(0).get(1);
        	String tipotarjeta = (String) ((ArrayList<ArrayList<String>>)getGroup(groupPosition)).get(0).get(2);
        	String saldo = (String) ((ArrayList<ArrayList<String>>)getGroup(groupPosition)).get(0).get(3);
 
        	//Si no existe un view lo creo y le doy el estilo del xml para grupos
        	if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.expandlist_group_item, null);
            }
 
        	//Le pongo los valores al xml
            TextView aliastxt = (TextView) convertView.findViewById(R.id.tvGroup);
            TextView cuentatxt = (TextView) convertView.findViewById(R.id.tvCuenta);
            TextView tipotarjetatxt = (TextView) convertView.findViewById(R.id.tvTipo_tarjeta);
            TextView saldotxt = (TextView) convertView.findViewById(R.id.tvSaldo);
            aliastxt.setText(alias);
            cuentatxt.setText(cuenta);
            tipotarjetatxt.setText(tipotarjeta);
            saldotxt.setText(saldo);
            
            //Vevuelvo el view con los valores
            return convertView;
        }
 
        //No tengo idea que es pero creo que es para saber si los IDs no se mueven, no se, la clase me lo pedia para que funcionara xD
        //@Override
        public boolean hasStableIds() {
            return true;
        }
        
        //Para decirle si los hijos pueden ser seleccionados, si le dices que true puedes decirle que pase algo cuando los seleccione (como el onClic de los botones)
        //@Override
        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }        
 
    }
