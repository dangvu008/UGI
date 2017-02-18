package dang.ugi.com.view.NguoiDung;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dang.ugi.com.asyntask.LoadNguoiDungAsynTask;
import dang.ugi.com.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNguoiDung extends Fragment {


    public FragmentNguoiDung() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_nguoi_dung, container, false);
        new LoadNguoiDungAsynTask(getActivity(),view).execute("");
        return view;
    }

}
