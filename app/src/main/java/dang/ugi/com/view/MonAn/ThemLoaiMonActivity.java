package dang.ugi.com.view.MonAn;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.LoaiMon;
import dang.ugi.com.presenter.LoaiMonAn.ImplLoaiMonAnPresenter;

public class ThemLoaiMonActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextTenloaiMon;
    Button btnThem,btnthoat;
    TextInputLayout textInputLayout;
    ImplLoaiMonAnPresenter implLoaiMonAnPresenter;
    CoordinatorLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_loai_mon);
        editTextTenloaiMon = (EditText) findViewById(R.id.editTextTenLoaiMon);
        btnThem = (Button) findViewById(R.id.btnThemLoaiMon);
        btnthoat = (Button) findViewById(R.id.btn_ThemLoaiMon_thoat);
        layout = (CoordinatorLayout) findViewById(R.id.layout_themLoaiMon);
        textInputLayout = (TextInputLayout) findViewById(R.id.validate_layout_TenLoaiMon);
        implLoaiMonAnPresenter =  new ImplLoaiMonAnPresenter(this);
        addEvents();
    }

    private void addEvents() {
        editTextTenloaiMon.addTextChangedListener(new LoaiMonAnTextWatcher(editTextTenloaiMon));
        btnThem.setOnClickListener(this);
        btnthoat.setOnClickListener(this);
    }

    public boolean validateTenLoai(){
        String tenLoaiMon = editTextTenloaiMon.getText().toString().trim();
        if (tenLoaiMon.isEmpty()){
            textInputLayout.setError("Điền tên loại món !");
            editTextTenloaiMon.requestFocus();
            return false;
        }else if (implLoaiMonAnPresenter.kiemtraLoaiMonTonTai(tenLoaiMon)){
            textInputLayout.setError("Tên loại món đã tồn tại ");
            editTextTenloaiMon.requestFocus();
            return false;
        }else{
            textInputLayout.setErrorEnabled(false);
            return true;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnThemLoaiMon:{
                themLoaiMon();
            }break;
            case R.id.btn_ThemLoaiMon_thoat:{
                finish();
            }break;
        }
    }

    private void themLoaiMon() {
        if (validateTenLoai()){
            LoaiMon loaiMon = new LoaiMon();
            loaiMon.setTenLoaiMon(editTextTenloaiMon.getText().toString());
            if (implLoaiMonAnPresenter.themLoaiMon(loaiMon)!= 0){
                setResult(RESULT_OK);
                Snackbar.make(layout,"Thêm loại món thành công !",Snackbar.LENGTH_SHORT).show();
                finish();
            }else{
                Snackbar.make(layout,"Thêm loại món không thành công !",Snackbar.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public class LoaiMonAnTextWatcher implements TextWatcher {
        View view ;

        public LoaiMonAnTextWatcher(View view) {
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
                case R.id.editTextTenLoaiMon:
                    validateTenLoai();
                    break;
            }
        }
    }
}
