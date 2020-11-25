package frgp.utn.edu.com.volquetes.conexion;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.entidad.Cliente;
import frgp.utn.edu.com.volquetes.entidad.Reserva;
import frgp.utn.edu.com.volquetes.principal;

public class DataMainActivityAltaReserva extends AsyncTask<String, Void, String>  {
    int band = 0;
    Reserva reserva = new Reserva();
    Cliente cliente = new Cliente();
    private Context context;
    private ProgressDialog dialog;
    public DataMainActivityAltaReserva (Context context){
        this.context = context;
        dialog = new ProgressDialog(context);
    }


    protected String doInBackground(String... urls) {
        String response = "";
        String codCliente = urls[0];
        String precio = urls[1];
        String idFormaPago = urls[2];
        String fechaEntrega = urls[3];
        //String fechaRetiro = urls[5];
        System.out.println("codCliente: " + codCliente + " - precio " + precio + " - idFormaPago " + idFormaPago + " - fechaEntrega " + fechaEntrega );



        try {
            Class.forName("com.mysql.jdbc.Driver");

            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            ///Valido si existe el Cliente que voy a registrar:
            Statement st2 = con.createStatement();
            ResultSet rs1 = st2.executeQuery("SELECT max(idVolquete) FROM `volquetes` WHERE idEstadoVolquete = 1");//1Habilitado-2Deshabiliado
            while(rs1.next()) {
                if(rs1.getInt("max(idVolquete)") != 0){
                    reserva.setIdVolquete(rs1.getInt("max(idVolquete)"));
                    band= 1;
                }

            }


            Statement st3 = con.createStatement();
            ResultSet rs3 = st3.executeQuery("SELECT idCliente FROM `clientes` WHERE codCliente='"+codCliente+"'");
            while(rs3.next()) {
                if(rs3.getInt("idCliente") != 0){
                    cliente.setId(rs3.getInt("idCliente"));
                }

            }

            if(band==1){
                Statement st = con.createStatement();
                System.out.println("ACAAAAAA antes RS");
                int rs = st.executeUpdate("INSERT INTO `reservas` (`idCliente`, `idVolquete`, `idUsuario`, `precio`, `idFormaPago`, `fechaEntrega`) VALUES ('"+cliente.getId()+"','"+reserva.getIdVolquete()+"','1', '"+precio+"','"+idFormaPago+"','"+ fechaEntrega +"' )");
                System.out.println("ACAAAAAA despues RS" + rs);
                Statement st1 = con.createStatement();
                int rs2 = st1.executeUpdate("UPDATE `volquetes` SET `idEstadoVolquete`= 2 WHERE idVolquete = " +reserva.getIdVolquete() );//seteoDesHabilitado
                System.out.println(rs2);
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
     if(band ==0){
         Toast.makeText(this.context, "No hay volquetes disponibles", Toast.LENGTH_SHORT).show();
     }else{
         Toast.makeText(this.context, "Reserva Exitosa - Se le asigno el volquete: " + reserva.getIdVolquete(), Toast.LENGTH_SHORT).show();
         Intent intent_name = new Intent();
         intent_name.setClass(context, principal.class);
         context.startActivity(intent_name);
     }

    }
}
