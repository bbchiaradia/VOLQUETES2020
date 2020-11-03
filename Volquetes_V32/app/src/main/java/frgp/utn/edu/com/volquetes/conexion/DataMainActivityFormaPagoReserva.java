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

public class DataMainActivityFormaPagoReserva extends AsyncTask<String, Void, String> {

    private Spinner sp_forma_pago;
    private ListView lvFormaPago;
    private Context context;

    private static ArrayList<FormaPago> listaFormaPago = new ArrayList<>();

    public DataMainActivityFormaPagoReserva(Context ct, Spinner lv ){
        this.context = ct;
        sp_forma_pago = lv;
    }
    protected String doInBackground(String... urls) {
        String response = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM formaPago");
            //result2 = " ";
            listaFormaPago.clear();
            FormaPago formaPago;
            while(rs.next()) {
                //formaPago = new FormaPago();
               // formaPago.setIdFormaPago(rs.getInt("idFormaPago"));
                //formaPago.setDescripcionFormaPago(rs.getString("descripcionFormaPago"));
                listaFormaPago.add(new FormaPago(rs.getInt("idFormaPago"),rs.getString("descripcionFormaPago")));
            }
            System.out.println("Lista forma de pago " +listaFormaPago.size() );

        } catch (Exception e) {
            e.printStackTrace();
            response = "Carga no exitosa";
        }
            return response;

    }

    @Override
    protected void onPostExecute(String response) {
        ArrayAdapter<FormaPago> adapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, listaFormaPago);
        sp_forma_pago.setAdapter(adapter);
    }
}
