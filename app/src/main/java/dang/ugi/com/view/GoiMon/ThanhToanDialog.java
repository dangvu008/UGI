package dang.ugi.com.view.GoiMon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dang.ugi.com.asyntask.LoadTienThuaAsynTask;
import dang.ugi.com.asyntask.LoadTinhTienAsynTask;
import dang.ugi.com.R;
import dang.ugi.com.model.Entities.BanAn;
import dang.ugi.com.model.Entities.ChiTietGoiMon;
import dang.ugi.com.model.Entities.GoiMon;
import dang.ugi.com.model.Entities.HoaDon;
import dang.ugi.com.model.Utils.FormatData;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.presenter.BanAn.ImplPresenterBanAn;
import dang.ugi.com.presenter.ChiTietGoiMon.ImplChiTietGoiMonPresenter;
import dang.ugi.com.presenter.GoiMon.ImplGoiMonPresenter;
import dang.ugi.com.presenter.HoaDon.ImplPresenterHoaDon;

public class ThanhToanDialog extends AppCompatActivity {

    Button btnXacNhan,btnThoat;
    EditText editTextTongTien,editTextTienPhatSinh,editTextTienMat,editTextTienThua,editTextGhiChu;
    TextView textViewTenBan,textViewMaGoiMon;
    GoiMon goimon;
    Context context;
    float tienPhatSinh,tongtien,tienmat,tienthua;
    List<ChiTietGoiMon> listChiTietGoiMon;
    ImplChiTietGoiMonPresenter implChiTietGoiMonPresenter;
    ImplGoiMonPresenter implGoiMonPresenter;
    ImplPresenterBanAn implPresenterBanAn;
    ImplPresenterHoaDon implPresenterHoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan_dialog);
        addControls();
        context = this;
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bsGoiMon");
        goimon = (GoiMon) bundle.getSerializable("GOIMON");
        implChiTietGoiMonPresenter = new ImplChiTietGoiMonPresenter(this);
        implPresenterBanAn = new ImplPresenterBanAn(this);
        implPresenterHoaDon = new ImplPresenterHoaDon(this);
        implGoiMonPresenter = new ImplGoiMonPresenter(this);
        textViewMaGoiMon.setText(String.valueOf(goimon.getMaGoiMon()));
        loaddata();
        addEvents();

    }

    private void addEvents() {
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               themHoaDon();
            }
        });
        editTextTienPhatSinh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTextTienPhatSinh.getText().toString().length()!=0){
                    tienPhatSinh = Float.parseFloat(editTextTienPhatSinh.getText().toString());
                    new LoadTinhTienAsynTask(context,1).execute(tienPhatSinh);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
               /* if (editTextTienPhatSinh.getText().toString().length()==0){
                    tienPhatSinh = double.parsedouble(editTextTienPhatSinh.getText().toString());
                    new LoadTinhTienAsynTask(context,1).execute(tienPhatSinh);
                }*/
                if (editTextTienPhatSinh.getText().toString().length()!=0){
                    tienPhatSinh = Float.parseFloat(editTextTienPhatSinh.getText().toString());
                    new LoadTinhTienAsynTask(context,1).execute(tienPhatSinh);
                }
            }
        });
        editTextTienMat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (editTextTienMat.getText().length()!=0){
                    tongtien = Float.parseFloat(editTextTongTien.getText().toString());
                    tienmat = Float.parseFloat(editTextTienMat.getText().toString());
                    new  LoadTienThuaAsynTask(context).execute(tienmat);
                }/*else{
                    tienPhatSinh = Double.parseDouble(editTextTienPhatSinh.getText().toString());
                    new LoadTinhTienAsynTask(context,1).execute(tienPhatSinh);
                }*/
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editTextTienMat.getText().length()!=0){

                }/*else{
                    tienPhatSinh = Double.parseDouble(editTextTienPhatSinh.getText().toString());
                    new LoadTinhTienAsynTask(context,1).execute(tienPhatSinh);
                }*/
            }
        });
    }

    private void themHoaDon() {
        int maNguoiDung = PrefDangNhap.layNguoiDungHienTai(context).getMaNguoiDung();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MINUTE,0);
        HoaDon hoadon = new HoaDon();
        hoadon.setMaGoiMon(Integer.parseInt(textViewMaGoiMon.getText().toString()));
        hoadon.setMaNguoiDungThanhToan(maNguoiDung);
        hoadon.setTienthem(Float.parseFloat(editTextTienPhatSinh.getText().toString().replace(".","").trim()));
        hoadon.setGhiChu(editTextGhiChu.getText().toString());
        hoadon.setSotien(Float.parseFloat(editTextTongTien.getText().toString().replace(".","").trim()));
        hoadon.setThoiGianThanhToan(new Timestamp(date.getTime()));
        if (implPresenterHoaDon.themHoaDon(hoadon)!=-1){
            implPresenterBanAn.capNhatTrangThaiBan(goimon.getMaBan(),0);
            goimon.setTinhTrang(1);
            implGoiMonPresenter.capNhatGoiMon(goimon);
            Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void loaddata() {
      listChiTietGoiMon = implChiTietGoiMonPresenter.listChiTietGoiMon(goimon.getMaGoiMon());
        BanAn banAn = implPresenterBanAn.layBanAnbyMaBanAn(goimon.getMaBan());
        float tongtien = 0;
        for (int i = 0; i < listChiTietGoiMon.size(); i++) {
            tongtien += listChiTietGoiMon.get(i).getThanhTien();
        }
        float vat = (float) (0.1 * tongtien);
        tongtien = tongtien + vat;
        String strTongTien = FormatData.formatMoneyVietNam(tongtien);
        editTextTienPhatSinh.setText("0");
        editTextTongTien.setText(strTongTien);
        editTextTienMat.setText(strTongTien);
        textViewTenBan.setText(banAn.getTenBanAn());
        editTextTienThua.setText("0");

    }

    private void addControls() {
        editTextTienMat = (EditText) findViewById(R.id.editText_thanhtoan_tienmat);
        editTextTienPhatSinh = (EditText) findViewById(R.id.editText_thanhtoan_tienphatsinh);
        editTextTongTien = (EditText) findViewById(R.id.editText_thanhtoan_tongtien);
        editTextTienThua = (EditText) findViewById(R.id.editText_thanhtoan_tienthua);
        editTextGhiChu = (EditText) findViewById(R.id.editText_thanhtoan_ghichu);
        btnThoat = (Button) findViewById(R.id.btn_dialog_thanhtoan_thoat);
        btnXacNhan = (Button) findViewById(R.id.btn_dialog_thanhtoan_xacnhan);
        textViewTenBan = (TextView) findViewById(R.id.textView_dialog_thanhtoan_tenBan);
        textViewMaGoiMon = (TextView) findViewById(R.id.textView_dialog_thanhtoan_magoimon);
    }
}
