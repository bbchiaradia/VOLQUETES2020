package frgp.utn.edu.com.volquetes;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

import frgp.utn.edu.com.volquetes.conexion.DataMainActivitBuscarUbicacionReservas;

import static android.graphics.Color.BLUE;

public class mapa extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        ArrayList<String> ArrayDescripcionMarker = new ArrayList<String>();
        ArrayList<String> ArrayX = new ArrayList<String>();
        ArrayList<String> ArrayY = new ArrayList<String>();
        Integer tamanio=0;


/*

        PolygonOptions polygonOptions = new PolygonOptions()
                .add(new LatLng(34.47, -58.68),
                        new LatLng(37.45, -122.0),
                        new LatLng(37.45, -122.2),
                        new LatLng(37.35, -122.2),
                        new LatLng(37.35, -122.0))
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

// Get back the mutable Polygon
        Polygon polygon = mMap.addPolygon(polygonOptions);

        */



        new DataMainActivitBuscarUbicacionReservas(mapa.this, ArrayX, ArrayY, ArrayDescripcionMarker,tamanio).execute();

        /////////////  SI DESCOMENTO ESTO QUE ES CUANDO LEVANTA DE LA BASE NO FUNCIONA /////////////////
        //ArrayList<String> lista = (ArrayList<String>) getIntent().getStringArrayListExtra("miLista");
        // ArrayList<String> lista2 = (ArrayList<String>) getIntent().getStringArrayListExtra("miLista2");
        ///////////////////////////////////////////////////////////////////////////////////////////////77





        ////////////////  PONIENDOLE EXPLICITAMENTE LOS DATOS SI /////////////////////////
        ArrayList<String> lista = new ArrayList<>();
        lista.add("-34.4703236");
        lista.add("-34.4720495");
        lista.add("-34.4586286");
        lista.add("-34.4762176");
        lista.add("-34.4500756");

        ArrayList<String> lista2 = new ArrayList<>();
        lista2.add("-58.6838082");
        lista2.add("-58.6904324");
        lista2.add("-58.7001067");
        lista2.add("-58.7232627");
        lista2.add("-58.7303537");

        tamanio = 5;
        ///////////////////////////////////////////////////////////////////////////////////


        System.out.println("aca lista1" +lista);
        System.out.println("aca tamaño" +tamanio);
        System.out.println("aca lista2" +lista2);


        /// Casteo la lista que tiene las latitudes
        double[] failsArray = new double[tamanio]; //create an array with the size of the failList
        for (int i = 0; i < tamanio; ++i) { //iterate over the elements of the list
            failsArray[i] = Double.parseDouble(lista.get(i)); //store each element as a double in the array
        }

        /// Casteo la lista que tiene las longitudes
        double[] failsArray2 = new double[tamanio]; //create an array with the size of the failList
        for (int i = 0; i < tamanio; ++i) { //iterate over the elements of the list
            failsArray2[i] = Double.parseDouble(lista2.get(i)); //store each element as a double in the array
        }

        ///// Recorro las listas y genero el marker.
        for (int i = 0; i < tamanio; i++){
            LatLng vol_1 = new LatLng(failsArray[i], failsArray2[i]);
            System.out.println("Belen" +vol_1.latitude);
            System.out.println(vol_1.longitude);
            mMap.addMarker(new MarkerOptions().position(vol_1));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vol_1, 12f));
        }



        /////////////  DIUJO ZONAS - POLIGIONOS /////////////////
        Polygon polygon1 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-34.470327, -58.683718),
                        new LatLng(-34.478402, -58.673591),
                        new LatLng( -34.488080, -58.694278),
                        new LatLng(-34.475546, -58.704168))
                .strokeColor(Color.RED));
        polygon1.setTag("ZONA 1");
        polygon1.setStrokeWidth(4f);



        Polygon polygon2 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-34.470327, -58.683718),
                        new LatLng(-34.447765, -58.700565),
                        new LatLng( -34.468960, -58.756097),
                        new LatLng( -34.474217, -58.736015))
                .strokeColor(BLUE));
        polygon2.setTag("ZONA 2");
        polygon2.setStrokeWidth(4f);


        Polygon polygon3 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-34.470327, -58.683718),
                        new LatLng(-34.468210, -58.623871),
                        new LatLng(  -34.446975,  -58.674276),
                        new LatLng( -34.466990, -58.683652))
                .strokeColor(Color.GREEN));
        polygon3.setTag("ZONA 3");
        polygon3.setStrokeWidth(4f);


        ///////////// FIN  DIUJO ZONAS - POLIGIONOS /////////////////



    }
}
