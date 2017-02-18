package dang.ugi.com.view.GoiMon;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import dang.ugi.com.asyntask.TimkiemTenBanAsynTask;
import dang.ugi.com.R;
import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.model.Entities.GoiMon;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;
import dang.ugi.com.presenter.GoiMon.ImplGoiMonPresenter;

public class ChuyenBanActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextViewTenBan;
    Button btnChuyen,btnThoat;
    TextInputLayout validateTenBan;
    Intent intent;
    Bundle bundle;
    ImplGoiMonPresenter implGoiMonPresenter;
    ImplPresenterBanAn implPresenterBanAn;
    BanAn banan;
    AppCompatActivity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_ban);
        addControls();
        addEvents();
        context = this;
        implPresenterBanAn = new ImplPresenterBanAn(this);
        intent = getIntent();
        bundle = intent.getBundleExtra("bsGoiMon");
        if (bundle!=null){
            GoiMon  goimon = (GoiMon) bundle.getSerializable("GOIMON");
            banan= implPresenterBanAn.layBanAnbyMaBanAn(goimon.getMaBan());
            autoCompleteTextViewTenBan.setText(banan.getTenBanAn());
        }
    }

    private void addEvents() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private boolean validateTenBan() {
        boolean kiemtra = false;
        if (autoCompleteTextViewTenBan.getText().length()==0) {
            validateTenBan.setError(getString(R.string.bancangoimon));
            validateTenBan.setErrorEnabled(true);
            autoCompleteTextViewTenBan.requestFocus();
            kiemtra = false;
        }else{
            validateTenBan.setErrorEnabled(false);
            kiemtra = true;
        }
        return  kiemtra;
    }
    private void addControls() {
        autoCompleteTextViewTenBan = (AutoCompleteTextView) findViewById(R.id.autocomplete_chuyenban_tenban);
        btnChuyen = (Button) findViewById(R.id.btn_chuyenban_chuyenban);
        btnThoat = (Button) findViewById(R.id.btn_chuyenban_thoat);
        validateTenBan= (TextInputLayout) findViewById(R.id.layout_validate_chuyenban_autocomplete_TenBan);
        autoCompleteTextViewTenBan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                new TimkiemTenBanAsynTask(context).execute("", String.valueOf(banan.getMaBanAn()));
                return false;
            }
        });
        autoCompleteTextViewTenBan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String s = charSequence.toString();
                new TimkiemTenBanAsynTask(context).execute(s, String.valueOf(banan.getMaBanAn()));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                validateTenBan();
            }
        });
    }
}
