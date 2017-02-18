package dang.ugi.com.presenter.NguoiDung;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import dang.ugi.com.model.Entities.NguoiDung;

/**
 * Created by DANG on 9/8/2016.
 */
public class MdangNhap {
   private AccessTokenTracker accessTokenTracker;
   private AccessToken accessToken;
  private Context context;
    private  NguoiDung nguoiDung;
    private ImplNguoiDungPresenter implNguoiDungPresenter;
    public MdangNhap(Context context) {
        this.context = context;
        implNguoiDungPresenter = new ImplNguoiDungPresenter(context);
    }

    public AccessToken layAccessTokenHientai(){
        AccessTokenTracker accessTokenTracker  = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };
        accessToken = AccessToken.getCurrentAccessToken();
        return  accessToken ;
    }

    public void destroyAcessToken(){
        accessTokenTracker.stopTracking();
    }

}
