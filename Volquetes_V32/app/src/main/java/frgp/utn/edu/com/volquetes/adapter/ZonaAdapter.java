package frgp.utn.edu.com.volquetes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.entidad.FormaPago;
import frgp.utn.edu.com.volquetes.entidad.Zona;

public class ZonaAdapter extends ArrayAdapter<Zona> {
    public ZonaAdapter(Context context, List<Zona> objetos) {
        super(context, R.layout.list_template_zona , objetos);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_template_zona, null);

        TextView tvid = (TextView) item.findViewById(R.id.id_zona);
        TextView tvdni = (TextView) item.findViewById(R.id.descripcion_zona);


        tvid.setText(getItem(position).getIdZona()+"");
        tvdni.setText(getItem(position).getNombreZona()+"");

        return item;
    }
}

