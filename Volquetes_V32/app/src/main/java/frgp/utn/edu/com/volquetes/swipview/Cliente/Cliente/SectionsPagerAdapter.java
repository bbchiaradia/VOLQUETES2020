package frgp.utn.edu.com.volquetes.swipview.Cliente.Cliente;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import frgp.utn.edu.com.volquetes.R;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    private int[]tabIcons ={R.drawable.alta, R.drawable.modificacion, R.drawable.baja } ;
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AgregarClienteFragment agregar = new AgregarClienteFragment();
                return agregar;
            case 1:
                ModificarClienteFragment modificar = new ModificarClienteFragment();
                return modificar;
            case 2:
                ListarClienteFragment listar = new ListarClienteFragment();
                return listar;
        }
        return null;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 3;
    }







}