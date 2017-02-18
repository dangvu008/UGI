package dang.ugi.com.view.NguoiDung;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import dang.ugi.com.R;
import dang.ugi.com.model.Entities.CuaHang;
import dang.ugi.com.model.Entities.NguoiDung;
import dang.ugi.com.model.Utils.PrefDangNhap;
import dang.ugi.com.network.NguoiDung.ConstantsNguoiDung;
import dang.ugi.com.network.NguoiDung.ServerRequestNguoiDung;
import dang.ugi.com.network.NguoiDung.ServerResponseNguoiDung;
import dang.ugi.com.network.NguoiDung.RequestInterfaceNguoiDung;
import dang.ugi.com.network.RetrofitHandler;
import dang.ugi.com.presenter.NguoiDung.ImplNguoiDungPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by DANG on 9/5/2016.
 */
public class FragmentDangNhap extends Fragment implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    Button btnLogin;
    ImageButton ibtnLogin_facebook,ibtnLogin_google;
    EditText editTextEmailSdt,editTextMatKhau;
    TextView tvQuenMatKhau;
    TextInputLayout textInputLayout_Email,textInputLayoutMatKhau;
    ProgressDialog progressDialog;
    CallbackManager callbackManager;
    ImplNguoiDungPresenter implPresenterDangNhap;
    NguoiDung nguoiDung;
    CuaHang cuaHang;
    public static final int RESULT_CODE_DANGNHAP_FB_THANHCONG = 2 ;
    public static final int REQUEST_CODE_DANGNHAP_GG = 3 ;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInOptions gso;
    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    private int allowlogin = -1;
    public FragmentDangNhap() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangnhap,container,false);
        ibtnLogin_facebook = (ImageButton) view.findViewById(R.id.ibtnLogin_facebook);
        ibtnLogin_google = (ImageButton) view.findViewById(R.id.ibtnLogin_google);
        addControls(view);
        editTextEmailSdt.addTextChangedListener(new DangNhapTextWatcher(editTextEmailSdt));
        editTextMatKhau.addTextChangedListener(new DangNhapTextWatcher(editTextMatKhau));
        implPresenterDangNhap = new ImplNguoiDungPresenter(getActivity());
        ibtnLogin_facebook.setOnClickListener(this);
        ibtnLogin_google.setOnClickListener(this);
        tvQuenMatKhau.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        dangNhapFacebook();
        mGoogleApiClient = layGoogleApiClient();
        return view;
    }



    private void addControls(View view) {
        editTextEmailSdt = (EditText) view.findViewById(R.id.edit_dangNhap_email);
        editTextMatKhau = (EditText) view.findViewById(R.id.edit_DangNhap_MatKhau);
        textInputLayout_Email = (TextInputLayout) view.findViewById(R.id.input_layout_DangNhap_Email);
        textInputLayoutMatKhau = (TextInputLayout) view.findViewById(R.id.input_layout_dangNhap_matkhau);
        btnLogin = (Button) view.findViewById(R.id.btnDangNhap);
        tvQuenMatKhau = (TextView) view.findViewById(R.id.tvQuenMatKhau);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
         if (requestCode ==REQUEST_CODE_DANGNHAP_GG && resultCode== Activity.RESULT_OK){
             GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
             layNguoiDungHientai(result);
             NguoiDung nguoiDungDK = checkAllowLoginSoccial(nguoiDung);
             CuaHang cuahang = checkNguoiDungCuaHang(nguoiDung);
             if (nguoiDungDK==null && cuahang==null){
                 implPresenterDangNhap.dangkimoi(nguoiDung);
             }else {
                if(PrefDangNhap.layNguoiDungHienTai(getActivity())==null) {
                    PrefDangNhap.themNguoiDungHienTai(nguoiDung,getActivity());
                }
                 implPresenterDangNhap.chuyenManHinhChinh();
             }
         }


    }
    public GoogleApiClient layGoogleApiClient(){

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        return mGoogleApiClient;
    }
    private void dangNhapGoogle(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent,REQUEST_CODE_DANGNHAP_GG);
    }
    public  void dangXuatGoogle(){
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
    }

    private void layNguoiDungHientai(GoogleSignInResult result) {
        if (result.isSuccess()){
            nguoiDung = new NguoiDung();
            GoogleSignInAccount account = result.getSignInAccount();
            nguoiDung.setTenNguoiDung(account.getDisplayName());
            nguoiDung.setEmail(account.getEmail());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()){
            GoogleSignInResult result = opr.get();
            layNguoiDungHientai(result);
        }else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                   layNguoiDungHientai(result);
                }
            });
        }
    }
    NguoiDung nguoidungSocial;
    public NguoiDung checkAllowLoginSoccial(NguoiDung nguoiDung){
        Retrofit retrofit = RetrofitHandler.retrofit(ConstantsNguoiDung.BASE_URL);
         RequestInterfaceNguoiDung requestInterfaceNguoiDung =retrofit.create(RequestInterfaceNguoiDung.class);
        ServerRequestNguoiDung request = new ServerRequestNguoiDung();
        request.setNguoiDung(nguoiDung);
        request.setOperation(ConstantsNguoiDung.LOGIN_PERMISSION);
        Call<ServerResponseNguoiDung> responseCall = requestInterfaceNguoiDung.operation(request);
        responseCall.enqueue(new Callback<ServerResponseNguoiDung>() {
            @Override
            public void onResponse(Call<ServerResponseNguoiDung> call, Response<ServerResponseNguoiDung> response) {
                if (response.isSuccessful()) {
                     nguoidungSocial = response.body().getNguoiDung();
                }
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<ServerResponseNguoiDung> call, Throwable t) {

            }
        });
        return nguoidungSocial;
    }
    public CuaHang checkNguoiDungCuaHang(NguoiDung nguoiDung){
        Retrofit retrofit = RetrofitHandler.retrofit(ConstantsNguoiDung.BASE_URL);
        RequestInterfaceNguoiDung requestInterfaceNguoiDung =retrofit.create(RequestInterfaceNguoiDung.class);
        ServerRequestNguoiDung request = new ServerRequestNguoiDung();
        request.setNguoiDung(nguoiDung);
        request.setOperation(ConstantsNguoiDung.LOGIN_CHECKCUAHANG_NGUOIDUNG);
        Call<ServerResponseNguoiDung> responseCall = requestInterfaceNguoiDung.operation(request);
        responseCall.enqueue(new Callback<ServerResponseNguoiDung>() {
            @Override
            public void onResponse(Call<ServerResponseNguoiDung> call, Response<ServerResponseNguoiDung> response) {
                if (response.isSuccessful()) {
                     cuaHang = response.body().getCuaHang();
                }
                getActivity().finish();
            }

            @Override
            public void onFailure(Call<ServerResponseNguoiDung> call, Throwable t) {
            }
        });
        return cuaHang;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDangNhap :{
                dangNhap();
            }break;
            case R.id.ibtnLogin_facebook :{
               dangnhapFacebook();
            }break;
            case R.id.ibtnLogin_google :{
                dangNhapGoogle();
            }break;
            case R.id.tvQuenMatKhau:{
                quenMatKhau();
            }break;

        }
    }

    private void dangnhapFacebook() {
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","name,email,gender"));
    }
    public void dangNhapFacebook() {
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }

        };
        accessToken = AccessToken.getCurrentAccessToken();
       LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               layThongTinNguoiDungHienTai(accessToken);
                getActivity().finish();
            }
            @Override
            public void onCancel () {
                getActivity().finish();
            }

            @Override
            public void onError (FacebookException error){
                getActivity().finish();
            }

        });
      // return nguoiDung;
    }

    private void layThongTinNguoiDungHienTai(AccessToken accessToken) {
        if (accessToken != null) {
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    FacebookRequestError error = response.getError();
                    if (error != null) {
                        return;
                    } else {
                        try {
                            nguoiDung = new NguoiDung();
                            String gender = object.getString("gender");
                            String tenNguoiDung = object.getString("name");
                            String email = object.getString("email");
                            String ngaysinh = object.getString("user_birthday");
                            nguoiDung.setTenNguoiDung(tenNguoiDung);
                            nguoiDung.setEmail(email);
                            nguoiDung.setGioitinh(gender);
                            nguoiDung.setNamSinh(ngaysinh);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    getActivity().finish();

                }

            });
            Bundle parameter = new Bundle();
            parameter.putString("fields", "name,user_birthday,email,gender");
            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();
        }

    }

    private void quenMatKhau() {
    }

    private void dangNhap() {
        if (!validateEmailDangNhap()){
            return;
        }if (!validateMatKhauDangNhap()){
            return;
        }else{
            String emailSdt= editTextEmailSdt.getText().toString();
            String matKhau = editTextMatKhau.getText().toString();
            NguoiDung nguoiDung = implPresenterDangNhap.dangNhap(emailSdt,matKhau);
            dangnhapOnline(nguoiDung);
                if (nguoiDung!=null){
                    PrefDangNhap.themNguoiDungHienTai(nguoiDung,getActivity());
                    implPresenterDangNhap.chuyenManHinhChinh();
                }

        }
    }
    boolean ketqua = false;
    private boolean dangnhapOnline(NguoiDung nguoiDung) {
        Retrofit retrofit = RetrofitHandler.retrofit(ConstantsNguoiDung.BASE_URL);
        RequestInterfaceNguoiDung requestInterfaceNguoiDung = retrofit.create(RequestInterfaceNguoiDung.class);
        ServerRequestNguoiDung serverRequestNguoiDung = new ServerRequestNguoiDung();
        serverRequestNguoiDung.setNguoiDung(nguoiDung);
        serverRequestNguoiDung.setOperation(ConstantsNguoiDung.LOGIN_OPERATION);
        Call<ServerResponseNguoiDung> response = requestInterfaceNguoiDung.operation(serverRequestNguoiDung);
        response.enqueue(new Callback<ServerResponseNguoiDung>() {
            @Override
            public void onResponse(Call<ServerResponseNguoiDung> call, Response<ServerResponseNguoiDung> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getActivity(), "successful", Toast.LENGTH_SHORT).show();
                    ketqua = true;
                }
            }

            @Override
            public void onFailure(Call<ServerResponseNguoiDung> call, Throwable t) {
                ketqua = false;
            }
        });
        return ketqua;
    }

    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("connectionResult",connectionResult+" ");
    }
    private class DangNhapTextWatcher implements TextWatcher {
        View view;

        public DangNhapTextWatcher(View view) {
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
                case R.id.edit_dangNhap_email:{
                    validateEmailDangNhap();
                }break;
                case R.id.edit_DangNhap_MatKhau:
                    validateMatKhauDangNhap();
                    break;
            }
        }
    }

    private boolean validateMatKhauDangNhap() {
        if (editTextMatKhau.getText().toString().trim().isEmpty()){
            textInputLayoutMatKhau.setError("Nhập mật khẩu");
            editTextMatKhau.requestFocus();
            return false;
        }else{
            textInputLayoutMatKhau.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmailDangNhap() {
        if (editTextEmailSdt.getText().toString().trim().isEmpty()){
            textInputLayout_Email.setError("Nhập email hoặc số điện thoại");
            editTextEmailSdt.requestFocus();
            return false;
        }else{
            textInputLayout_Email.setErrorEnabled(false);
        }
        return true;
    }
}
