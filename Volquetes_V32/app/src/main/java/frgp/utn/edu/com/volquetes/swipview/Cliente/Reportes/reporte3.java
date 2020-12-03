package frgp.utn.edu.com.volquetes.swipview.Cliente.Reportes;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import frgp.utn.edu.com.volquetes.R;

public class reporte3 extends AppCompatActivity {

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

    public void FechaDesdeActivar (View view){
        bfechaDesde = (Button) findViewById(R.id.btn_fechaDesdeReporte3);
        efechaDesde= (EditText) findViewById(R.id.fecha_reserva_desdeReporte3);
    //    bfechaDesde.setOnClickListener(this);
    }

    public void FechaHastaActivar (View view){
        bfechahasta = (Button) findViewById(R.id.btn_fechaHastaReporte3);
        efechaHasta= (EditText) findViewById(R.id.fecha_reserva_hastaeporte3);
    //    bfechahasta.setOnClickListener(this);
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
