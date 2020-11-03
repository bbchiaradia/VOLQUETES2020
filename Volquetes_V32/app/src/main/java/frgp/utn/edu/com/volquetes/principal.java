package frgp.utn.edu.com.volquetes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import frgp.utn.edu.com.volquetes.swipview.Cliente.Cliente.TabbedActivity;
import frgp.utn.edu.com.volquetes.swipview.Cliente.Reportes.reportes_principal;
import frgp.utn.edu.com.volquetes.swipview.Cliente.Reserva.TabbedActivityReserva;
import frgp.utn.edu.com.volquetes.swipview.Cliente.Volquete.TabbedActivityVolquete;

public class principal extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ImageButton btn_irAgenda = (ImageButton) findViewById(R.id.img_agenda);

        btn_irAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent abrir = new Intent(principal.this, TabbedActivity.class);
                startActivity(abrir);
                //FragmentManager fm = getSupportFragmentManager();
                //FragmentTransaction ft = fm.beginTransaction();
                //agrega el Fragment en el contenedor, en este caso el FrameLayout con id `FrameLayout`.
                //ft.add(R.id.nico, new MainActivity());
                //ft.commit();

            }
        });





        ImageButton btn_irReserva = (ImageButton) findViewById(R.id.img_reserva);

        btn_irReserva.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent abrir = new Intent(principal.this, TabbedActivityReserva.class);
                startActivity(abrir);
            }
        });


        ImageButton btn_irVolquete = (ImageButton) findViewById(R.id.img_volquete);

        btn_irVolquete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent abrir = new Intent(principal.this, TabbedActivityVolquete.class);
                startActivity(abrir);
            }
        });


        ImageButton btn_irMapa = (ImageButton) findViewById(R.id.img_mapa);

        btn_irMapa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent abrir = new Intent(principal.this, mapa.class);
                startActivity(abrir);
            }
        });


        ImageButton btn_irReportes = (ImageButton) findViewById(R.id.img_reportes);

        btn_irReportes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent abrir = new Intent(principal.this, reportes_principal.class);
                startActivity(abrir);
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }


    public void CerrarSesion(MenuItem item){
        Intent cerrar = new Intent(this, MainActivity.class);
        startActivity(cerrar);
    }

    public void irAgenda(Void v){
        Intent cerrar = new Intent(this, MainActivity.class);
        startActivity(cerrar);
    }


}
