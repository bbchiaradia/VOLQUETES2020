package frgp.utn.edu.com.volquetes.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataMainActivityReporteConsultarStock extends AsyncTask<String, Void, String> {
    private Context context;
    int band = 0;
    final EditText txt_disponible;
    final EditText txt_reservados;

    public DataMainActivityReporteConsultarStock(Context context, EditText stk_disponibles, EditText stk_reservados) {
        this.context = context;
        this.txt_disponible = stk_disponibles;
        this.txt_reservados = stk_reservados;

    }
    int cantReg = 0;


    String Disponibles_s = null;
    String Reservados_s = null;

    //@Override
    protected String doInBackground(String... urls) {
        String response = "";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            ///Valido si existe el Cliente que voy a registrar:
            Statement st1 = con.createStatement();

                ResultSet rs1 = st1.executeQuery("SELECT count(*) as total FROM `volquetes` where idEstadoVolquete=1");
                while(rs1.next()) {
                    band=1;
                    Disponibles_s = rs1.getString("total");
                    }

                ResultSet rs2 = st1.executeQuery("SELECT count(*) as total FROM `volquetes` where idEstadoVolquete=2");
                while(rs2.next()) {
                    band=1;

                    if (rs2.getString("total") == null){
                        Reservados_s ="0";
                    }
                    else{
                        Reservados_s = rs2.getString("total");
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
           txt_disponible.setText(Disponibles_s.toString());
           txt_reservados.setText(Reservados_s.toString());

        }else if(band ==0){
            Toast.makeText(context, "No se pudo consultar el stock", Toast.LENGTH_SHORT).show();
        }
    }




}
