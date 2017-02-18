package dang.ugi.com.view.BanAn;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dang.ugi.com.view.TrangChu.IFormActivity;
import dang.ugi.com.R;
import dang.ugi.com.adapter.BanAnAdapter;
import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;

public class ThemBanNhanh extends AppCompatActivity implements View.OnClickListener,IFormActivity{

    EditText editTextSoBan;
    TextInputLayout validate_SoBan;
    Button btn_themBanNhanh_them,btn_themBanNhanh_thoat;
    CoordinatorLayout layout_themBanNhanh;
    ImplPresenterBanAn implPresenterBanAn;
    List<BanAn> list;
    int macuaHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ban_nhanh);
        implPresenterBanAn = new ImplPresenterBanAn(this);
         macuaHang= PrefNhaHang.layCuaHangHienTai(this).getMaCuaHang();
        loadControls();
        addEvents();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_themBanNhanh_them:
                luuDuLieu();
                break;
            case R.id.btn_themBanNhanh_thoat:
                finish();
                break;
        }
    }

    @Override
    public void loadControls() {
        layout_themBanNhanh = (CoordinatorLayout) findViewById(R.id.activity_them_ban_nhanh);
        editTextSoBan = (EditText) findViewById(R.id.editTextSoLuongBanMuonThem);
        validate_SoBan= (TextInputLayout) findViewById(R.id.input_layout_soBanThem);
        btn_themBanNhanh_them = (Button) findViewById(R.id.btn_themBanNhanh_them);
        btn_themBanNhanh_thoat = (Button) findViewById(R.id.btn_themBanNhanh_thoat);
    }

    @Override
    public void loadDataEdit() {

    }
    private List<Integer> soBanLonNhat(){
        List<String> listTenBan = implPresenterBanAn.layToanBoTenBanAn(macuaHang);
        List<Integer> listsoBan = new ArrayList<>();

        Pattern pattern= Pattern.compile("[0-9]+$");
        if (listTenBan!=null){
            for (String tenBan :listTenBan) {
                Matcher matcher = pattern.matcher(tenBan);
                while (matcher.find()){
                    int soBan = Integer.parseInt(matcher.group());
                    listsoBan.add(soBan);
                }
            }
        }else{
            listsoBan.add(0);
        }

        return listsoBan;

    }
    @Override
    public void luuDuLieu() {
        if (validateSoBan()){
            int soBanduocThem = 0;
            int soluongBanThem = Integer.parseInt(editTextSoBan.getText().toString());
            String tenBan = "";
            int soBanLonNhat = 0;
            if (soBanLonNhat().size()!=0){
                soBanLonNhat = Collections.max(soBanLonNhat());
            }
            for (int i = 1; i <= soluongBanThem; i++) {
                 int soBan = soBanLonNhat + i ;
                tenBan = "Bàn số " + soBan;
                if (implPresenterBanAn.kiemTraTonTaiBanAn(tenBan,macuaHang)){
                    soBanduocThem = soluongBanThem +1;
                }
                BanAn banAn = new BanAn(tenBan,macuaHang,1);
                implPresenterBanAn.themBanAn(banAn);
                soBanduocThem ++;
            }
            if (soBanduocThem>0){
                list = implPresenterBanAn.layToanBoBanAn(macuaHang);
                BanAnAdapter banAnAdapter = new BanAnAdapter(this,list);
                banAnAdapter.notifyDataSetChanged();
                Snackbar.make(layout_themBanNhanh,soBanduocThem+" bàn được thêm !",Snackbar.LENGTH_LONG).show();
                setResult(Activity.RESULT_OK);
                finish();
            }
        }
    }

    @Override
    public void addEvents() {
        editTextSoBan.addTextChangedListener(new ThemBanNhanhTextWatcher(editTextSoBan));
        btn_themBanNhanh_thoat.setOnClickListener(this);
        btn_themBanNhanh_them.setOnClickListener(this);
    }
    public class ThemBanNhanhTextWatcher implements TextWatcher {
        View view;

        public ThemBanNhanhTextWatcher(View view) {
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
                case R.id.editTextSoLuongBanMuonThem:
                    validateSoBan();
                    break;
            }
        }
    }

    private boolean validateSoBan() {
        String strSoBan = editTextSoBan.getText().toString();
        if (strSoBan.isEmpty()){
            validate_SoBan.setError("Nhập số bàn muốn thêm");
            editTextSoBan.requestFocus();
            return false;
        }else{
            validate_SoBan.setErrorEnabled(false);
        }
        return true;
    }
}
