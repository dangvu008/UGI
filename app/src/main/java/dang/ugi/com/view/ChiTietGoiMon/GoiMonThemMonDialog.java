package dang.ugi.com.view.ChiTietGoiMon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.regex.Pattern;

import dang.ugi.com.R;
import dang.ugi.com.asyntask.TimKiemTenMonAsynTask;
import dang.ugi.com.model.Entities.ChiTietGoiMon;
import dang.ugi.com.model.Entities.GoiMon;
import dang.ugi.com.model.Entities.MonAn;
import dang.ugi.com.presenter.ChiTietGoiMon.ImplChiTietGoiMonPresenter;
import dang.ugi.com.presenter.MonAn.ImpPresenterMonAn;
import dang.ugi.com.view.TrangChu.IFormActivity;

public class GoiMonThemMonDialog extends Activity implements IFormActivity, View.OnClickListener {

    private AutoCompleteTextView autoCompleteTextViewTenMon;
    private EditText editTextSoLuongMon;
    private Button btn_danggoimon_themmon, btn_danggoimon_thoatthemmon;
    private ImageButton imageButtonTruSoLuong,imageButtonCongSoLuong;
    private TextInputLayout validate_TenMon, validate_SoLuong;
    private Context activity;
    private int maMonSelected;
    private List<MonAn> listMonAn;
    private ImpPresenterMonAn impPresenterMonAn;
    private ImplChiTietGoiMonPresenter implChiTietGoiMonPresenter;
    private Intent intent;
    private Bundle bundle;
    private GoiMon goiMon;
    private ChiTietGoiMon chiTietGoiMon;
    private CoordinatorLayout activity_goi_mon_them_mon_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goi_mon_them_mon_dialog);
        loadControls();
        activity = this;
        impPresenterMonAn = new ImpPresenterMonAn(this);
        implChiTietGoiMonPresenter = new ImplChiTietGoiMonPresenter(this);
        activity_goi_mon_them_mon_dialog = (CoordinatorLayout) findViewById(R.id.activity_goi_mon_them_mon_dialog);
         intent = getIntent();
         bundle = intent.getBundleExtra("GOIMON");
        if (bundle!=null){
            goiMon = (GoiMon) bundle.getSerializable("bGoiMon");
        }
        addEvents();
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog()
                .penaltyDeath().build());
    }

    @Override
    public void loadControls() {
        autoCompleteTextViewTenMon = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_dialog_goimon_tenmon);
        editTextSoLuongMon = (EditText) findViewById(R.id.editText_danggoimon_soluong);
        btn_danggoimon_themmon = (Button) findViewById(R.id.btn_themMonVaoGoiMon_them);
        btn_danggoimon_thoatthemmon = (Button) findViewById(R.id.btn_themMonVaoGoiMon_thoat);
        validate_TenMon = (TextInputLayout) findViewById(R.id.layout_validate_dialog_goimon_autocompleteTenMon);
        validate_SoLuong = (TextInputLayout) findViewById(R.id.layout_validate_dialog_goimon_soluong);
        imageButtonCongSoLuong = (ImageButton) findViewById(R.id.img_btn_themMonVaoGoiMoncongsoluong);
        imageButtonTruSoLuong = (ImageButton) findViewById(R.id.img_btn_themMonVaoGoiMon_trusoluong);

    }

    @Override
    public void loadDataEdit() {

    }

    @Override
    public void luuDuLieu() {
        boolean ketqua = false;
        if (!validateForm()) {
            return;
        } else {
            if (goiMon!=null){
                String tenMon = autoCompleteTextViewTenMon.getText().toString();
                int maCuaHang= goiMon.getMaCuaHang();
                if (!impPresenterMonAn.kiemTraTenMonTonTaiTrongThucDon(tenMon,maCuaHang)){
                    validate_TenMon.setError(getString(R.string.monkhongtontaitrongthucdon));
                }else{
                    int soluong = Integer.parseInt(editTextSoLuongMon.getText().toString());
                    int maGoiMon = goiMon.getMaGoiMon();
                    float giaMon = impPresenterMonAn.layGiaMonTheoMaMon(maMonSelected);
                    float thanhTien = soluong * giaMon;
                    chiTietGoiMon = implChiTietGoiMonPresenter.kiemTraMonDaTonTai(maMonSelected,maGoiMon);
                    if (chiTietGoiMon==null){
                        chiTietGoiMon = new ChiTietGoiMon();
                        chiTietGoiMon.setMaGoiMon(maGoiMon);
                        chiTietGoiMon.setThanhTien(thanhTien);
                        chiTietGoiMon.setSoLuong(soluong);
                        chiTietGoiMon.setMaMon(maMonSelected);
                        if (implChiTietGoiMonPresenter.themChiTietGoiMon(chiTietGoiMon)!=-1){
                            ketqua = true;
                        }
                    }else{
                        thanhTien = thanhTien + chiTietGoiMon.getThanhTien();
                        chiTietGoiMon.setThanhTien(thanhTien);
                        if (implChiTietGoiMonPresenter.capnhatChiTietGoiMon(chiTietGoiMon)!=-1){
                            ketqua = true;
                        }
                    }
                    if (ketqua){
                        intent.putExtra("MAGOIMON",goiMon.getMaGoiMon());
                        setResult(Activity.RESULT_OK,intent);
                       Snackbar.make(activity_goi_mon_them_mon_dialog,"Thêm thành công ", Snackbar.LENGTH_LONG).show();
                        finish();
                    }else{
                        implChiTietGoiMonPresenter.xoaChiTietGoiMon(chiTietGoiMon.getMaChiTietGoiMon());
                    }
                }
            }

        }
    }

    public boolean validateForm() {
        if (validateAutocompleteTenMon()) {
            return true;
        } else if (validateSoLuongMon()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addEvents() {
        autoCompleteTextViewTenMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autoCompleteTextViewTenMon.getText().length() == 0) {
                    new TimKiemTenMonAsynTask((Activity) view.getContext()).execute("");
                }
            }
        });
        autoCompleteTextViewTenMon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout layout = (LinearLayout) view;
                TextView textViewTen = (TextView) layout.getChildAt(0);
                TextView textViewMa = (TextView) layout.getChildAt(1);
                autoCompleteTextViewTenMon.setText(textViewTen.getText().toString());
                maMonSelected = Integer.parseInt(textViewMa.getText().toString());
            }
        });
        autoCompleteTextViewTenMon.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                if (s.length() != 0) {
                    new TimKiemTenMonAsynTask(activity).execute(s);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        btn_danggoimon_thoatthemmon.setOnClickListener(this);
        btn_danggoimon_themmon.setOnClickListener(this);
        imageButtonTruSoLuong.setOnClickListener(this);
        imageButtonCongSoLuong.setOnClickListener(this);
    }

    public boolean validateAutocompleteTenMon() {
        String strAutocomplete = autoCompleteTextViewTenMon.getText().toString();
        if (strAutocomplete.length() == 0) {
            validate_TenMon.setErrorEnabled(true);
            validate_TenMon.setError("Chọn món !");
            autoCompleteTextViewTenMon.requestFocus();
            return false;
        }else{
            validate_TenMon.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validateSoLuongMon() {
        String strSoLuong = editTextSoLuongMon.getText().toString();
        Pattern pattern = Pattern.compile("\\d{1,5}");
        if (strSoLuong.length() == 0) {
            validate_SoLuong.setErrorEnabled(true);
            validate_SoLuong.setError("Nhập số lượng !");
            editTextSoLuongMon.requestFocus();
            return false;
        } else if (!pattern.matcher(strSoLuong).matches()) {
            validate_SoLuong.setErrorEnabled(true);
            validate_SoLuong.setError("Nhập  lại số lượng !");
            editTextSoLuongMon.requestFocus();
            return false;
        }else if(Integer.parseInt(strSoLuong)==0){
            validate_SoLuong.setErrorEnabled(true);
            validate_SoLuong.setError("Số Lượng phải lớn hơn 0 !");
            editTextSoLuongMon.requestFocus();
            return false;
        }else{
            validate_SoLuong.setErrorEnabled(false);
            return true;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_themMonVaoGoiMon_them: {
                luuDuLieu();
            }
            break;
            case R.id.btn_themMonVaoGoiMon_thoat:
                finish();
                break;
            case R.id.img_btn_themMonVaoGoiMoncongsoluong:{
              int soLuong = Integer.parseInt(editTextSoLuongMon.getText().toString());
                soLuong = soLuong+1;
                editTextSoLuongMon.setText(String.valueOf(soLuong));
            }break;
            case R.id.img_btn_themMonVaoGoiMon_trusoluong:{
                int soLuong = Integer.parseInt(editTextSoLuongMon.getText().toString());
                soLuong = soLuong - 1 ;
                if (soLuong>=1){
                    editTextSoLuongMon.setText(String.valueOf(soLuong));
                }
            }break;


        }
    }


    public class SoLuongTextWatcher implements TextWatcher {
        View view;

        public SoLuongTextWatcher(View view) {
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
            switch (view.getId()) {
                case R.id.autocomplete_danggoimon_tenban:
                    validateAutocompleteTenMon();
                    break;
                case R.id.editText_danggoimon_soluong:
                    validateSoLuongMon();
                    break;
            }
        }
    }
}
