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


        new DataMainActivitBuscarUbicacionReservas(mapa.this, ArrayX, ArrayY, ArrayDescripcionMarker,tamanio).execute();


        System.out.println("aca lista1 ANTESSSS" +getIntent().getStringArrayListExtra("miLista"));
        System.out.println("aca tamaño ANTESSSS" +getIntent().getIntExtra("tamanio",0));
        System.out.println("aca lista2 ANTESSS" +getIntent().getStringArrayListExtra("miLista2"));
        System.out.println("aca listaCLIENTE ANTESSS" +getIntent().getStringArrayListExtra("milistaCliente"));

        //cantidad de reservas/markes a dibujar
        tamanio = getIntent().getIntExtra("tamanio",0);
        /// Casteo la lista que tiene las latitudes
        double[] failsArray = new double[tamanio]; //create an array with the size of the failList
        for (int i = 0; i < tamanio; ++i) { //iterate over the elements of the list
            failsArray[i] = Double.parseDouble(getIntent().getStringArrayListExtra("miLista").get(i)); //store each element as a double in the array
        }

        /// Casteo la lista que tiene las longitudes
        double[] failsArray2 = new double[tamanio]; //create an array with the size of the failList
        for (int i = 0; i < tamanio; ++i) { //iterate over the elements of the list
            failsArray2[i] = Double.parseDouble(getIntent().getStringArrayListExtra("miLista2").get(i)); //store each element as a double in the array
        }

        ///// Recorro las listas y genero el marker.
        for (int i = 0; i < tamanio; i++){
            LatLng vol_1 = new LatLng(failsArray[i], failsArray2[i]);
            mMap.addMarker(new MarkerOptions().position(vol_1).title(getIntent().getStringArrayListExtra("milistaCliente").get(i)));
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
