package frgp.utn.edu.com.volquetes.conexion;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.entidad.*;

public class DataMainActivityListarBorrarReserva extends AsyncTask<String, Void, String> {
    private Context context;
    Reserva reserva = new Reserva();
    int band = 0;
    final ListView list_reserva;
    final ArrayList list_id_reserva = null ;

    Button btn_Baja;
    private ProgressDialog dialog;
    //LayoutInflater imagen_retiro =  LayoutInflater.from(context);
    //final View vista = imagen_retiro.inflate(R.layout.imagen_finalizar_reserva, null);

    public DataMainActivityListarBorrarReserva(Context context, ListView listaCliente) {
        this.context = context;
        this.list_reserva = listaCliente;
        dialog = new ProgressDialog(context);
    }
    int cantReg = 0;
    ArrayList<String> Coleccionn = new ArrayList<String>();
    ArrayAdapter<String> adaptador;

    ArrayList<String> ArrayIDReserva = new ArrayList<String>();
    ArrayList<String> ArrayCodVolquete = new ArrayList<String>();
    ArrayList<String> ArrayIDVolquete = new ArrayList<String>();

    //@Override
    protected String doInBackground(String... urls) {
        String response = "";
        String codigo_cliente = urls[0];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            ///Valido si existe el Cliente que voy a registrar:
            Statement st1 = con.createStatement();


            if (codigo_cliente == null){
                ResultSet rs1 = st1.executeQuery("SELECT r.idReserva, r.fechaEntrega, c.nombreCliente, c.codCliente, v.codigoVolquete, v.idvolquete FROM `reservas` r,`clientes` c , `volquetes` v where fechaRetiro is null and r.idcliente = c.idcliente and r.idvolquete =v.idvolquete");
                while(rs1.next()) {
                    band=1;
                    Coleccionn.add("Cliente: "+rs1.getString("nombreCliente")+" ("+rs1.getString("codCliente") +")"+"\nCódigo Volquete: "+rs1.getString("codigoVolquete") +"\nFecha Entrega :"+ rs1.getDate("fechaEntrega"));
                    ArrayIDReserva.add(rs1.getString("idReserva"));
                    ArrayCodVolquete.add(rs1.getString("codigoVolquete"));
                    ArrayIDVolquete.add(rs1.getString("idVolquete"));

                }
            }else{
                // ResultSet rs1 = st1.executeQuery("SELECT * FROM `reservas` where idCliente like '%"+codigo_cliente+"%' and fechaRetiro is null");
                ResultSet rs1 = st1.executeQuery("SELECT r.idReserva, r.fechaEntrega, c.nombreCliente, c.codCliente, v.codigoVolquete, v.idvolquete FROM `reservas` r,`clientes` c , `volquetes` v where fechaRetiro is null and r.idcliente = c.idcliente and r.idvolquete =v.idvolquete and c.codCliente like '%"+codigo_cliente+"%'");

                while(rs1.next()) {
                    band=1;
                    Coleccionn.add("Cliente: "+rs1.getString("nombreCliente")+" ("+rs1.getString("codCliente") +")"+"\nCódigo Volquete: "+rs1.getString("codigoVolquete") +"\nFecha Entrega :"+ rs1.getDate("fechaEntrega"));
                    ArrayIDReserva.add(rs1.getString("idReserva"));
                    ArrayCodVolquete.add(rs1.getString("codigoVolquete"));
                    ArrayIDVolquete.add(rs1.getString("idVolquete"));
                }
            }


            ///Valido si no existen registros para realizar el Insert
            if(band==1){
                response = "Carga exitosa";
            }

        } catch (Exception e) {
            e.printStackTrace();
            response = "Carga no exitosa";
        }

        return response;
    }


    protected void onPreExecute() {
        dialog.show();
        dialog.setContentView(R.layout.progress_dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }


    //@Override
    protected void onPostExecute(String response) {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        LayoutInflater imagen_retiro =  LayoutInflater.from(context);
        final View vista = imagen_retiro.inflate(R.layout.imagen_finalizar_reserva, null);

        //Valido si el usuario no existio y se registro correctamente
        if(band == 1 && response == "Carga exitosa") {
            adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Coleccionn);
            list_reserva.setAdapter(adaptador);
            list_reserva.clearChoices();

            list_reserva.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView adapterView, View view, int i, long l) {

                    System.out.println(ArrayIDReserva.get(i));
                    final String id_reserva = ArrayIDReserva.get(i);
                    final String id_volquete = ArrayIDVolquete.get(i);
                    final int es = i;
                    System.out.println(id_reserva);
                    System.out.println(id_volquete);

                    AlertDialog.Builder alerta = new AlertDialog.Builder(context);
                    alerta.setMessage("¿Desea borrar la reserva del volquete: " +ArrayCodVolquete.get(i)+ "?")
                            .setCancelable(false)
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new DataMainActivityBorrarReserva(context).execute(id_reserva.toString(), id_volquete.toString());
                                    Coleccionn.remove(es);
                                    list_reserva.setAdapter(null);
                                    adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Coleccionn);
                                    list_reserva.setAdapter(adaptador);
                                    list_reserva.deferNotifyDataSetChanged();

                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();

                                }
                            });

                    if (vista.getParent()!= null){
                        ((ViewGroup)vista.getParent()).removeView(vista);
                    }
                    AlertDialog titulo = alerta.create();
                    titulo.setTitle("Finalizar Reserva" );
                    titulo.setView(vista);
                    titulo.show();
                }
            });

        }else if(band ==0){
            Toast.makeText(context, "El codigo de cliente ingresado no existe", Toast.LENGTH_SHORT).show();
        }







    }




}
