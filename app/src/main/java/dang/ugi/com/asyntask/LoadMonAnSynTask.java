package dang.ugi.com.asyntask;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import dang.ugi.com.R;
import dang.ugi.com.adapter.MonAnFragmentAdapter;

/**
 * Created by DANG on 3/23/2017.
 */

public class LoadMonAnSynTask extends AsyncTask<Void,Void,Void> {
    Context context;
    RecyclerView recyclerViewMonAnTheoLoai;
    View view;
    public LoadMonAnSynTask(View view) {
       this.view=view;
        recyclerViewMonAnTheoLoai = (RecyclerView) view.findViewById(R.id.recyerView_monan);
    }

    @Override
    protected Void doInBackground(Void... params) {
        publishProgress();
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        loadToRecyclerView();
    }
    private void loadToRecyclerView(){

        MonAnFragmentAdapter monAnFragmentAdapter = new MonAnFragmentAdapter(view.getContext());
        recyclerViewMonAnTheoLoai.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.VERTICAL,false));
        //recyclerViewMonAnTheoLoai.addItemDecoration(new GridSpacingItemDecoration(2, dpToPX(5), true));
        recyclerViewMonAnTheoLoai.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMonAnTheoLoai.setAdapter(monAnFragmentAdapter);
        recyclerViewMonAnTheoLoai.setHasFixedSize(true);
        monAnFragmentAdapter.notifyDataSetChanged();
    }
}
