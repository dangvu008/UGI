package dang.ugi.com.view.GoiMon;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dang.ugi.com.R;
import dang.ugi.com.asyntask.LoadGoiMonAsynTask;


public class FragmentGoiMon extends Fragment {
    View view;
    public FragmentGoiMon() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_goi_mon, container, false);
        new LoadGoiMonAsynTask(getActivity(),view).execute(2);
        addEvents();
        return view;
    }

    private void addEvents() {
    }

    @Override
    public void onResume() {
        super.onResume();
        new LoadGoiMonAsynTask(getActivity(),view).execute(2);
    }
}
