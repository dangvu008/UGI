package dang.ugi.com.view.BanAn;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;
import dang.ugi.com.view.TrangChu.MainActivity;

/**
 * Created by DANG on 10/6/2016.
 */

public  class BanAnAsynTask extends AsyncTask<String,List<BanAn>,List<BanAn>> {
    List<BanAn>list;
    ImplPresenterBanAn implPresenterBanAn;
    Context context;
    FragmentBanAn fragmentBanAn;
    RecyclerView recyclerView;
    int maCuahang;
    public BanAnAsynTask(Context context) {
        this.context = context;
        implPresenterBanAn = new ImplPresenterBanAn(context);
        fragmentBanAn = (FragmentBanAn) ((AppCompatActivity)context).getSupportFragmentManager().findFragmentByTag(MainActivity.TAG_BAN);
    }

    @Override
    protected List<BanAn> doInBackground(String... strings) {
        String keySearch = strings[0];
        maCuahang = Integer.parseInt(strings[1]);
        list = implPresenterBanAn.timKiemBanAnBangTen(keySearch,maCuahang);
        publishProgress(list);
        return list;
    }

    @Override
    protected void onProgressUpdate(List<BanAn>... values) {
       // fragmentBanAn.recyclerView= recyclerView;
      //  fragmentBanAn.loadDataToRecyerView(values[0]);
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<BanAn> list) {
        super.onPostExecute(list);
    }
}
