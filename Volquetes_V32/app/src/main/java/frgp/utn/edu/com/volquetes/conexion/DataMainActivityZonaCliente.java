package frgp.utn.edu.com.volquetes.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.entidad.FormaPago;
import frgp.utn.edu.com.volquetes.entidad.Zona;

public class DataMainActivityZonaCliente extends AsyncTask<String, Void, String> {

    private Spinner sp_zona;
    private ListView lvZona;
    private Context context;

    private static ArrayList<Zona> listaZona = new ArrayList<>();

    public DataMainActivityZonaCliente(Context ct, Spinner lv ){
        this.context = ct;
        sp_zona = lv;
    }
    protected String doInBackground(String... urls) {
        String response = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM zonas");
            //result2 = " ";
            listaZona.clear();
            Zona zona;
            while(rs.next()) {
                listaZona.add(new Zona(rs.getInt("idZona"),rs.getString("nombreZona")));
            }
            System.out.println("Lista zonas " +listaZona.size() );

        } catch (Exception e) {
            e.printStackTrace();
            response = "Carga no exitosa";
        }
            return response;

    }

    @Override
    protected void onPostExecute(String response) {
        ArrayAdapter<Zona> adapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, listaZona);
        sp_zona.setAdapter(adapter);
    }
}
