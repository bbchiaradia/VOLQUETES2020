package frgp.utn.edu.com.volquetes.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataMainActivityBorrarReserva extends AsyncTask<String, Void, String> {
    private Context context;
    int band = 0;
    int band2 = 0;
    public DataMainActivityBorrarReserva(Context context) {
        this.context = context;
    }

    //@Override
    protected String doInBackground(String... urls) {
        String response = "";
        String id_reserva = urls[0];
        String id_volquete= urls[1];
        System.out.println("id reserva: " + id_reserva );
        System.out.println("id volquete: " + id_volquete );

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            // Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            int rs = st.executeUpdate("DELETE FROM `reservas` WHERE idReserva= "+id_reserva);
            int rs1 = st1.executeUpdate("UPDATE `volquetes` SET `idEstadoVolquete`=1 WHERE idvolquete = "+id_volquete);
            System.out.println(rs);
            System.out.println(rs1);
            response = "Carga exitosa";
        } catch (Exception e) {
            e.printStackTrace();
            response = "Carga no exitosa";
        }

        return response;

    }

    //@Override
    protected void onPostExecute(String response) {
        if(response == "Carga exitosa") {
            Toast.makeText(context, "La reserva se ha borrado correctamente", Toast.LENGTH_SHORT).show();
          //  Intent intent_name = new Intent();
           // intent_name.setClass(context, principal.class);
            //context.startActivity(intent_name);
        }else if(band ==0){
            Toast.makeText(context, "La reserva no se ha podido borrar correctamente", Toast.LENGTH_SHORT).show();
        }
    }




}
