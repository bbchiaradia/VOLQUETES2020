package frgp.utn.edu.com.volquetes.swipview.Cliente.Volquete;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import frgp.utn.edu.com.volquetes.MainActivity;
import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityAltaVolquete;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityBorrarVolquete;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityListarVolquete;


public class TabbedActivityVolquete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbedvolquete);
        SectionsPagerAdapterVolquete sectionsPagerAdapter = new SectionsPagerAdapterVolquete(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pagerVolquete);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }


    public void CerrarSesion(MenuItem item) {
        Intent cerrar = new Intent(this, MainActivity.class);
        startActivity(cerrar);
    }


    public void AltaVolquete(View view) {

        final EditText codigo_volquete_alta = (EditText) findViewById(R.id.codigo_volquete_alta);
        Button btn_alta = (Button) findViewById(R.id.btn_agregar_volquete_alta);

        btn_alta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";
                if (codigo_volquete_alta.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo CODIGO vacío \n";
                }
                if (msj_toast_alta.isEmpty()){
                     new DataMainActivityAltaVolquete(TabbedActivityVolquete.this).execute(codigo_volquete_alta.getText().toString());
                    // Toast.makeText((getApplicationContext()), "se agregaria", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    public void BorrarVolquete(View view) {

        final EditText codigo_volquete_modif = (EditText) findViewById(R.id.codigo_volquete_modif);
        Button btn_borrar = (Button) findViewById(R.id.btn_borrar_volquete_modif);

        btn_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";
                if (codigo_volquete_modif.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo CODIGO vacío \n";
                }
                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityBorrarVolquete(TabbedActivityVolquete.this).execute(codigo_volquete_modif.getText().toString());
                    // Toast.makeText((getApplicationContext()), "se agregaria", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }




    public void BuscarListarVolquete(View view) {

        final EditText codigo_volquete_listar = (EditText) findViewById(R.id.codigo_volquete_listar);
        Button btn_buscar = (Button) findViewById(R.id.btn_buscar_volquete_listar);
        final ListView ListaVolquete =(ListView) findViewById(R.id.ListaVolquetes);

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";
                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityListarVolquete(TabbedActivityVolquete.this, ListaVolquete).execute(codigo_volquete_listar.getText().toString());
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



}


