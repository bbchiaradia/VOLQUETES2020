package frgp.utn.edu.com.volquetes.swipview.Cliente.Volquete;

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
public class SectionsPagerAdapterVolquete extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_4, R.string.tab_text_3};
    private int[]tabIcons ={R.drawable.alta, R.drawable.modificacion, R.drawable.baja } ;
    private final Context mContext;

    public SectionsPagerAdapterVolquete(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                AgregarVolqueteFragment agregar = new AgregarVolqueteFragment();
                return agregar;
            case 1:
                ModificarVolqueteFragment modificar = new ModificarVolqueteFragment();
                return modificar;
            case 2:
                ListarVolqueteFragment listar = new ListarVolqueteFragment();
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