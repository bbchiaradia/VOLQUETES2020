package frgp.utn.edu.com.volquetes.conexion;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import frgp.utn.edu.com.volquetes.entidad.Cliente;
import frgp.utn.edu.com.volquetes.principal;

public class DataMainActivityAltaCliente extends AsyncTask<String, Void, String> {
    private Context context;
    Cliente cliente;
    int band = 0;
    public DataMainActivityAltaCliente(Context context) {
        this.context = context;
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
            if(band==0){
                Statement st = con.createStatement();
                int rs = st.executeUpdate("INSERT INTO `clientes` (`nombreCliente`, `direccion`, `email`, `cuit`, `celular`,`telefonoParticular`,`telefonoLaboral`, `codCliente`) VALUES ('"+nombre_cliente+"','"+direccion_cliente+"','"+email_cliente+"', '"+cuit_cliente+"','"+celular_cliente+"', '"+telParticular_cliente+"','"+telLaboral_cliente+"', '"+codigo_cliente+"')");
                System.out.println(rs);
                response = "Carga exitosa";
                //Seteo el nombre del usuario para mostrar en el toast
                cliente = new Cliente(nombre_cliente,direccion_cliente,email_cliente,cuit_cliente,celular_cliente,telParticular_cliente,telLaboral_cliente,codigo_cliente);
            }


        } catch (Exception e) {
            e.printStackTrace();
            response = "Carga no exitosa";
        }

        return response;
    }

    //@Override
    protected void onPostExecute(String response) {
        //Valido si el usuario no existio y se registro correctamente
        if(band == 0 && response == "Carga exitosa") {
            Toast.makeText(context, "El cliente  " + cliente.toString() +"  se ha registrado correctamente", Toast.LENGTH_SHORT).show();
            Intent intent_name = new Intent();
            intent_name.setClass(context, principal.class);
            context.startActivity(intent_name);
        }else if(band ==1){
            Toast.makeText(context, "El cliente ingresado ya se encuentra registrado", Toast.LENGTH_SHORT).show();
        }
    }




}