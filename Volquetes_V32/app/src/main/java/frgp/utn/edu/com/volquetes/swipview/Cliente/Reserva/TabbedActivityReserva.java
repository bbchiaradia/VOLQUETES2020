package frgp.utn.edu.com.volquetes.swipview.Cliente.Reserva;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

import frgp.utn.edu.com.volquetes.MainActivity;
import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityAltaReserva;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityBuscarClienteReserva;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityFormaPagoReserva;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityListarBorrarReserva;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityListarFinalizarReserva;
import frgp.utn.edu.com.volquetes.entidad.FormaPago;


public class TabbedActivityReserva extends AppCompatActivity implements View.OnClickListener {
    private ListView lvClientes;
    Button bfecha;
    EditText efecha;
    Spinner forma_pago_alta;
    private int dia,mes,ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbedreserva);
        SectionsPagerAdapterReserva sectionsPagerAdapter = new SectionsPagerAdapterReserva(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pagerReserva);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);

        return true;

    }


    public void BuscarListarReservas(View view) {
        //DataMainActivityFormaPagoReserva task = new DataMainActivityFormaPagoReserva(lvClientes,this);
        final EditText codigo_cliente_listar_reserva = (EditText) findViewById(R.id.codigo_cliente_listar_reserva);
        Button btn_buscar = (Button) findViewById(R.id.btn_buscar_reserva_listar);
        final ListView ListaReserva =(ListView) findViewById(R.id.ListaFinalizarReservas);

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";

                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityListarFinalizarReserva(TabbedActivityReserva.this, ListaReserva).execute(codigo_cliente_listar_reserva.getText().toString());
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }






    public void BuscarBorrarReservas(View view) {


        final EditText codigo_cliente_borrar_reserva = (EditText) findViewById(R.id.codigo_cliente_borrar_reserva);
        Button btn_buscar = (Button) findViewById(R.id.btn_buscar_reserva_borrar);
        final ListView ListaReserva =(ListView) findViewById(R.id.ListaBorrarReservas);


        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";

                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityListarBorrarReserva(TabbedActivityReserva.this, ListaReserva).execute(codigo_cliente_borrar_reserva.getText().toString());
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    public void  BuscarClienteReserva(View view){
        final EditText codigo_cliente_reserva = (EditText) findViewById(R.id.codigo_cliente_reserva);
        final EditText nombre_cliente_reserva = (EditText) findViewById(R.id.nombre_cliente_reserva);
        Button btn_buscar = (Button) findViewById(R.id.btn_buscar_id_cliente_reserva);

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";

                if (codigo_cliente_reserva.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo CODIGO vacío \n";
                }
                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityBuscarClienteReserva(TabbedActivityReserva.this, nombre_cliente_reserva).execute(codigo_cliente_reserva.getText().toString());
                    forma_pago_alta = (Spinner) findViewById(R.id.formaPago);
                    DataMainActivityFormaPagoReserva task = new DataMainActivityFormaPagoReserva(TabbedActivityReserva.this, forma_pago_alta);
                    task.execute();
                     //forma_pago_alta.setAdapter(adapter);
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void  GuardarReserva(View view){
        final EditText codigo_cliente_reserva = (EditText) findViewById(R.id.codigo_cliente_reserva);
        final EditText precio_reserva = (EditText) findViewById(R.id.precio_reserva);
        final EditText fecha_reserva = (EditText) findViewById(R.id.fecha_reserva);
        Button btn_agregar_reserva = (Button) findViewById(R.id.btn_agregar_reserva);
        forma_pago_alta = (Spinner) findViewById(R.id.formaPago);




        btn_agregar_reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";

                if (codigo_cliente_reserva.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo codigo cliente vacío \n";
                }
                if (precio_reserva.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo precio reserva vacío \n";
                }
                if (fecha_reserva.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo fecha reserva vacío \n";
                }

                FormaPago form = (FormaPago) forma_pago_alta.getSelectedItem();
                String s = String.valueOf(form.getIdFormaPago());




                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityAltaReserva(TabbedActivityReserva.this).execute(codigo_cliente_reserva.getText().toString(),precio_reserva.getText().toString(), s ,fecha_reserva.getText().toString(), s.toString());
                    // Toast.makeText((getApplicationContext()), "se agregaria", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void FechaActivar (View view){
        bfecha = (Button) findViewById(R.id.btn_fecha);
        efecha= (EditText) findViewById(R.id.fecha_reserva);
        bfecha.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == bfecha){
            final Calendar c = Calendar.getInstance();
            dia= c.get(Calendar.DAY_OF_MONTH);
            mes= c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                   // efecha.setText(dayOfMonth + "/" + (month+1)+"/"+year);
                    efecha.setText(year + "-" + (month+1)+"-"+dayOfMonth + " 00:00:00");


                }
            },dia,mes,ano
            );
            datePickerDialog.show();

        }
    }





    public void CerrarSesion(MenuItem item) {
        Intent cerrar = new Intent(this, MainActivity.class);
        startActivity(cerrar);
    }


}










