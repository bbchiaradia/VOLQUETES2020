package frgp.utn.edu.com.volquetes.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import android.app.ProgressDialog;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.entidad.Volquete;

public class DataMainActivityListarVolquete extends AsyncTask<String, Void, String> {
    private Context context;
    Volquete volquete = new Volquete();
    int band = 0;
    final ListView list_volquete;
    private ProgressDialog dialog;

    public DataMainActivityListarVolquete(Context context, ListView listaVolquete) {
        this.context = context;
        this.list_volquete = listaVolquete;
        dialog = new ProgressDialog(context);

    }
    int cantReg = 0;
    ArrayList<String> Coleccionn = new ArrayList<String>();
    ArrayAdapter<String> adaptador;

    //@Override
    protected String doInBackground(String... urls) {
        String response = "";
        String codigo_volquete = urls[0];
        System.out.println("codigo_volquete: " + codigo_volquete );


        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            ///Valido si existe el Cliente que voy a registrar:
            Statement st1 = con.createStatement();


            if (codigo_volquete == null){
                ResultSet rs1 = st1.executeQuery("SELECT v.idvolquete, \n" +
                        "v.codigoVolquete,\n" +
                        "case when (v.idEstadoVolquete =1) then \"Disponible\" else \"Reservado\" end  as Estado,\n" +
                        "c.idCliente, \n" +
                        "case when (r.idReserva is null ) then \"No existe\" else c.nombreCliente end as reserva\n" +
                        "FROM `volquetes` as v left join `reservas` as r on v.idVolquete = r.idvolquete LEFT join `clientes` as c on r.idcliente = c.idcliente\n" +
                        "where r.fechaRetiro is null");
                while(rs1.next()) {
                    band=1;
                    volquete.setCodigoVolquete(rs1.getString("codigoVolquete"));
                    Coleccionn.add( "COD: "+ rs1.getString("codigoVolquete") + "\nEstado: " + rs1.getString("Estado") + "\nCiente: " + rs1.getString("reserva") );
                }
            }else{
                ResultSet rs1 = st1.executeQuery("SELECT v.idvolquete, \n" +
                        "v.codigoVolquete,\n" +
                        "case when (v.idEstadoVolquete =1) then \"Disponible\" else \"Reservado\" end  as Estado,\n" +
                        "c.idCliente, \n" +
                        "case when (r.idReserva is null ) then \"No Existe\" else c.nombreCliente end as reserva\n" +
                        "FROM `volquetes` as v left join `reservas` as r on v.idVolquete = r.idvolquete LEFT join `clientes` as c on r.idcliente = c.idcliente\n" +
                        "where r.fechaRetiro is null and codigoVolquete like '%"+codigo_volquete+"%' ");
                while(rs1.next()) {
                    band=1;
                    volquete.setCodigoVolquete(rs1.getString("codigoVolquete"));
                    Coleccionn.add( "COD: "+rs1.getString("codigoVolquete") + "\nEstado: " + rs1.getString("Estado") + "\nCiente: " + rs1.getString("reserva") );
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
        //Valido si el usuario no existio y se registro correctamente
        if(band == 1 && response == "Carga exitosa") {
            adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Coleccionn);
            list_volquete.setAdapter(adaptador);

        }else if(band ==0){
            Toast.makeText(context, "El codigo de volquete ingresado no existe", Toast.LENGTH_SHORT).show();
        }
    }




}
