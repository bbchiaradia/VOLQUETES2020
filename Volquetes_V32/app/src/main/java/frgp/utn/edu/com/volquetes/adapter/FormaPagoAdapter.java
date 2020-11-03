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

public class FormaPagoAdapter extends ArrayAdapter<FormaPago> {
    public FormaPagoAdapter(Context context, List<FormaPago> objetos) {
        super(context, R.layout.list_template_formpago , objetos);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_template_formpago, null);

        TextView tvid = (TextView) item.findViewById(R.id.id_forma_pago);
        TextView tvdni = (TextView) item.findViewById(R.id.descripcion_forma_pago);


        tvid.setText(getItem(position).getIdFormaPago()+"");
        tvdni.setText(getItem(position).getDescripcionFormaPago()+"");

        return item;
    }
}

