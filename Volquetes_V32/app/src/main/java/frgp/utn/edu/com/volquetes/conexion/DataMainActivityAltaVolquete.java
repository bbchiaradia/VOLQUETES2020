package frgp.utn.edu.com.volquetes.conexion;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import android.app.ProgressDialog;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.entidad.Volquete;
import frgp.utn.edu.com.volquetes.principal;

public class DataMainActivityAltaVolquete extends AsyncTask<String, Void, String> {
    private Context context;
    Volquete volquete = new Volquete();
    int band = 0;
    private ProgressDialog dialog;
    public DataMainActivityAltaVolquete(Context context) {
        this.context = context;
        dialog = new ProgressDialog(context);
    }

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
            ResultSet rs1 = st1.executeQuery("SELECT * FROM `volquetes` WHERE codigoVolquete = '"+codigo_volquete+"'");
            while(rs1.next()) {
               band=1;
            }
            ///Valido si no existen registros para realizar el Insert
            if(band==0){
                Statement st = con.createStatement();
                int rs = st.executeUpdate("INSERT INTO `volquetes` (`codigoVolquete`, `idEstadoVolquete`) VALUES ('"+codigo_volquete+"','1')");
                System.out.println(rs);
                response = "Carga exitosa";
                //Seteo el nombre del usuario para mostrar en el toast

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
        if(band == 0 && response == "Carga exitosa") {
            Toast.makeText(context, "El volquete se ha registrado correctamente", Toast.LENGTH_SHORT).show();
            Intent intent_name = new Intent();
            intent_name.setClass(context, principal.class);
            context.startActivity(intent_name);
        }else if(band ==1){
            Toast.makeText(context, "El codigo de volquete ingresado ya existe", Toast.LENGTH_SHORT).show();
        }
    }




}
