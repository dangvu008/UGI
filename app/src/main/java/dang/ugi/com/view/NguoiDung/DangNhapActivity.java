package dang.ugi.com.view.NguoiDung;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;

import dang.ugi.com.R;
import dang.ugi.com.adapter.ViewPaperAdapter;
import dang.ugi.com.presenter.NguoiDung.ImplNguoiDungPresenter;

public class DangNhapActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImplNguoiDungPresenter implPresenterDangNhap;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_dang_nhap);
        intent = getIntent();
        viewPager = (ViewPager) findViewById(R.id.viewPaper);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        implPresenterDangNhap = new ImplNguoiDungPresenter(this);
        StrictMode.setVmPolicy (new StrictMode.VmPolicy.Builder().detectAll().penaltyLog()
                .penaltyDeath().build());
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPaperAdapter adapter = new ViewPaperAdapter(getSupportFragmentManager());
            adapter.addFragment(new FragmentDangNhap(),getString(R.string.login));
          /*  if (intent.getStringExtra("capnhatnguoidung")==null){
                adapter.addFragment(new FragmentDangki(),getString(R.string.register));

            }else{
                adapter.addFragment(new FragmentDangki(),intent.getStringExtra("capnhatnguoidung"));
            }*/



        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
