package dang.ugi.com.view.MonAn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;

import java.util.List;
import java.util.regex.Pattern;

import dang.ugi.com.view.TrangChu.IFormActivity;
import dang.ugi.com.R;
import dang.ugi.com.adapter.LoaiMonAnAdapterSpinner;
import dang.ugi.com.model.Entities.Gia;
import dang.ugi.com.model.Entities.LoaiMon;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.model.Utils.Static_Id;
import dang.ugi.com.presenter.CuaHang.ImpPresenterCuaHang;
import dang.ugi.com.presenter.LoaiMonAn.ImplLoaiMonAnPresenter;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;

import static dang.ugi.com.model.Utils.Static_Id.REQUEST_CODE_CHONHINHANH_MONAN;
import static dang.ugi.com.model.Utils.Static_Id.REQUEST_THEMLOAIMON;

public class ThemMonActivity extends AppCompatActivity implements IFormActivity,
        View.OnClickListener{

    EditText editTextGia;
    ImageView imageViewHinhAnhMon,imageViewThemLoaiMon;
    MultiAutoCompleteTextView autoCompleteTextViewTenMon;
    TextInputLayout validate_TenMon,validate_Gia;
    Button btnThemMon,btnThoatThemMon;
    Spinner spinnerLoaiMon;
    CheckBox cbThemVaoThucDon;
    private String duongDanHinhAnhMon;
    ImpPresenterMonAn impPresenterMonAn;
    ImplLoaiMonAnPresenter implLoaiMonAnPresenter;
    ImpPresenterCuaHang impPresenterCuaHang;
    List<LoaiMon> listLoaiMon;
    LoaiMonAnAdapterSpinner loaiMonAnAdapterSpinner;
    Intent intent;
    int maLoaiMonSelected;
    String tenMon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon);
        implLoaiMonAnPresenter = new ImplLoaiMonAnPresenter(this);
        impPresenterMonAn = new ImpPresenterMonAn(this);
        impPresenterCuaHang = new ImpPresenterCuaHang(this);
        loadControls();
        addEvents();
        loadLoaiMon();
        intent = getIntent();
        if (intent.getExtras()!=null){
            loadDataEdit();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnThemMon:
                luuDuLieu();
                break;
            case R.id.btnThoatThemMon:
                finish();
                break;
            case R.id.imageView_chuyen_themLoaiMon:
               chuyenManHinhThemLoaiMon();
                break;
            case R.id.imageViewHinhAnhMon:
                chonHinhAnhMonAn();
                break;
        }
    }

    private void chuyenManHinhThemLoaiMon() {
        intent = new Intent(ThemMonActivity.this,ThemLoaiMonActivity.class);
        startActivityForResult(intent, Static_Id.REQUEST_THEMLOAIMON);
    }

    private void chonHinhAnhMonAn() {
        intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,getString(R.string.ChonHinhAnhMonAn)),Static_Id.REQUEST_CODE_CHONHINHANH_MONAN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_CHONHINHANH_MONAN && resultCode==RESULT_OK){
            duongDanHinhAnhMon = data.getData().toString();
            imageViewHinhAnhMon.setImageURI(data.getData());
        }if (requestCode==REQUEST_THEMLOAIMON && resultCode==RESULT_OK){
            loadLoaiMon();
        }
    }

    private void loadLoaiMon() {
        listLoaiMon = implLoaiMonAnPresenter.listLoaiMon();
        loaiMonAnAdapterSpinner = new LoaiMonAnAdapterSpinner(this,R.layout.custom_spinner_loaimonan,listLoaiMon);
        spinnerLoaiMon.setAdapter(loaiMonAnAdapterSpinner);
        loaiMonAnAdapterSpinner.notifyDataSetChanged();

    }
    @Override
    public void loadControls() {
        editTextGia = (EditText) findViewById(R.id.edit_GiaMon);
        imageViewHinhAnhMon = (ImageView) findViewById(R.id.imageViewHinhAnhMon);
        imageViewThemLoaiMon = (ImageView) findViewById(R.id.imageView_chuyen_themLoaiMon);
        autoCompleteTextViewTenMon = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoTenMon);
        spinnerLoaiMon = (Spinner) findViewById(R.id.spinnerLoaiMon);
        btnThemMon = (Button) findViewById(R.id.btnThemMon);
        btnThoatThemMon = (Button) findViewById(R.id.btnThoatThemMon);
        cbThemVaoThucDon = (CheckBox) findViewById(R.id.checkBoxThemVaoThucDon);
        validate_TenMon = (TextInputLayout) findViewById(R.id.input_layout_TenMon);
        validate_Gia = (TextInputLayout) findViewById(R.id.input_layout_Gia);
    }

    @Override
    public void loadDataEdit() {
        Bundle bundle = intent.getBundleExtra("bMonAn");
        MonAn monAn = (MonAn) bundle.getSerializable("MONAN");
        double gia = impPresenterMonAn.layGiaMonTheoMaMon(monAn.getMaMonAn());
        autoCompleteTextViewTenMon.setText(monAn.getTenMonAn());
         editTextGia.setText(String.valueOf(gia));
        imageViewHinhAnhMon.setImageURI(Uri.parse(monAn.getHinhAnh()));


    }

    @Override
    public void luuDuLieu() {
        int maNguoiDung = PrefDangNhap.layNguoiDungHienTai(this).getMaNguoiDung();
        tenMon = autoCompleteTextViewTenMon.getText().toString();
        // NumberFormat tien = NumberFormat.getCurrencyInstance();
      /*  Locale vietnam = new Locale("vi","VN");
        NumberFormat format = NumberFormat.getCurrencyInstance(vietnam);
        String strGia = format.format(editTextGia.getText().toString());*/
       /* NumberFormat format = new DecimalFormat("###,###");
        String strGia = format.format(Double.parseDouble(editTextGia.getText().toString()));*/
        int maCuaHang = 0;
        maLoaiMonSelected = listLoaiMon.get(spinnerLoaiMon.getSelectedItemPosition()).getMaLoaiMon();
        float gia = Float.valueOf(editTextGia.getText().toString());
        if (cbThemVaoThucDon.isChecked()){
            maCuaHang = impPresenterCuaHang.layMaCuaHangByMaNguoiDung(maNguoiDung);
        }
        if (validateTenMon() && validateGia()){
            MonAn monAn = new MonAn();
            monAn.setTenMonAn(tenMon);
            monAn.setHinhAnh(duongDanHinhAnhMon);
            monAn.setMaLoaiMon(maLoaiMonSelected);
            monAn.setGiaThamKhao(gia);
            int maMonInserted = (int) impPresenterMonAn.themMonAn(monAn);
            if (maMonInserted!=-1){
                Gia objGia = new Gia();
                objGia.setMaCuaHang(maCuaHang);
                objGia.setMaMon(maMonInserted);
                objGia.setGia(gia);
                if (impPresenterMonAn.themBangGia(objGia)!=-1){
                    setResult(RESULT_OK);
                    finish();
                }
            }
        }

    }
    public boolean validateTenMon(){
        tenMon = autoCompleteTextViewTenMon.getText().toString();
        if (tenMon.isEmpty()){
            validate_TenMon.setError(getString(R.string.NhapTenMon));
            autoCompleteTextViewTenMon.requestFocus();
            return false;
        }if (impPresenterMonAn.kiemTraTenMonTonTai(tenMon)){
            validate_TenMon.setError(getString(R.string.TenMonTonTai));
            autoCompleteTextViewTenMon.requestFocus();
            return false;
        }else{
            validate_TenMon.setErrorEnabled(false);
            return true;
        }
    }
    public boolean validateGia(){
        String strGia = editTextGia.getText().toString();
        if (strGia.isEmpty()){
            validate_Gia.setError(getString(R.string.NhapGia));
            editTextGia.requestFocus();
            return false;
        }else if (!checkNumber(strGia)){
            validate_Gia.setError(getString(R.string.GiaLaSoDuong));
            editTextGia.requestFocus();
            return false;
        }else{
            validate_Gia.setErrorEnabled(false);
            return true;
        }
    }
    @Override
    public void addEvents() {
        imageViewThemLoaiMon.setOnClickListener(this);
        btnThoatThemMon.setOnClickListener(this);
        btnThemMon.setOnClickListener(this);
        imageViewHinhAnhMon.setOnClickListener(this);
        editTextGia.addTextChangedListener(new ThemMonTextWatcher(editTextGia));
        autoCompleteTextViewTenMon.addTextChangedListener(new ThemMonTextWatcher(autoCompleteTextViewTenMon));
    }
    private boolean checkNumber(String gia){
        Pattern pattern = Pattern.compile("^\\d{1,10}$");
        return pattern.matcher(gia).matches();
    }

    public class ThemMonTextWatcher implements TextWatcher{
        View view;

        public ThemMonTextWatcher(View view) {
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
                case R.id.multiAutoTenMon:
                    validateTenMon();
                    break;
                case R.id.edit_GiaMon:
                    validateGia();
                    break;
            }
        }
    }
}
