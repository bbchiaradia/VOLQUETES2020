package frgp.utn.edu.com.volquetes.conexion;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import frgp.utn.edu.com.volquetes.MainActivity;
import frgp.utn.edu.com.volquetes.entidad.Usuario;

public class DataMainActivityRegistrarse extends AsyncTask<String, Void, String> {
    private Context context;
    Usuario usuario = new Usuario();
    int band = 0;
    public DataMainActivityRegistrarse(Context context) {
        this.context = context;
    }

    //@Override
    protected String doInBackground(String... urls) {
        String response = "";
        String nombre_usuario = urls[0];
        String contrasenia_usuario = urls[1];
        String confirmar_contrasenia_usuario = urls[2];
        System.out.println("nombre: " + nombre_usuario + "contrasenia   " + contrasenia_usuario + " - confirmar_contra " + confirmar_contrasenia_usuario );


        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            ///Valido si existe el Usuario que voy a registrar:
            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT idUsuario, nombreUsuario, contraseniaUsuario FROM `usuarios` WHERE nombreUsuario = '"+nombre_usuario+"'");
            while(rs1.next()) {
               band=1;
            }
            ///Valido si no existen registros para realizar el Insert
            if(band==0){
                Statement st = con.createStatement();
                int rs = st.executeUpdate("INSERT INTO `usuarios` (`nombreUsuario`, `contraseniaUsuario`) VALUES ('"+nombre_usuario+"','"+contrasenia_usuario+"')");
                System.out.println(rs);
                response = "Carga exitosa";
                //Seteo el nombre del usuario para mostrar en el toast
                usuario.setNombre(nombre_usuario);
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
            Toast.makeText(context, "El usuario  " + usuario.toString() +"  se ha registrado correctamente", Toast.LENGTH_SHORT).show();
            Intent intent_name = new Intent();
            intent_name.setClass(context, MainActivity.class);
            context.startActivity(intent_name);
        }else if(band ==1){
            Toast.makeText(context, "El usuario ingresado ya se encuentra registrado", Toast.LENGTH_SHORT).show();

        }
    }




}
