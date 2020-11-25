package frgp.utn.edu.com.volquetes.conexion;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.entidad.Cliente;

public class DataMainActivityBuscarCliente extends AsyncTask<String, Void, String> {
    private Context context;
    final EditText nombre_cliente_modif ;
    final EditText direccion_cliente_modif ;
    final EditText email_cliente_modif;
    final EditText cuit_cliente_modif ;
    final EditText cel_cliente_modif ;
    final EditText tel_par_cliente_modif;
    final EditText tel_lab_cliente_modif;
    final EditText latitud_cliente_modif;
    final EditText longitud_cliente_modif;
    private ProgressDialog dialog;

    Cliente cliente = new Cliente();
    int band = 0;
    public DataMainActivityBuscarCliente(Context context , EditText nombre_modif,EditText direccion_modif, EditText email_modif,EditText cuit_modif,EditText cel_modif,EditText tel_par_modif,EditText tel_lab_modif,  EditText latitud_cliente_modif, EditText longitud_cliente_modif) {
        this.nombre_cliente_modif = nombre_modif;
        this.direccion_cliente_modif = direccion_modif;
        this.email_cliente_modif = email_modif;
        this.cuit_cliente_modif = cuit_modif;
        this.cel_cliente_modif = cel_modif;
        this.tel_par_cliente_modif = tel_par_modif;
        this.tel_lab_cliente_modif = tel_lab_modif;
        this.latitud_cliente_modif = latitud_cliente_modif;
        this.longitud_cliente_modif = longitud_cliente_modif;
        this.context = context;
        dialog = new ProgressDialog(context);

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

                cliente.setId(rs1.getInt("idCliente"));
                cliente.setNombreCli(rs1.getString("nombreCliente"));
                cliente.setDireccionCliente(rs1.getString("direccion"));
                cliente.setEmailCliente(rs1.getString("email"));
                cliente.setCUITCliente(rs1.getString("cuit"));
                cliente.setCelularCliente(rs1.getString("celular"));
                cliente.setTelParticularCliente(rs1.getString("telefonoParticular"));
                cliente.setTelLaboralCliente(rs1.getString("telefonoLaboral"));
                cliente.setCodigoCliente(rs1.getString("codCliente"));
                cliente.setLatitud(rs1.getString("latitud"));
                cliente.setLongitud(rs1.getString("longitud"));
                System.out.println(rs1.getString("nombreCliente"));


            }
            ///Valido si no existen registros para realizar el Insert
            if(band==1){
                Statement st = con.createStatement();



             //   int rs = st.executeUpdate("INSERT INTO `clientes` (`nombreCliente`, `direccion`, `email`, `cuit`, `celular`,`telefonoParticular`,`telefonoLaboral`, `codCliente`) VALUES ('"+nombre_cliente+"','"+direccion_cliente+"','"+email_cliente+"', '"+cuit_cliente+"','"+celular_cliente+"', '"+telParticular_cliente+"','"+telLaboral_cliente+"', '"+codigo_cliente+"')");
              //  System.out.println(rs);
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
            Toast.makeText(context, "Los datos del cliente se han cargado correctamente", Toast.LENGTH_SHORT).show();
            this.nombre_cliente_modif.setText(cliente.getNombreCliente().toString());
            this.direccion_cliente_modif.setText(cliente.getDireccionCliente().toString());
            this.email_cliente_modif.setText(cliente.getEmailCliente().toString());
            this.cuit_cliente_modif.setText(cliente.getCuitCliente().toString());
            this.cel_cliente_modif.setText(cliente.getCelularCliente().toString());
            this.tel_par_cliente_modif.setText(cliente.getTel_par_Cliente().toString());
            this.tel_lab_cliente_modif.setText(cliente.getTel_lab_Cliente().toString());
            this.latitud_cliente_modif.setText(cliente.getLatitud().toString());
            this.longitud_cliente_modif.setText(cliente.getLongitud().toString());
        }else if(band ==0){
            Toast.makeText(context, "El cliente ingresado no existe", Toast.LENGTH_SHORT).show();
        }
    }
}
