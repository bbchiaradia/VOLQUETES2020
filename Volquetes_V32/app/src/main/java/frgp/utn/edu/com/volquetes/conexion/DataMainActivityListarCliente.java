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

import frgp.utn.edu.com.volquetes.entidad.Cliente;

public class DataMainActivityListarCliente extends AsyncTask<String, Void, String> {
    private Context context;
    Cliente cliente = new Cliente();
    int band = 0;
    final ListView list_cliente;

    public DataMainActivityListarCliente(Context context, ListView listaCliente) {
        this.context = context;
        this.list_cliente = listaCliente;

    }
    int cantReg = 0;
    ArrayList<String> Coleccionn = new ArrayList<String>();
    ArrayAdapter<String> adaptador;

    //@Override
    protected String doInBackground(String... urls) {
        String response = "";
        String codigo_cliente = urls[0];
      //  System.out.println("codigo_cliente: " + codigo_cliente );


        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            ///Valido si existe el Cliente que voy a registrar:
            Statement st1 = con.createStatement();


            if (codigo_cliente == null){
                ResultSet rs1 = st1.executeQuery("SELECT * FROM `clientes`");
                while(rs1.next()) {
                    band=1;
                    cliente.setCodigoCliente(rs1.getString("codCliente"));
                    Coleccionn.add(rs1.getString("nombreCliente")+" - "+ rs1.getString("codCliente") +"\nDireccion: "+ rs1.getString("direccion") +"\nCUIT: "+ rs1.getString("cuit") +"\nE-mail: "+ rs1.getString("email") );
                }
            }else{
                ResultSet rs1 = st1.executeQuery("SELECT * FROM `clientes` where codCliente like '%"+codigo_cliente+"%' or nombreCliente like '%"+codigo_cliente+"%' or email like '%"+codigo_cliente+"%'");
                while(rs1.next()) {
                    band=1;
                    cliente.setCodigoCliente(rs1.getString("codCliente"));
                    //Coleccionn.add(rs1.getString("nombreCliente")+" - "+ rs1.getString("codCliente"));
                    Coleccionn.add(rs1.getString("nombreCliente")+" - "+ rs1.getString("codCliente") +"\nDireccion: "+ rs1.getString("direccion") +"\nCUIT: "+ rs1.getString("cuit") +"\nE-mail: "+ rs1.getString("email") );
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

    //@Override
    protected void onPostExecute(String response) {
        //Valido si el usuario no existio y se registro correctamente
        if(band == 1 && response == "Carga exitosa") {
            adaptador = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, Coleccionn);
            list_cliente.setAdapter(adaptador);

        }else if(band ==0){
            Toast.makeText(context, "El codigo de cliente ingresado no existe", Toast.LENGTH_SHORT).show();
        }
    }




}
