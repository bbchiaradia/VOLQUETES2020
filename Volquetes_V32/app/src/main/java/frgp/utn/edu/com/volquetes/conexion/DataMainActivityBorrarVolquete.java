package frgp.utn.edu.com.volquetes.conexion;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import android.app.ProgressDialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.entidad.Volquete;
import frgp.utn.edu.com.volquetes.principal;

public class DataMainActivityBorrarVolquete extends AsyncTask<String, Void, String> {
    private Context context;
    Volquete volquete = new Volquete();
    private ProgressDialog dialog;
    int band = 0;
    int band2 = 0;
    public DataMainActivityBorrarVolquete(Context context) {
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
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery("SELECT idVolquete FROM `reservas` WHERE idVolquete =  (SELECT idVolquete FROM `volquetes` WHERE codigoVolquete = '"+codigo_volquete+"')");
            while(rs2.next()) {
                 band2 = 1;
            }

            Statement st1 = con.createStatement();
            ResultSet rs1 = st1.executeQuery("SELECT * FROM `volquetes` WHERE codigoVolquete = '"+codigo_volquete+"'");
            while(rs1.next()) {
               band=1;
            }
              //SELECT idVolquete FROM `reservas` WHERE idVolquete =  (SELECT idVolquete FROM `volquetes` WHERE codigoVolquete = 'VOL1')
            if(band==1 && band2==0){
                Statement st = con.createStatement();
                int rs = st.executeUpdate("DELETE FROM `volquetes` WHERE `codigoVolquete` = '"+codigo_volquete+"'");
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
        if(band == 1 && response == "Carga exitosa" && band2 != 1) {
            Toast.makeText(context, "El volquete se ha borrado correctamente", Toast.LENGTH_SHORT).show();
            Intent intent_name = new Intent();
            intent_name.setClass(context, principal.class);
            context.startActivity(intent_name);
        }else if(band ==0 && band2 != 1){
            Toast.makeText(context, "El codigo de volquete ingresado no existe ", Toast.LENGTH_SHORT).show();
        }
        if(band2 ==1){
            Toast.makeText(context, "El codigo de volquete esta asociado a una reserva ", Toast.LENGTH_SHORT).show();
        }

    }




}
