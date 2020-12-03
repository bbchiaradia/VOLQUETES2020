package frgp.utn.edu.com.volquetes.swipview.Cliente.Cliente;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import frgp.utn.edu.com.volquetes.MainActivity;
import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityAltaCliente;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityBorrarCliente;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityBuscarCliente;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityFormaPagoReserva;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityListarCliente;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityModificarCliente;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityZonaCliente;
import frgp.utn.edu.com.volquetes.entidad.FormaPago;
import frgp.utn.edu.com.volquetes.entidad.Zona;
import frgp.utn.edu.com.volquetes.swipview.Cliente.Reserva.TabbedActivityReserva;


public class TabbedActivity extends AppCompatActivity {
Spinner zona_alta;
    Spinner zona_modif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbedcliente);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
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


    public void Alta(View view) {


        zona_alta = (Spinner) findViewById(R.id.zona_alta);
        DataMainActivityZonaCliente task = new DataMainActivityZonaCliente(TabbedActivity.this, zona_alta);
        task.execute();

        final EditText nombre_alta = (EditText) findViewById(R.id.nombre_cliente_alta);
        final EditText direccion_alta = (EditText) findViewById(R.id.direccion_cliente_alta);
        final EditText email_alta = (EditText) findViewById(R.id.email_cliente_alta);
        final EditText cuit_alta = (EditText) findViewById(R.id.cuit_cliente_alta);
        final EditText cel_alta = (EditText) findViewById(R.id.celular_cliente_alta);
        final EditText tel_par_alta = (EditText) findViewById(R.id.tel_particular_cliente_alta);
        final EditText tel_lab_alta = (EditText) findViewById(R.id.tel_laboral_cliente_alta);
        final EditText codigo_cliente_alta = (EditText) findViewById(R.id.codigo_cliente_alta);
        final EditText latitud_cliente_alta = (EditText) findViewById(R.id.latitud_cliente);
        final EditText longitud_cliente_alta = (EditText) findViewById(R.id.longitud_cliente);





        Button btn_alta = (Button) findViewById(R.id.btn_agregar_cliente_alta);

        btn_alta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";

                if (nombre_alta.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo NOMBRE vacío \n";
                }
                if (direccion_alta.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo DIRECCION vacío \n";
                }
                if (codigo_cliente_alta.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo CODIGO vacío \n";
                }
                if  (cel_alta.getText().toString().isEmpty() && tel_lab_alta.getText().toString().isEmpty() && tel_par_alta.getText().toString().isEmpty()){
                    msj_toast_alta = msj_toast_alta  + "Tiene que ingresar al menos un n° telefónico \n";
                }else{

                    if ( cel_alta.getText().toString().isEmpty()==false && ValidarTelefono(cel_alta.getText().toString()) == false ) {
                        if(cel_alta.length()< 8 || cel_alta.length() > 15 ) {
                            msj_toast_alta = msj_toast_alta + "Tu telefono CELULAR debe contar con un mínimo y máximo de 8 y 15 caracteres respectivamente \n";
                        }
                    }

                    if ( tel_lab_alta.getText().toString().isEmpty()==false &&ValidarTelefono(tel_lab_alta.getText().toString()) == false ) {
                        if(tel_lab_alta.length()< 8 || tel_lab_alta.length() > 15 ) {
                            msj_toast_alta = msj_toast_alta + "Tu telefono LABORAL debe contar con un mínimo y máximo de 8 y 15 caracteres respectivamente \n";
                        }
                    }


                    if ( tel_par_alta.getText().toString().isEmpty()==false && ValidarTelefono(tel_par_alta.getText().toString()) == false ) {
                        if(tel_par_alta.length()< 8 || tel_par_alta.length() > 15 ) {
                            msj_toast_alta = msj_toast_alta + "Tu telefono PARTICULAR debe contar con un mínimo y máximo de 8 y 15 caracteres respectivamente \n";
                        }
                    }

                }

                if ( ValidarEmail(email_alta.getText().toString())== false ) {
                    msj_toast_alta = msj_toast_alta + "El Mail es invalido \n";
                }




                if ( ValidarCuit(cuit_alta.getText().toString())== false ) {
                    msj_toast_alta = msj_toast_alta + "El Cuit es invalido \n";
                }


                if (latitud_cliente_alta.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo LATITUD vacío \n";
                }

                if (longitud_cliente_alta.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo LONGITUD vacío \n";
                }


                Zona form = (Zona) zona_alta.getSelectedItem();
                String s = String.valueOf(form.getIdZona());



                if (msj_toast_alta.isEmpty()){
                     new DataMainActivityAltaCliente(TabbedActivity.this).execute(nombre_alta.getText().toString(), direccion_alta.getText().toString(), email_alta.getText().toString(), cuit_alta.getText().toString(), cel_alta.getText().toString(), tel_par_alta.getText().toString(), tel_lab_alta.getText().toString(),codigo_cliente_alta.getText().toString(), latitud_cliente_alta.getText().toString(), longitud_cliente_alta.getText().toString(),s.toString());
                    // Toast.makeText((getApplicationContext()), "se agregaria", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    public void BuscarCliente(View view) {
        final EditText codigo_cliente_modif = (EditText) findViewById(R.id.codigo_cliente_modif);
        Button btn_buscar = (Button) findViewById(R.id.btn_buscar_id_cliente_modif);
        final EditText nombre_modif = (EditText) findViewById(R.id.nombre_cliente_modif);
        final EditText direccion_modif = (EditText) findViewById(R.id.direccion_cliente_modif);
        final EditText email_modif = (EditText) findViewById(R.id.email_cliente_modif);
        final EditText cuit_modif = (EditText) findViewById(R.id.cuit_cliente_modif);
        final EditText cel_modif = (EditText) findViewById(R.id.celular_cliente_modif);
        final EditText tel_par_modif = (EditText) findViewById(R.id.tel_particular_cliente_modif);
        final EditText tel_lab_modif = (EditText) findViewById(R.id.tel_laboral_cliente_modif);
        final EditText latitud_cliente_modif = (EditText) findViewById(R.id.latitud_cliente_modif);
        final EditText longitud_cliente_modif = (EditText) findViewById(R.id.longitud_cliente_modif);
        Button btn_modif = (Button) findViewById(R.id.btn_modificar_cliente);


        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";

                if (codigo_cliente_modif.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo CODIGO vacío \n";
                }
                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityBuscarCliente(TabbedActivity.this, nombre_modif, direccion_modif, email_modif,cuit_modif, cel_modif, tel_par_modif,tel_lab_modif,latitud_cliente_modif,longitud_cliente_modif ).execute(codigo_cliente_modif.getText().toString());
                    zona_modif = (Spinner) findViewById(R.id.zona_modif);
                    DataMainActivityZonaCliente task = new DataMainActivityZonaCliente(TabbedActivity.this, zona_modif);
                    task.execute();
                    // Toast.makeText((getApplicationContext()), "se agregaria", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });




    }


    public void ModificarCliente(View view) {
        final EditText codigo_cliente_modif = (EditText) findViewById(R.id.codigo_cliente_modif);
        final EditText nombre_modif = (EditText) findViewById(R.id.nombre_cliente_modif);
        final EditText direccion_modif = (EditText) findViewById(R.id.direccion_cliente_modif);
        final EditText email_modif = (EditText) findViewById(R.id.email_cliente_modif);
        final EditText cuit_modif = (EditText) findViewById(R.id.cuit_cliente_modif);
        final EditText cel_modif = (EditText) findViewById(R.id.celular_cliente_modif);
        final EditText tel_par_modif = (EditText) findViewById(R.id.tel_particular_cliente_modif);
        final EditText tel_lab_modif = (EditText) findViewById(R.id.tel_laboral_cliente_modif);
        final EditText latitud_cliente_modif = (EditText) findViewById(R.id.latitud_cliente_modif);
        final EditText longitud_cliente_modif = (EditText) findViewById(R.id.longitud_cliente_modif);
        zona_modif = (Spinner) findViewById(R.id.zona_modif);
        Button btn_modif = (Button) findViewById(R.id.btn_modificar_cliente);


        btn_modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";


                Zona form = (Zona) zona_modif.getSelectedItem();
                String s = String.valueOf(form.getIdZona());


                if (nombre_modif.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo NOMBRE vacío \n";
                }
                if (direccion_modif.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo DIRECCION vacío \n";
                }
                if (codigo_cliente_modif.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo CODIGO vacío \n";
                }
                if  (cel_modif.getText().toString().isEmpty() && tel_lab_modif.getText().toString().isEmpty() && tel_par_modif.getText().toString().isEmpty()){
                    msj_toast_alta = msj_toast_alta  + "Tiene que ingresar al menos un n° telefónico \n";
                }else{

                    if ( cel_modif.getText().toString().isEmpty()==false && ValidarTelefono(cel_modif.getText().toString()) == false ) {
                        if(cel_modif.length()< 8 || cel_modif.length() > 15 ) {
                            msj_toast_alta = msj_toast_alta + "Tu telefono CELULAR debe contar con un mínimo y máximo de 8 y 15 caracteres respectivamente \n";
                        }
                    }

                    if (tel_lab_modif.getText().toString().isEmpty()==false && ValidarTelefono(tel_lab_modif.getText().toString()) == false ) {
                        if(tel_lab_modif.length()< 8 || tel_lab_modif.length() > 15 ) {
                            msj_toast_alta = msj_toast_alta + "Tu telefono LABORAL debe contar con un mínimo y máximo de 8 y 15 caracteres respectivamente \n";
                        }
                    }


                    if (tel_par_modif.getText().toString().isEmpty()==false && ValidarTelefono(tel_par_modif.getText().toString()) == false ) {
                        if(tel_par_modif.length()< 8 || tel_par_modif.length() > 15 ) {
                            msj_toast_alta = msj_toast_alta + "Tu telefono PARTICULAR debe contar con un mínimo y máximo de 8 y 15 caracteres respectivamente \n";
                        }
                    }

                }






                if ( ValidarEmail(email_modif.getText().toString())== false ) {
                    msj_toast_alta = msj_toast_alta + "El Mail es invalido \n";
                }

                if ( ValidarCuit(cuit_modif.getText().toString())== false ) {
                    msj_toast_alta = msj_toast_alta + "El Cuit es invalido \n";
                }

                if (latitud_cliente_modif.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo LATITUD vacío \n";
                }

                if (longitud_cliente_modif.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo LONGITUD vacío \n";
                }


                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityModificarCliente(TabbedActivity.this).execute(nombre_modif.getText().toString(), direccion_modif.getText().toString(), email_modif.getText().toString(), cuit_modif.getText().toString(), cel_modif.getText().toString(), tel_par_modif.getText().toString(), tel_lab_modif.getText().toString(),codigo_cliente_modif.getText().toString(), latitud_cliente_modif.getText().toString(), longitud_cliente_modif.getText().toString(),s.toString());
                    // Toast.makeText((getApplicationContext()), "se agregaria", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });




    }




    public void BorrarCliente(View view) {
        final EditText codigo_cliente_modif = (EditText) findViewById(R.id.codigo_cliente_modif);
        ImageButton btn_borrar = (ImageButton) findViewById(R.id.btn_borrar_cliente);


        btn_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";

                if (codigo_cliente_modif.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo CODIGO vacío \n";
                }
                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityBorrarCliente(TabbedActivity.this).execute(codigo_cliente_modif.getText().toString());
                    // Toast.makeText((getApplicationContext()), "se agregaria", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });




    }




    public void BuscarListarCliente(View view) {

        final EditText codigo_cliente_listar = (EditText) findViewById(R.id.codigo_cliente_listar);
        Button btn_buscar = (Button) findViewById(R.id.btn_buscar_cliente_listar);
        final ListView ListaCliente =(ListView) findViewById(R.id.ListaClientes);

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";

                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityListarCliente(TabbedActivity.this, ListaCliente).execute(codigo_cliente_listar.getText().toString());
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public boolean ValidarEmail(String email){
        Pattern Plantilla = null;
        Matcher Resultado = null;
        Plantilla = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        Resultado = Plantilla.matcher(email);
        if(Resultado.find()==true)
            return true;
        else {
            return false;
        }

    }

    public boolean ValidarTelefono(String telefono){
        Pattern Plantilla = null;
        Matcher Resultado = null;
        Plantilla = Pattern.compile("^[+]?[0-9]{10,13}$");
        Resultado = Plantilla.matcher(telefono);
        if(Resultado.find()==true)
            return true;
        else {
            return false;
        }

    }

    public boolean ValidarCuit(String telefono){
        Pattern Plantilla = null;
        Matcher Resultado = null;
        Plantilla = Pattern.compile("\\b(20|23|24|27|30|33|34)(\\D)?[0-9]{8}(\\D)?[0-9]");
        Resultado = Plantilla.matcher(telefono);
        if(Resultado.find()==true)
            return true;
        else {
            return false;
        }

    }




}


