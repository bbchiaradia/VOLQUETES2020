package frgp.utn.edu.com.volquetes.swipview.Cliente.Cliente;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import frgp.utn.edu.com.volquetes.R;


public class ListarClienteFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listar_cliente2, container, false);
    }

}
