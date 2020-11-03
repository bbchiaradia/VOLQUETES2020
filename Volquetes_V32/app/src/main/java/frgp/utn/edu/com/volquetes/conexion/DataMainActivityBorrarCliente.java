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

public class DataMainActivityBorrarCliente extends AsyncTask<String, Void, String> {
    private Context context;
    Cliente cliente = new Cliente();
    int band = 0;
    public DataMainActivityBorrarCliente(Context context) {
        this.context = context;
    }

    //@Override
    protected String doInBackground(String... urls) {
        String response = "";
        String codigo_cliente = urls[0];
        System.out.println("nombre: " + codigo_cliente );


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
                int rs = st.executeUpdate("DELETE FROM `clientes` WHERE `codCliente` = '"+codigo_cliente+"'");
                System.out.println(rs);
                response = "Carga exitosa";
                //Seteo el nombre del usuario para mostrar en el toast
               // Cliente.setNombreCli(nombre_cliente);
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
        if(band == 1 && response == "Carga exitosa") {
            Toast.makeText(context, "El cliente se ha borrado correctamente", Toast.LENGTH_SHORT).show();
            Intent intent_name = new Intent();
            intent_name.setClass(context, principal.class);
            context.startActivity(intent_name);
        }else if(band ==0){
            Toast.makeText(context, "El cliente ingresado no existe", Toast.LENGTH_SHORT).show();
        }
    }




}
