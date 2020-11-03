package frgp.utn.edu.com.volquetes.conexion;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import frgp.utn.edu.com.volquetes.entidad.Usuario;
import frgp.utn.edu.com.volquetes.principal;

public class DataMainActivityIniciarSesion extends AsyncTask<String, Void, String> {
    int band = 0;//deberia dejarlo en 0, lo pongo ahora porque la base no anda
    private Context context;
    TextView mensaje_error;
    Usuario usuario = new Usuario();
    public DataMainActivityIniciarSesion(Context context) {
        this.context = context;

    }
    //@Override
    protected String doInBackground(String... urls) {
        String response = "";
        String nombre_usuario = urls[0];
        String contrasenia_usuario = urls[1];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT idUsuario, nombreUsuario, contraseniaUsuario FROM `usuarios` WHERE nombreUsuario = '"+nombre_usuario+"' and contraseniaUsuario ='"+contrasenia_usuario+"'");
            while(rs.next()) {
                band = 1;
                this.usuario.setId(rs.getInt("idUsuario"));
                this.usuario.setNombre(rs.getString("nombreUsuario"));
                this.usuario.setContrasenia(rs.getString("contraseniaUsuario"));
            }

            System.out.println(usuario.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response = "Carga no exitosa";
        }
        return response;
    }
    //@Override
    protected void onPostExecute(String response) {

       if(band==0) {
            Toast.makeText(context, "Usuario y/o contraseña invalido" , Toast.LENGTH_SHORT).show();
           /*Intent intent_name = new Intent();
           intent_name.setClass(context, principal.class );
           context.startActivity(intent_name);
*/
        }
        if (band ==1){
            //Toast.makeText(context, "Bienvenido/a " + usuario.toString() , Toast.LENGTH_SHORT).show();
            //Llamo al intent de la pantalla principal ya que en el MainActivity no pude validar ni hacer que esta clase devuelva un dato.
            Intent intent_name = new Intent();
            intent_name.setClass(context, principal.class );
            context.startActivity(intent_name);
        }

    }

}
