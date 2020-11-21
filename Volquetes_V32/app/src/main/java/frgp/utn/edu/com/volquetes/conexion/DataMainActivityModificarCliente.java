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
import frgp.utn.edu.com.volquetes.principal;

public class DataMainActivityModificarCliente extends AsyncTask<String, Void, String> {
    private Context context;
    Cliente cliente = new Cliente();
    int band = 0;
    private ProgressDialog dialog;
    public DataMainActivityModificarCliente(Context context) {
        this.context = context;
        dialog = new ProgressDialog(context);
    }

    //@Override
    protected String doInBackground(String... urls) {
        String response = "";
        String nombre_cliente = urls[0];
        String direccion_cliente = urls[1];
        String email_cliente = urls[2];
        String cuit_cliente = urls[3];
        String celular_cliente = urls[4];
        String telParticular_cliente = urls[5];
        String telLaboral_cliente = urls[6];
        String codigo_cliente = urls[7];
        System.out.println("nombre: " + nombre_cliente + "direccion   " + direccion_cliente + " - email " + email_cliente + " - cuit " + cuit_cliente + " - cel " + celular_cliente + " - partic " + telParticular_cliente+ " - Laboral " + telLaboral_cliente + " - codigo " + codigo_cliente  );


        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            ///Valido si existe el Cliente que voy a registrar:
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT * FROM `clientes` WHERE codCliente = '"+codigo_cliente+"'");
            while(rs1.next()) {
               band=1;
            }
            ///Valido si no existen registros para realizar el Insert
            if(band==1){
                Statement st = con.createStatement();
                int rs = st.executeUpdate("UPDATE `clientes` SET `nombreCliente`='"+nombre_cliente+"',`direccion`='"+direccion_cliente+"',`telefonoParticular`='"+telParticular_cliente+"',`telefonoLaboral`='"+telLaboral_cliente+"',`celular`='"+celular_cliente+"',`cuit`='"+cuit_cliente+"',`email`='"+email_cliente+"' WHERE `codCliente` = '"+codigo_cliente+"'");
                System.out.println(rs);
                response = "Carga exitosa";
                //Seteo el nombre del usuario para mostrar en el toast
                cliente.setNombreCli(nombre_cliente);
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
            Toast.makeText(context, "El cliente  " + cliente.toString() +"  se ha modificado correctamente", Toast.LENGTH_SHORT).show();
            Intent intent_name = new Intent();
            intent_name.setClass(context, principal.class);
            context.startActivity(intent_name);
        }else if(band ==0){
            Toast.makeText(context, "El cliente ingresado no se encuentra registrado", Toast.LENGTH_SHORT).show();
        }
    }




}
