package dang.ugi.com.view.MonAn;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.MonAnAdapter;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

import static dang.ugi.com.R.layout.fragment_danhsach_chitiet_mon_an_theo_loai;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentListMonAnTheoLoai.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FragmentListMonAnTheoLoai extends Fragment {

    RecyclerView recyclerViewDanhSachChiTiet;
    List<MonAn> list;
    ImpPresenterMonAn impPresenterMonAn;
    private OnFragmentInteractionListener mListener;

    public FragmentListMonAnTheoLoai() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(fragment_danhsach_chitiet_mon_an_theo_loai,container,false);
        impPresenterMonAn = new ImpPresenterMonAn(getActivity());
        recyclerViewDanhSachChiTiet = (RecyclerView) view.findViewById(R.id.recyclerView_listMonAnTheoLoai);
        Bundle bundle = getArguments();
        loadData(bundle);
        return view;
    }

    private void loadData(Bundle bundle) {
        if (bundle!=null){
            int maLoaiMOn = bundle.getInt("maLoai");
            list = impPresenterMonAn.listAllMonAnTheoMaLoaiChiTiet(maLoaiMOn);
            MonAnAdapter adapter = new MonAnAdapter(getActivity(),list,R.layout.custom_cardview_monan,1);
            StaggeredGridLayoutManager gridLayoutManager =
                    new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            gridLayoutManager.scrollToPosition(0);
            recyclerViewDanhSachChiTiet.setLayoutManager(gridLayoutManager);
            //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPX(5), true));
            recyclerViewDanhSachChiTiet.setItemAnimator(new SlideInUpAnimator());
            recyclerViewDanhSachChiTiet.setAdapter(adapter);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
