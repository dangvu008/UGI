package dang.ugi.com.view.CuaHang;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dang.ugi.com.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentCuaHang extends Fragment {


    public FragmentCuaHang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cua_hang, container, false);
    }

}
