package frgp.utn.edu.com.volquetes.conexion;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.entidad.Reserva;

public class DataMainActivityReporte3ClienteReservasFecha extends AsyncTask<String, Void, String> {
    private Context context;
    Reserva reserva = new Reserva();
    int band = 0;
    final ListView list_reserva;
    final ArrayList list_id_reserva = null ;


    private ProgressDialog dialog;

    Button btn_Baja;


    public DataMainActivityReporte3ClienteReservasFecha(Context context, ListView listaCliente) {
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
        String fecha_desde = urls[0];
        String fecha_hasta = urls[1];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            ///Valido si existe el Cliente que voy a registrar:
            Statement st1 = con.createStatement();
            Statement st2 = con.createStatement();

                ResultSet rs1 = st1.executeQuery("SELECT r.idReserva, r.fechaEntrega, c.nombreCliente, c.codCliente, v.codigoVolquete, v.idvolquete FROM `reservas` r,`clientes` c , `volquetes` v where r.idcliente = c.idcliente and r.idvolquete =v.idvolquete and r.fechaEntrega >= '"+fecha_desde+"' and r.fechaEntrega <= '"+fecha_hasta+"'");


                while(rs1.next()) {
                    band=1;
                    Coleccionn.add("Cliente: "+rs1.getString("nombreCliente")+" ("+rs1.getString("codCliente") +")"+"\nCódigo Volquete: "+rs1.getString("codigoVolquete") +"\nFecha Entrega :"+ rs1.getDate("fechaEntrega"));

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


        }else if(band ==0){
            Toast.makeText(context, "No existen Entregas de Reservas entre ese rango de fechas", Toast.LENGTH_SHORT).show();
        }







    }




}
