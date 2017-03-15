package dang.ugi.com.view.CuaHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;

import dang.ugi.com.R;
import dang.ugi.com.adapter.LoaiCuaHangAdapter;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.LoaiCuaHang;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.model.Utils.Static_Id;
import dang.ugi.com.network.CuaHang.ConstantsCuaHang;
import dang.ugi.com.network.CuaHang.RequestInterfaceCuaHang;
import dang.ugi.com.network.CuaHang.ServerRequestCuaHang;
import dang.ugi.com.network.CuaHang.ServerResponseCuaHang;
import dang.ugi.com.network.RetrofitHandler;
import dang.ugi.com.presenter.CuaHang.ImpPresenterCuaHang;
import dang.ugi.com.presenter.NguoiDung.ImplNguoiDungPresenter;
import dang.ugi.com.view.TrangChu.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by DANG on 9/13/2016.
 */
public class ThemCuaHangActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editTextTenCuaHang,editTextDiaChi,editTextSDT;
    ImageView imgLogoCuaHang;
    Button btnLuuCuaHang,btnThoat;
    TextInputLayout validateTen;
    CoordinatorLayout layout_themCuaHang;
    Spinner spinnerLoaiCuaHang;

    String duongdanLogo = "";
    ImpPresenterCuaHang impPresenterCuaHang;
    LoaiCuaHangAdapter loaiCuaHangAdapter;
    int maLoaiCuaHang = 0  ;
    boolean ketqua;
    Intent intent;
    private ImplNguoiDungPresenter implPresenterDangNhap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_cua_hang);
        impPresenterCuaHang = new ImpPresenterCuaHang(this);
        implPresenterDangNhap = new ImplNguoiDungPresenter(this);
        addViews();
        addEvents();
        intent = getIntent();
        if (impPresenterCuaHang.layLoaiCuaHang().size()==0){
            themDuLieuLoaiCuaHang();
        }
        loadLoaiCuaHang();

    }

    private void themDuLieuLoaiCuaHang() {
        impPresenterCuaHang.themLoaiCuaHang(getString(R.string.QuanCaPhe));
        impPresenterCuaHang.themLoaiCuaHang(getString(R.string.QuanTraSua));
        impPresenterCuaHang.themLoaiCuaHang(getString(R.string.QuanNuoc));
        impPresenterCuaHang.themLoaiCuaHang(getString(R.string.QuanKhac));
    }

    private void loadLoaiCuaHang() {
        List<LoaiCuaHang> listLoaiCuaHang = impPresenterCuaHang.layLoaiCuaHang();
        loaiCuaHangAdapter = new LoaiCuaHangAdapter(listLoaiCuaHang,this);
        spinnerLoaiCuaHang.setAdapter(loaiCuaHangAdapter);
    }

    private void addEvents() {
        btnLuuCuaHang.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
        imgLogoCuaHang.setOnClickListener(this);
        editTextTenCuaHang.addTextChangedListener(new ThemCuaHangTextWatcher(editTextTenCuaHang));
        spinnerLoaiCuaHang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maLoaiCuaHang = (int) loaiCuaHangAdapter.getItemId(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void addViews() {
        editTextDiaChi = (EditText) findViewById(R.id.editextDiaChiCuaHang);
        editTextTenCuaHang = (EditText) findViewById(R.id.editTextTenCuaHang);
        editTextSDT = (EditText) findViewById(R.id.edittextSoDienThoaiCuaHang);
        imgLogoCuaHang = (ImageView) findViewById(R.id.imageViewLogoCuaHang);
        btnLuuCuaHang = (Button) findViewById(R.id.btnThemCuaHang);
        btnThoat = (Button) findViewById(R.id.btnThoatThemCuaHang);
        validateTen = (TextInputLayout) findViewById(R.id.input_layout_TenCuaHang);
        layout_themCuaHang = (CoordinatorLayout) findViewById(R.id.activity_them_CuaHang);
        spinnerLoaiCuaHang = (Spinner) findViewById(R.id.spinnerLoaiCuaHang);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnThoatThemCuaHang:
            {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
                break;
            case R.id.btnThemCuaHang:
                luuCuaHang();
                break;
            case R.id.imageViewLogoCuaHang:
                chonHinhAnhLogo();
                break;
        }
    }

    private void chonHinhAnhLogo() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Chọn lô gô nhà hàng !"), Static_Id.REQUEST_CHONLOGOCUAHANG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Static_Id.REQUEST_CHONLOGOCUAHANG&&resultCode==RESULT_OK){
            duongdanLogo = data.getData().toString();
            imgLogoCuaHang.setImageURI(data.getData());
        }
    }

    private void luuCuaHang() {
        if (!validateTenCuaHang()){
            int maCuahang = 0;
            CuaHang lastCuaHang = implPresenterDangNhap.lastIdCuaHang();
            if (lastCuaHang!=null)
                maCuahang  =lastCuaHang.getMaCuaHang() +1;
            else{
                maCuahang =1;
            }

            String tenCuahang= editTextTenCuaHang.getText().toString();
            String diaChi = editTextDiaChi.getText().toString();
            String sodienthoai = editTextSDT.getText().toString();
            maLoaiCuaHang = spinnerLoaiCuaHang.getSelectedItemPosition() + 1;
            CuaHang cuaHang = new CuaHang();
            cuaHang.setTenCuahang(tenCuahang);
            cuaHang.setMaLoaiCuaHang(maLoaiCuaHang);
            cuaHang.setDiaChi(diaChi);
            cuaHang.setSoDienThoai(sodienthoai);
            cuaHang.setLogo(duongdanLogo);
            cuaHang.setMaCuaHang(maCuahang);
            int maCuaHangInserted = (int) impPresenterCuaHang.themCuaHang(cuaHang);
           // themCuaHangToSerVer(cuaHang);
            if (maCuaHangInserted!=-1){
                impPresenterCuaHang.themCuaHangNguoiDung(maCuahang, PrefDangNhap.layNguoiDungHienTai(this).getMaNguoiDung());
                setResult(RESULT_OK);
                cuaHang.setMaCuaHang(maCuaHangInserted);
                PrefNhaHang.themCuaHangHienTai(cuaHang,this);
                Snackbar.make(layout_themCuaHang,"Thêm thành công !",Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(ThemCuaHangActivity.this, MainActivity.class);
                startActivity(intent);
            }else{
                PrefDangNhap.xoaNguoiDungHienTai(this);
                Snackbar.make(layout_themCuaHang,"Thêm thất  bại !",Snackbar.LENGTH_SHORT).show();
            }

        }
    }
    public boolean themCuaHangToSerVer(CuaHang cuaHang){
        Retrofit retrofit = RetrofitHandler.retrofit(ConstantsCuaHang.BASE_URL);
        RequestInterfaceCuaHang requestInterfaceCuaHang = retrofit.create(RequestInterfaceCuaHang.class);
        ServerRequestCuaHang request = new ServerRequestCuaHang();
        request.setCuaHang(cuaHang);
        request.setOperation(ConstantsCuaHang.REGISTER_OPERATION);
        Call<ServerResponseCuaHang> response = requestInterfaceCuaHang.operation(request);
        response.enqueue(new Callback<ServerResponseCuaHang>() {
            @Override
            public void onResponse(Call<ServerResponseCuaHang> call, Response<ServerResponseCuaHang> response) {
                String result = response.body().getResult();
                ketqua = Boolean.parseBoolean(result);
            }

            @Override
            public void onFailure(Call<ServerResponseCuaHang> call, Throwable t) {
                ketqua =false;
            }
        });
        return ketqua;
    }
    public boolean validateTenCuaHang(){
        if (editTextTenCuaHang.getText().toString().isEmpty()){
            validateTen.setError("Điền tên cửa hàng !");
            editTextTenCuaHang.requestFocus();
            return true;
        }else if (impPresenterCuaHang.kiemTratenCuahang(editTextTenCuaHang.getText().toString())){
            validateTen.setError(" tên cửa hàng đã tồn tại!");
            editTextTenCuaHang.requestFocus();
            return true;
        }else{
            validateTen.setErrorEnabled(false);
            return false;
        }
        
    }
    public class ThemCuaHangTextWatcher implements TextWatcher{
        View view;

        public ThemCuaHangTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.editTextTenCuaHang:{
                    validateTenCuaHang();
                }break;
            }
        }
    }
}
