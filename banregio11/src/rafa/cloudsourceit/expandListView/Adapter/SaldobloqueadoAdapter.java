package rafa.cloudsourceit.expandListView.Adapter;

import java.util.ArrayList;

import rafa.cloudsourceit.slideonce.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SaldobloqueadoAdapter extends BaseAdapter {
	private final Context context;
	private final ArrayList<ArrayList<String>> grupos;
	
	
	public SaldobloqueadoAdapter(Context context, ArrayList<ArrayList<String>> grupos) {
		this.context = context;
        this.grupos = grupos;
	}


	public int getCount() {
		// TODO Auto-generated method stub
		return grupos.size();
	}


	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return grupos.get(position);
	}


	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public ArrayList<ArrayList<String>> getGroup(int groupPosition) {
    	ArrayList<ArrayList<String>> vector = new ArrayList<ArrayList<String>>();
    	vector.add(new ArrayList<String>());
    	vector.get(0).add(grupos.get(groupPosition).get(0));
    	vector.get(0).add(grupos.get(groupPosition).get(1));
    	vector.get(0).add(grupos.get(groupPosition).get(2));
        return vector;
    }

	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		String concepto = (String) ((ArrayList<ArrayList<String>>)getGroup(position)).get(0).get(0);
    	String fecha = (String) ((ArrayList<ArrayList<String>>)getGroup(position)).get(0).get(1);
    	String saldo = (String) ((ArrayList<ArrayList<String>>)getGroup(position)).get(0).get(2);
    	
    	if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_saldobloqueado, null);
        }
    	
    	//Le pongo los valores al xml
    	TextView fechatxt = (TextView) convertView.findViewById(R.id.tvfecha);
    	TextView conceptotxt = (TextView) convertView.findViewById(R.id.tvconcepto);
        TextView saldotxt = (TextView) convertView.findViewById(R.id.tvsaldo);
        conceptotxt.setText(concepto);
        fechatxt.setText(fecha);
        saldotxt.setText(saldo);
        
        //Vevuelvo el view con los valores
        return convertView;   	
    	
	}

}