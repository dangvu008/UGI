package dang.ugi.com.view.NguoiDung;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.ArrayList;

import dang.ugi.com.R;
import dang.ugi.com.customView.customEditTextPassword;
import dang.ugi.com.customView.customEdittextEmail;
import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.Utils.FragmentDialogDateTimePicker;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.model.Utils.PrefNhaHang;
import dang.ugi.com.presenter.CuaHang.ImpPresenterCuaHang;
import dang.ugi.com.presenter.NguoiDung.ImplNguoiDungPresenter;
import dang.ugi.com.view.TrangChu.MainActivity;

/**
 * Created by DANG on 9/5/2016.
 */
public class FragmentDangki extends Fragment implements View.OnClickListener {

    public static final String TAG = FragmentDangki.class.getSimpleName();
    customEdittextEmail editTextTenNguoiDung, editTextSoDienThoai, editTextEmail;
    customEditTextPassword editTextMatKhau, editTextNhapLaiMatKhau;
    TextView textViewNgaySinh;
    TextInputLayout input_layout_TenNguoiDung, input_layout_Email, input_layout_SoDienThoai, input_layout_MatKhau, input_layout_NhapLaiMatKhau;
    RadioButton rbNam, rbNu;
    RadioGroup rbgGioiTinh;
    Button btnDangKi, btnThoatDK,btnNgaySinh;
    Spinner spinnerQuyen;
    CheckBox cbTinhtrang;
    DatePicker datePicker;
    String tenNguoiDung, email, sodienthoai, ngaySinh, matKhau, matKhauNhapLai, cryptionMatKhau, gioitinh;
    String namSinh;
    int phanQuyen, tinhtrang;
    ImplNguoiDungPresenter implPresenterDangNhap;
    ImpPresenterCuaHang impPresenterCuaHang;
    private boolean ketqua = false;
    public FragmentDangki() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangki, container, false);
        addControls(view);
        loadSpinnerQuyen();
        loadFormEdit();
        implPresenterDangNhap = new ImplNguoiDungPresenter(getActivity());
        impPresenterCuaHang = new ImpPresenterCuaHang(getActivity());
        btnDangKi.setOnClickListener(this);
        btnThoatDK.setOnClickListener(this);
        editTextTenNguoiDung.addTextChangedListener(new DangKiTextWatcher(editTextTenNguoiDung));
        editTextSoDienThoai.addTextChangedListener(new DangKiTextWatcher(editTextSoDienThoai));
        editTextEmail.addTextChangedListener(new DangKiTextWatcher(editTextEmail));
        editTextMatKhau.addTextChangedListener(new DangKiTextWatcher(editTextMatKhau));
        editTextNhapLaiMatKhau.addTextChangedListener(new DangKiTextWatcher(editTextNhapLaiMatKhau));
        btnNgaySinh.setOnClickListener(this);
        return view;

    }


    private void loadFormEdit() {
    }

    private void loadValuesFromControls(){
        tenNguoiDung = editTextTenNguoiDung.getText().toString();
        sodienthoai = editTextSoDienThoai.getText().toString();
        email = editTextEmail.getText().toString();
        namSinh = textViewNgaySinh.getText().toString();
        matKhau = editTextMatKhau.getText().toString();
        matKhauNhapLai = editTextNhapLaiMatKhau.getText().toString();
        tinhtrang = (cbTinhtrang.isChecked() ? 1 : 0);
        gioitinh = (rbNam.isChecked() ? "Male" : "Female");
        phanQuyen = (spinnerQuyen.getSelectedItemPosition() + 1);
        try {
            cryptionMatKhau = AESCrypt.encrypt("mat khau", matKhau);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    private void addControls(View view) {
        editTextTenNguoiDung = (customEdittextEmail) view.findViewById(R.id.edit_TenNguoiDung);
        editTextSoDienThoai = (customEdittextEmail) view.findViewById(R.id.edit_SoDienThoai);
        editTextEmail = (customEdittextEmail) view.findViewById(R.id.edit_email);
        textViewNgaySinh = (TextView) view.findViewById(R.id.textview_dangki_ngaysinh);
        editTextMatKhau = (customEditTextPassword) view.findViewById(R.id.editMatKhau);
        editTextNhapLaiMatKhau = (customEditTextPassword) view.findViewById(R.id.editNhapLaiMatKhau);
        input_layout_TenNguoiDung = (TextInputLayout) view.findViewById(R.id.input_layout_TenNguoiDung);
        input_layout_SoDienThoai = (TextInputLayout) view.findViewById(R.id.input_layout_SoDienThoai);
        input_layout_Email = (TextInputLayout) view.findViewById(R.id.input_layout_Email);
        input_layout_MatKhau = (TextInputLayout) view.findViewById(R.id.input_layout_matkhau);
        input_layout_NhapLaiMatKhau = (TextInputLayout) view.findViewById(R.id.input_layout_NhapLaiMatKhau);
        btnDangKi = (Button) view.findViewById(R.id.btnDangKi);
        btnThoatDK = (Button) view.findViewById(R.id.btnThoatDK);
        btnNgaySinh = (Button) view.findViewById(R.id.btn_dangky_chonngaysinh);
        rbgGioiTinh = (RadioGroup) view.findViewById(R.id.grbGioiTinh);
        rbNam = (RadioButton) view.findViewById(R.id.rbNam);
        rbNu = (RadioButton) view.findViewById(R.id.rbNu);
        spinnerQuyen = (Spinner) view.findViewById(R.id.spinnerPhanQuyen);
        cbTinhtrang = (CheckBox) view.findViewById(R.id.checkBoxTinhTrang);
        //datePicker = view.findViewById(R.id.date)

    }

    public void loadSpinnerQuyen() {
        ArrayList<String> listQuyen = new ArrayList<>();
        listQuyen.add("Quản lý");
        listQuyen.add("Nhân viên");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listQuyen);
        spinnerQuyen.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDangKi: {
                dangKi();
            }
            break;
            case R.id.btnThoatDK: {
                getActivity().finish();
            }
            break;
            case R.id.btn_dangky_chonngaysinh: {
                hienthiDialogNgay();
            }
            break;
            default:break;
        }
    }

    private void hienthiDialogNgay() {
        FragmentDialogDateTimePicker dialogFragment = new FragmentDialogDateTimePicker();
        dialogFragment.show(getActivity().getSupportFragmentManager(), "Ngày sinh");
    }

    private void dangKi() {
        if (!validateTenNguoiDung()) {
            return;
        }
        if (!validateEmail()) {
            return;
        }

        if (!validateSoDienThoai()) {
            return;
        }
        if (!validateMatKhau()) {
            return;
        }
        if (!validateNhapLaiMatKhau()) {
            return;
        } else {
            loadValuesFromControls();
            NguoiDung nguoiDungDK = new NguoiDung();
            nguoiDungDK.setTenNguoiDung(tenNguoiDung);
            nguoiDungDK.setSoDienThoai(sodienthoai);
            nguoiDungDK.setEmail(email);
            nguoiDungDK.setGioitinh(gioitinh);
            nguoiDungDK.setMatKhau(cryptionMatKhau);
            nguoiDungDK.setTinhTrang(tinhtrang);
            nguoiDungDK.setNamSinh(namSinh);
            nguoiDungDK.setMaQuyen(phanQuyen);

            int insertedId = (int)implPresenterDangNhap.themDuLieuNguoiDung(nguoiDungDK);
            if (insertedId!=-1){
                nguoiDungDK.setMaNguoiDung(insertedId);
                if (implPresenterDangNhap.themNguoiDungtoServer(nguoiDungDK)){
                    nguoiDungDK.setSync(1);
                }
                if (PrefDangNhap.layNguoiDungHienTai(getActivity())!=null && PrefNhaHang.layCuaHangHienTai(getActivity())!=null){
                    impPresenterCuaHang.themCuaHangNguoiDung(PrefNhaHang.layCuaHangHienTai(getActivity()).getMaCuaHang(),PrefDangNhap.layNguoiDungHienTai(getActivity()).getMaNguoiDung());
                    Intent intent =  new Intent(getActivity(),MainActivity.class);
                    getActivity().startActivity(intent);
                }else{
                    PrefDangNhap.themNguoiDungHienTai(nguoiDungDK, getActivity());
                    implPresenterDangNhap.dangkimoi(nguoiDungDK);
                }
            }else{
                Toast.makeText(getActivity(), "Faild", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }

        }
    }




    private boolean validateTenNguoiDung() {
        tenNguoiDung = editTextTenNguoiDung.getText().toString();
        if (tenNguoiDung.isEmpty()) {
            input_layout_TenNguoiDung.setError("Nhập tên người dùng");
            editTextTenNguoiDung.requestFocus();
            return false;
        }else if (implPresenterDangNhap.kiemTraTenTonTai(tenNguoiDung)){
            input_layout_TenNguoiDung.setError("tên người dùng da ton tai");
        editTextTenNguoiDung.requestFocus();
            return false;
        }

        else {
            input_layout_TenNguoiDung.setErrorEnabled(false);
        }

        return true;
    }



    private boolean validateSoDienThoai() {
        sodienthoai = editTextSoDienThoai.getText().toString();
        if (sodienthoai.isEmpty()) {
            input_layout_SoDienThoai.setError(getString(R.string.nhaplaisodienthoai));
            editTextSoDienThoai.requestFocus();
            return false;
        } if(!PhoneNumberUtils.isGlobalPhoneNumber(sodienthoai)){
            input_layout_SoDienThoai.setError(getString(R.string.nhaplaisodienthoai));
            editTextSoDienThoai.requestFocus();
            return false;
        }
        else if (implPresenterDangNhap.kiemTraSoDienthoaiTonTai(sodienthoai)) {
            input_layout_SoDienThoai.setError(getString(R.string.sodtdatontai));
            editTextSoDienThoai.requestFocus();
            return false;
        }else {
            input_layout_TenNguoiDung.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        email = editTextEmail.getText().toString();
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_layout_Email.setError(getString(R.string.nhaplaiemail));
            editTextEmail.requestFocus();
            return false;
        }
        if (implPresenterDangNhap.kiemtraTaiKhoanTonTai(email)) {
            input_layout_Email.setError(getString(R.string.emailtontai));
           editTextEmail.requestFocus();
            return false;
        } else {
            input_layout_Email.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateMatKhau() {
        matKhau = editTextMatKhau.getText().toString();
        if (matKhau.isEmpty()) {
            input_layout_MatKhau.setError(getString(R.string.nhapmatkhau));
            editTextMatKhau.requestFocus();
            return false;
        } else {
            input_layout_MatKhau.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateNhapLaiMatKhau() {
        matKhauNhapLai = editTextNhapLaiMatKhau.getText().toString();
        if (matKhauNhapLai.isEmpty()) {
            input_layout_NhapLaiMatKhau.setError(getString(R.string.nhaplaimatkhau));
            editTextNhapLaiMatKhau.requestFocus();
            return false;
        }
        if (!matKhau.equals(matKhauNhapLai)) {
            input_layout_NhapLaiMatKhau.setError(getString(R.string.matkhaukhongtrungkhop));
            editTextNhapLaiMatKhau.requestFocus();
            return false;
        } else {
            input_layout_NhapLaiMatKhau.setErrorEnabled(false);
        }
        return true;
    }




    public class DangKiTextWatcher implements TextWatcher {
        View view;

        public DangKiTextWatcher(View view) {
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
                case R.id.edit_TenNguoiDung:
                    validateTenNguoiDung();
                    break;
                case R.id.edit_email:
                    validateEmail();
                    break;
                case R.id.edit_SoDienThoai:
                    validateSoDienThoai();
                    break;
                case R.id.editMatKhau:
                    validateMatKhau();
                    break;
                case R.id.editNhapLaiMatKhau:
                    validateNhapLaiMatKhau();
                    break;
                default:
                    break;

            }
        }
    }


   
}
