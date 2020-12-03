package frgp.utn.edu.com.volquetes;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

import frgp.utn.edu.com.volquetes.conexion.DataMainActivitBuscarUbicacionReservas;

import static android.graphics.Color.BLUE;

public class mapa extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;

    Button btn_mapaInicio;
    Integer map=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


            final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);



        btn_mapaInicio = (Button) findViewById(R.id.btn_mapa_inicio);
        btn_mapaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrir = new Intent(mapa.this, principal.class);
                startActivity(abrir);
            }

            ;

        });

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


        new DataMainActivitBuscarUbicacionReservas(mapa.this).execute();



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
            // failsArray[i] = Double.parseDouble(lista.get(i)); //store each element as a double in the array
        }

        /// Casteo la lista que tiene las longitudes
        double[] failsArray2 = new double[tamanio]; //create an array with the size of the failList
        for (int i = 0; i < tamanio; ++i) { //iterate over the elements of the list
            failsArray2[i] = Double.parseDouble(getIntent().getStringArrayListExtra("miLista2").get(i)); //store each element as a double in the array
            // failsArray2[i] = Double.parseDouble(lista2.get(i)); //store each element as a double in the array
        }

        ///// Recorro las listas y genero el marker.
        for (int i = 0; i < tamanio; i++){
            LatLng vol_1 = new LatLng(failsArray[i], failsArray2[i]);
           mMap.addMarker(new MarkerOptions().position(vol_1).title(getIntent().getStringArrayListExtra("milistaCliente").get(i)));
            //  mMap.addMarker(new MarkerOptions().position(vol_1));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vol_1, 12f));
        }



        /////////////  DIUJO ZONAS - POLIGIONOS /////////////////

        Polygon polygon1 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-34.4466867, -58.7446665),
                        new LatLng(-34.4755556, -58.7870237),
                        new LatLng( -34.5313786, -58.7034557),
                        new LatLng(-34.5005326, -58.6488037))
                .strokeColor(Color.RED));
        polygon1.setTag("ZONA 1");
        polygon1.setStrokeWidth(4f);



        Polygon polygon2 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-34.4466867, -58.7446665),
                        new LatLng(-34.4810476,-58.6806737),
                        new LatLng( -34.4541926,-58.6249857),
                        new LatLng( -34.3982066,-58.6507117))
                .strokeColor(BLUE));
        polygon2.setTag("ZONA 2");
        polygon2.setStrokeWidth(4f);


        Polygon polygon3 = mMap.addPolygon(new PolygonOptions()
                .add(new LatLng(-34.4810476,-58.6806737),
                        new LatLng(-34.5005326, -58.6488037),
                        new LatLng(  -34.4786136,-58.6067997),
                        new LatLng( -34.4547056,-58.6234267))
                .strokeColor(Color.GREEN));
        polygon3.setTag("ZONA 3");
        polygon3.setStrokeWidth(4f);


        ///////////// FIN  DIUJO ZONAS - POLIGIONOS /////////////////




        /*
   //DIBUJO ZONAS DE CIRCULOS
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(-34.455587, -58.685503))
                .radius(1800)
                .strokeColor(Color.RED));
        circle.setStrokeWidth(4f);
        circle.setTag("Zona1");


        Circle circle2 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(-34.480523, -58.717237))
                .radius(1600)
                .strokeColor(Color.BLUE));
        circle2.setStrokeWidth(4f);
        circle2.setTag("Zona2");


        Circle circle3 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(-34.450193, -58.725039))
                .radius(1800)
                .strokeColor(Color.GREEN));
        circle2.setStrokeWidth(4f);
        circle2.setTag("Zona2");


        Circle circle4 = mMap.addCircle(new CircleOptions()
                .center(new LatLng(-34.469302, -58.653062))
                .radius(1500)
                .strokeColor(Color.YELLOW));
        circle2.setStrokeWidth(4f);
        circle2.setTag("Zona2");

        //funcion que revisa si un punto esta dentro del circulo zona 1

        float[] disResultado = new float[2];
        // LatLng pos = new LatLng(40.416775, -3.703790);
        LatLng pos = new LatLng(-34.470327, -58.683718);
        double lat = pos.latitude; //getLatitude
        double lng = pos.longitude;//getLongitude


        Location.distanceBetween( pos.latitude, pos.longitude,
                circle.getCenter().latitude,
                circle.getCenter().longitude,
                disResultado);

        if(disResultado[0] > circle.getRadius()){
            System.out.println("FUERAAAA ZONA 1" );
        } else {
            System.out.println("DENTROOO ZONA 1" );
        }

*/



    }
}
