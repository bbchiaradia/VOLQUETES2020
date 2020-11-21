package frgp.utn.edu.com.volquetes.conexion;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import frgp.utn.edu.com.volquetes.entidad.Ubicacion;
import frgp.utn.edu.com.volquetes.mapa;

public class DataMainActivitBuscarUbicacionReservas extends AsyncTask<String, Void, String> implements Serializable {
    private Context context;
    ArrayList latitud[] ={} ;
    double longitud[] = {} ;
    Integer tamanio =0;

    ArrayList<String> Arraylat = new ArrayList<>();
    ArrayList<String> ArrayTamano = new ArrayList<>();
    ArrayList<String> Arraylong = new ArrayList<>();
    GoogleMap Map;
     ArrayList<Double> descripcion = new ArrayList<Double>();

    int band = 0;

    public DataMainActivitBuscarUbicacionReservas(Context context, ArrayList Latitud, ArrayList Longitud, ArrayList Descripcion, Integer Tamanio) {
        this.context = context;
        this.Arraylat = Latitud;
        this.Arraylong = Latitud;
        this.descripcion = Descripcion;
        this.tamanio = Tamanio;
    }
    int cantReg = 0;
    ArrayList<String> Coleccionn = new ArrayList<String>();
    ArrayList<String> Arrayx = new ArrayList<>();
    ArrayList<String> Arrayy = new ArrayList<>();
    ArrayList<Ubicacion> ubicaciones = new ArrayList<>();



    //@Override
    protected String doInBackground(String... urls) {
        String response = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10309216", "sql10309216", "Np6V3EK8Fk");
            Connection con = DriverManager.getConnection("jdbc:mysql://freedb.tech/freedbtech_volquetes", "freedbtech_volquetes", "volquetes2020");
            ///Valido si existe el Cliente que voy a registrar:
            Statement st1 = con.createStatement();

                ResultSet rs1 = st1.executeQuery("SELECT distinct COUNT(*) as total, c.latitud, c.longitud , c.nombreCliente , c.codCliente\n" +
                        "FROM `reservas` r  inner join clientes c on c.idCliente = r.idCliente\n" +
                        "WHERE r.fechaRetiro is null\n" +
                        "and not (c.latitud is null or c.longitud is null) \n" +
                        "GROUP by c.idCliente");


                int contador = 0;
                while(rs1.next()) {
                band=1;
                    Coleccionn.add( rs1.getString("codCliente")+" - Cantidad Reservas: "+ rs1.getString("total"));
                    Arrayx.add( rs1.getString("latitud"));
                    //Arrayx.add( rs1.getString("longitud"));
                    Arrayy.add( rs1.getString("longitud"));

                   // Double latitud = rs1.getDouble("latitud");
                  //  Double longitud = rs1.getDouble("longitud");
                   // Ubicacion ubi = new Ubicacion(latitud,longitud);
                   // ubicaciones.add(ubi);

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

            Arraylat = Arrayx;
            Arraylong = Arrayy;
            System.out.println("Array lat"+Arraylat);
            System.out.println("Array long"+Arraylong);

            Integer tamanoLista1 = Arraylong.size();

            this.tamanio = tamanoLista1;

            Intent intent = new Intent(this.context, mapa.class);
            intent.putExtra("miLista", Arrayx);
            intent.putExtra("miLista2", Arrayy);
            this.context.startActivity(intent);




        }else if(band ==0){
            Toast.makeText(context, "No se encontraron reservas", Toast.LENGTH_SHORT).show();
        }
    }




}
