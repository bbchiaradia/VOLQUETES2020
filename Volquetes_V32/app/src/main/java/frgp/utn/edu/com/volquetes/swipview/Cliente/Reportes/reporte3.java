package frgp.utn.edu.com.volquetes.swipview.Cliente.Reportes;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import frgp.utn.edu.com.volquetes.R;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityReporte3ClienteReservasFecha;
import frgp.utn.edu.com.volquetes.conexion.DataMainActivityReporte4ClienteReservasMonto;

import static java.security.AccessController.getContext;

public class reporte3 extends AppCompatActivity implements View.OnClickListener {

    Button bfechaDesde;
    Button bfechahasta;
    EditText efechaDesde;
    EditText efechaHasta;
    private int dia,mes,ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte3);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





    }

    public void  BuscarClienteReporte3(View view){

        Button btn_buscar = (Button) findViewById(R.id.btn_buscar_cliente_listar_reporte3);
        final EditText fecha_desde = (EditText) findViewById(R.id.fecha_reserva_desdeReporte3);
        final EditText fecha_hasta = (EditText) findViewById(R.id.fecha_reserva_hastaeporte3);
        final ListView ListaReserva =(ListView) findViewById(R.id.ListaClientesReservasReporte3);

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msj_toast_alta = "";

                if (fecha_desde.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo FECHA DESDE vacío \n";
                }
                if (fecha_hasta.getText().toString().isEmpty()) {
                    msj_toast_alta = msj_toast_alta + "Campo FECHA HASTA vacío \n";
                }
                if (msj_toast_alta.isEmpty()){
                    new DataMainActivityReporte3ClienteReservasFecha(reporte3.this, ListaReserva).execute(fecha_desde.getText().toString(),fecha_hasta.getText().toString());
                }else{
                    Toast.makeText((getApplicationContext()), msj_toast_alta, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void FechaDesdeActivar (View view){
        bfechaDesde = (Button) findViewById(R.id.btn_fechaDesdeReporte3);
        efechaDesde= (EditText) findViewById(R.id.fecha_reserva_desdeReporte3);
        bfechaDesde.setOnClickListener(this);
    }

    public void FechaHastaActivar (View view){
        bfechahasta = (Button) findViewById(R.id.btn_fechaHastaReporte3);
        efechaHasta= (EditText) findViewById(R.id.fecha_reserva_hastaeporte3);
       bfechahasta.setOnClickListener(this);
    }



    public void onClick(View v) {
        if(v == bfechaDesde){
            final Calendar c = Calendar.getInstance();
            dia= c.get(Calendar.DAY_OF_MONTH);
            mes= c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    // efecha.setText(dayOfMonth + "/" + (month+1)+"/"+year);
                    efechaDesde.setText(year + "-" + (month+1)+"-"+dayOfMonth + " 00:00:00");


                }
            },dia,mes,ano
            );
            datePickerDialog.show();

        }
        if(v == bfechahasta){
            final Calendar c = Calendar.getInstance();
            dia= c.get(Calendar.DAY_OF_MONTH);
            mes= c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    // efecha.setText(dayOfMonth + "/" + (month+1)+"/"+year);
                    efechaHasta.setText(year + "-" + (month+1)+"-"+dayOfMonth + " 00:00:00");


                }
            },dia,mes,ano
            );
            datePickerDialog.show();

        }

    }






}
