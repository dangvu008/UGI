package dang.ugi.com.view.GoiMon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.BuildConfig;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.ChiTietGoiMonAdapter;
import dang.ugi.com.asyntask.LoadFormGoiMonAsynTask;
import dang.ugi.com.asyntask.LoadTongTienGoiMonAsyntask;
import dang.ugi.com.asyntask.TimkiemTenBanAsynTask;
import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.model.Entities.ChiTietGoiMon;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.GoiMon;
import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.Utils.FormatData;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.model.Utils.Static_Id;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;
import dang.ugi.com.presenter.ChiTietGoiMon.ImplChiTietGoiMonPresenter;
import dang.ugi.com.presenter.GoiMon.ImplGoiMonPresenter;
import dang.ugi.com.view.ChiTietGoiMon.GoiMonThemMonDialog;
import dang.ugi.com.view.TrangChu.IFormActivity;

import static dang.ugi.com.R.id.editText_danggoimon_tienThem;
import static dang.ugi.com.model.Utils.Static_Id.REQUEST_CODE_GOIMON_THEMMON;

public class GoiMonActivity extends Activity implements IFormActivity, View.OnClickListener {

    private AutoCompleteTextView autoCompleteTextViewTenBan;
    private RecyclerView recyclerView_danggoimon_MonDaGoi;
    private TextView textView_danggoimon_TongTien,textView_danggoimon_thongbaorong;
    private Button btn_danggoimon_xacNhan, btn_danggoimon_thoat, btn_danggoimon_themmon, btn_danggoimon_thanhtoan;
    private TextInputLayout validate_layout_tenBan;
    private Context context;
    private int maBanSelected;
    private List<ChiTietGoiMon> listChiTietGoiMon;
    private int maCuaHang;
    private int maNguoiDung;
    private GoiMon goiMon;
    private ImplPresenterBanAn implPresenterBanAn;
    private ImplGoiMonPresenter implGoiMonPresenter;
    private ImplChiTietGoiMonPresenter implChiTietGoiMonPresenter;
    private float tongTien;
    private Intent intentLoad;
    private BanAn banAn;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goi_mon);
        context = this;
        loadControls();
        autoCompleteTextViewTenBan.setThreshold(1);
        implPresenterBanAn = new ImplPresenterBanAn(this);
        implGoiMonPresenter = new ImplGoiMonPresenter(this);
        implChiTietGoiMonPresenter = new ImplChiTietGoiMonPresenter(this);
        CuaHang cuaHangHT = PrefNhaHang.layCuaHangHienTai(context);
        NguoiDung nguoiDungHT = PrefDangNhap.layNguoiDungHienTai(context);
        if (cuaHangHT!=null && nguoiDungHT!=null){
            maCuaHang = cuaHangHT.getMaCuaHang();
            maNguoiDung = nguoiDungHT.getMaNguoiDung();
        }

        implGoiMonPresenter.xoaLichSuGoiMonTam();
        intentLoad = getIntent();
         maBanSelected= intentLoad.getIntExtra("maBan",1);
         if (intentLoad.getExtras()!=null){
             banAn = implPresenterBanAn.layBanAnbyMaBanAn(maBanSelected);
             autoCompleteTextViewTenBan.setText(banAn.getTenBanAn());
             goiMon = implGoiMonPresenter.layGoiMonDangGoi(maBanSelected);
            if (goiMon!=null){
                new LoadFormGoiMonAsynTask(context).execute(goiMon.getMaGoiMon());
            }
         }
        addEvents();
      /*  if (BuildConfig.DEBUG)
        {
            System.gc();
        }

*/

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
     //   client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog()
                .penaltyDeath().build());
    }

    private void loadFormGoiMon(GoiMon goiMon) {
        listChiTietGoiMon = implChiTietGoiMonPresenter.listChiTietGoiMon(goiMon.getMaGoiMon());
        if (listChiTietGoiMon==null){
            recyclerView_danggoimon_MonDaGoi.setVisibility(View.GONE);
            textView_danggoimon_thongbaorong.setVisibility(View.VISIBLE);
        }else{
            textView_danggoimon_thongbaorong.setVisibility(View.GONE);
            recyclerView_danggoimon_MonDaGoi.setVisibility(View.VISIBLE);
        }

        ChiTietGoiMonAdapter chiTietGoiMonAdapter = new ChiTietGoiMonAdapter(this,listChiTietGoiMon);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        textView_danggoimon_TongTien.setText(FormatData.formatMoneyVietNam(goiMon.getTongTien()));
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_danggoimon_MonDaGoi.setLayoutManager(layoutManager);
        recyclerView_danggoimon_MonDaGoi.setAdapter(chiTietGoiMonAdapter);
        chiTietGoiMonAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadControls() {
        autoCompleteTextViewTenBan = (AutoCompleteTextView) findViewById(R.id.autocomplete_danggoimon_tenban);
        recyclerView_danggoimon_MonDaGoi = (RecyclerView) findViewById(R.id.recycler_danggoimon_listMonDangGoi);
        textView_danggoimon_TongTien = (TextView) findViewById(R.id.textview_DangGoiMon_TongTien);
        btn_danggoimon_xacNhan = (Button) findViewById(R.id.btn_danggoimon_xacNhan);
        btn_danggoimon_themmon = (Button) findViewById(R.id.btn_danggoimon_themMon);
        btn_danggoimon_thanhtoan = (Button) findViewById(R.id.btn_danggoimon_ThanhToan);
        btn_danggoimon_thoat = (Button) findViewById(R.id.btn_danggoimon_thoat);
        validate_layout_tenBan = (TextInputLayout) findViewById(R.id.layout_validate_goimon_autocomplete_TenBan);
        textView_danggoimon_thongbaorong = (TextView) findViewById(R.id.textview_DangGoiMon_thongbaorong);
    }

    @Override
    public void loadDataEdit() {

    }

    @Override
    public void luuDuLieu() {

        if (!validateTenBan()) {
            return;
        } else {
            tongTien =  Float.parseFloat(textView_danggoimon_TongTien.getText().toString().replace(".",""));
            goiMon.setTongTien(tongTien);
            goiMon.setTinhTrang(0);
            if (implGoiMonPresenter.capNhatGoiMon(goiMon) != -1) {
                setResult(Activity.RESULT_OK, intentLoad);
                implPresenterBanAn.capNhatTrangThaiBan(maBanSelected, 1);
                finish();
            } else {
                implGoiMonPresenter.xoaGoiMon(goiMon.getMaGoiMon());
                implChiTietGoiMonPresenter.xoaChiTietGoiMon(goiMon.getMaGoiMon());
                implPresenterBanAn.capNhatTrangThaiBan(maBanSelected, 0);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Static_Id.REQUEST_CODE_GOIMON_THEMMON && resultCode == Activity.RESULT_OK) {
                 int magoimon = data.getIntExtra("MAGOIMON",0);
                new LoadFormGoiMonAsynTask(context).execute(magoimon);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        GoiMon goiMonDG = implGoiMonPresenter.layGoiMonDangGoi(maBanSelected);
        if (goiMonDG!=null)
            loadFormGoiMon(goiMonDG);
    }

    @Override
    public void addEvents() {
        autoCompleteTextViewTenBan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (autoCompleteTextViewTenBan.getText().length() == 0) {
                    new TimkiemTenBanAsynTask((Activity)view.getContext(),0).execute("", "");
                }
            }
        });
        autoCompleteTextViewTenBan.addTextChangedListener(new GoiMonTextwatcher(autoCompleteTextViewTenBan));
        autoCompleteTextViewTenBan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout layout = (LinearLayout) view;
                TextView textViewTenBan = (TextView) layout.getChildAt(0);
                TextView textViewMaBan = (TextView) layout.getChildAt(1);
                maBanSelected = Integer.parseInt(textViewMaBan.getText().toString());
                autoCompleteTextViewTenBan.setText(textViewTenBan.getText().toString());
                goiMon = implGoiMonPresenter.layGoiMonDangGoi(maBanSelected);
                if (goiMon!=null) {
                    new LoadFormGoiMonAsynTask(context).execute(goiMon.getMaGoiMon());
                }
            }
        });
        btn_danggoimon_xacNhan.setOnClickListener(this);
        btn_danggoimon_themmon.setOnClickListener(this);
        btn_danggoimon_thoat.setOnClickListener(this);

    }
    private GoiMon taoMoiGoiMon(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,1);
        cal.add(Calendar.HOUR,2);
        Date date = cal.getTime();
        goiMon = new GoiMon();
        goiMon.setMaCuaHang(maCuaHang);
        goiMon.setMaBan(maBanSelected);
        goiMon.setMaNguoiDungGoi(maNguoiDung);
        goiMon.setThoiGianBatDau(date);
        goiMon.setTinhTrang(-1);
        int goiMonIdInserted = (int) implGoiMonPresenter.themTamGoiMon(goiMon);
        goiMon.setMaGoiMon(goiMonIdInserted);
        return goiMon;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_danggoimon_themMon: {
                chuyenManHinhThemMon();
            }
            break;
            case R.id.btn_danggoimon_thoat: {
                finish();
            }
            break;
            case R.id.btn_danggoimon_xacNhan: {
                luuDuLieu();
            }
            break;
        }
    }

    private void chuyenManHinhThemMon() {
        /*DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String thoigianGoi = format.format(cal.getTime());*/
        if (!validateTenBan()) {
            return;
        } else {
            if (BuildConfig.DEBUG) {
                System.gc();
            }
           GoiMon mGoiMon = implGoiMonPresenter.layGoiMonDangGoi(maBanSelected);
            if (mGoiMon==null){
             mGoiMon = taoMoiGoiMon();
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable("bGoiMon", mGoiMon);
            Intent intent = new Intent(GoiMonActivity.this, GoiMonThemMonDialog.class);
            intent.putExtra("GOIMON", bundle);
           intent.putExtra("requestcode", REQUEST_CODE_GOIMON_THEMMON);
            startActivityForResult(intent, REQUEST_CODE_GOIMON_THEMMON);
        }
    }



    private void thanhToan() {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }


    private boolean validateTenBan() {
        boolean kiemtra;
        if (autoCompleteTextViewTenBan.getText().length() == 0) {
            validate_layout_tenBan.setError(getString(R.string.bancangoimon));
            autoCompleteTextViewTenBan.requestFocus();
            kiemtra = false;
        } else {
            validate_layout_tenBan.setErrorEnabled(false);
            kiemtra = true;
        }
        return kiemtra;
    }

    public class GoiMonTextwatcher implements TextWatcher {

        View view;

        public GoiMonTextwatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            switch (view.getId()) {
                case R.id.autocomplete_danggoimon_tenban: {
                    String s = charSequence.toString();
                    if (s.length() != 0) {
                        new TimkiemTenBanAsynTask(context,0).execute(s);
                    }
                }
                break;
                case editText_danggoimon_tienThem: {
                    if (goiMon != null) {
                        new LoadTongTienGoiMonAsyntask(context).execute(goiMon.getMaGoiMon());
                    }
                }
                break;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.autocomplete_danggoimon_tenban: {
                    validateTenBan();
                }
                break;
            }
        }
    }


}
