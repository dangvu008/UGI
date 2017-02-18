package dang.ugi.com.view.BanAn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import dang.ugi.com.view.TrangChu.IFormActivity;
import dang.ugi.com.R;
import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;

public class ThemBanActivity extends AppCompatActivity implements IFormActivity,View.OnClickListener {

    TextInputLayout validate_tenBan;
    EditText editTextTenBan;
    Button btnThemBan,btnThoatThemBan;
    CoordinatorLayout coordinatorLayout;
    ImplPresenterBanAn implPresenterBanAn;
    String tenBan;
    BanAn banAn;
    Intent intent ;
    int maCuaHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ban);
        implPresenterBanAn = new ImplPresenterBanAn(this);
        loadControls();
        intent = getIntent();
        if (intent.getExtras()!=null){
            loadDataEdit();
        }
        maCuaHang= PrefNhaHang.layCuaHangHienTai(this).getMaCuaHang();
        loadDataForm();
        addEvents();
    }


    @Override
    public void loadControls() {
        validate_tenBan = (TextInputLayout) findViewById(R.id.validate_layout_TenBan);
        editTextTenBan = (EditText) findViewById(R.id.editTextTenBan);
        btnThemBan = (Button) findViewById(R.id.btnThemBan);
        btnThoatThemBan = (Button) findViewById(R.id.btn_themBan_thoat);
         coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_them_ban);
    }
    public void loadDataForm(){
        tenBan = editTextTenBan.getText().toString().trim();
    }
    @Override
    public void loadDataEdit() {
      Bundle bundle = intent.getBundleExtra("bBanAn");
        banAn = (BanAn) bundle.getSerializable("banAn");
        editTextTenBan.setText(banAn.getTenBanAn());
    }



    @Override
    public void luuDuLieu() {

        if (!validateTenBan()){
           return;
        }else{

            if (intent.getExtras()!=null){
                banAn.setTenBanAn(tenBan);
                banAn.setMaCuaHang(maCuaHang);
                if (implPresenterBanAn.suaBanAn(banAn)!=0){
                    setResult(Activity.RESULT_OK);
                    Snackbar.make(coordinatorLayout,getString(R.string.SuaBanThanhCong),Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            }else{
                BanAn ban= new BanAn();
                ban.setTenBanAn(tenBan);
                banAn.setMaCuaHang(maCuaHang);
                if (implPresenterBanAn.themBanAn(ban)!=-1){
                    setResult(Activity.RESULT_OK);
                    Snackbar.make(coordinatorLayout, R.string.themBanThanhCong,Snackbar.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }
    private void requestFocus(View view){
        if (view.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
    @Override
    public void addEvents() {
        btnThemBan.setOnClickListener(this);
        btnThoatThemBan.setOnClickListener(this);
        editTextTenBan.addTextChangedListener(new ThemBanTextWatcher(editTextTenBan));
    }


    public boolean validateTenBan(){
       tenBan = editTextTenBan.getText().toString().trim();
        if (tenBan.isEmpty()){
            validate_tenBan.setError("Nhập tên bàn");
            requestFocus(editTextTenBan);
            return false;
        }
        if (implPresenterBanAn.kiemTraTonTaiBanAn(tenBan,maCuaHang)){
            validate_tenBan.setError("Tên bàn đã tồn tại");
            editTextTenBan.requestFocus();
            return false;
        }else{
            validate_tenBan.setErrorEnabled(false);
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_themBan_thoat:{
                finish();
            }break;
            case R.id.btnThemBan:{
                luuDuLieu();
            }break;
        }
    }

    public class ThemBanTextWatcher implements TextWatcher {
        View view;

        public ThemBanTextWatcher(View view) {
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
                case R.id.editTextTenBan:{
                    validateTenBan();
                }
            }
        }
    }
}
