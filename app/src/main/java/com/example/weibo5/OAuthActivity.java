package com.example.weibo5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.weibo5.Util.Constants;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.UsersAPI;
import com.sina.weibo.sdk.openapi.models.User;

public class OAuthActivity extends AppCompatActivity implements View.OnClickListener, WeiboAuthListener {

    private Button btnSsoLogIn;
    private Button btnWeibLogIn;
    private Button btnAllInOneLogIn;

    private SsoHandler mSsoHandler;

    private AuthInfo mAuthInfo;

    private Oauth2AccessToken mAccessToken;

    UsersAPI mUserAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);

        mAuthInfo = new AuthInfo(this, Constants.APP_KEY,Constants.REDIRECT_URL,Constants.SCOPE);
        mSsoHandler = new SsoHandler(this,mAuthInfo);
        initView();
    }

    private void initView() {
        btnSsoLogIn = findViewById(R.id.logIn_btn_SsoLogin);
        btnSsoLogIn.setOnClickListener(this);
        btnWeibLogIn = findViewById(R.id.logIn_btn_WebLogin);
        btnWeibLogIn.setOnClickListener(this);
        btnAllInOneLogIn = findViewById(R.id.logIn_btn_AllInOneLogin);
        btnAllInOneLogIn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logIn_btn_SsoLogin:
                startSsoWeiBoLogIn();
                break;
            case R.id.logIn_btn_WebLogin:
                startWebWeiBoLogIn();
                break;
            case R.id.logIn_btn_AllInOneLogin:
                startAllInOneWeiBoLogIn();
                break;
        }

    }

    private void startAllInOneWeiBoLogIn() {
        mSsoHandler.authorize(this);
    }

    private void startWebWeiBoLogIn() {
        mSsoHandler.authorizeWeb(this);
    }

    private void startSsoWeiBoLogIn() {
        mSsoHandler.authorizeClientSso(this);
    }

    @Override
    public void onComplete(Bundle bundle) {
        mAccessToken = Oauth2AccessToken.parseAccessToken(bundle);

        if (mAccessToken.isSessionValid()){
            Constants.token = mAccessToken.getToken();
            Toast.makeText(OAuthActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
            getUserInfo();

            Intent intent = new Intent(OAuthActivity.this,MainPage.class);
            startActivity(intent);
            finish();
        }else{
            String code = bundle.getString("code");
            if (!TextUtils.isEmpty(code)){
                Toast.makeText(OAuthActivity.this,"签名不正确",Toast.LENGTH_SHORT).show();;
            }
        }
    }

    @Override
    public void onWeiboException(WeiboException e) {
        Toast.makeText(OAuthActivity.this,"授权异常",Toast.LENGTH_SHORT).show();;

    }
    @Override
    public void onCancel() {
        Toast.makeText(OAuthActivity.this,"授权取消",Toast.LENGTH_SHORT).show();;

    }
    private void getUserInfo() {
        mUserAPI = new UsersAPI(OAuthActivity.this,Constants.APP_KEY,mAccessToken);
        System.out.println("mUsersAPI ------>  " + mUserAPI.toString());

        long uid = Long.parseLong(mAccessToken.getUid());
        System.out.println("----------uid---------->   " + uid);
        mUserAPI.show(uid,mListener);
    }

    private RequestListener mListener = new RequestListener() {
        @Override
        public void onComplete(String s) {
            if (!TextUtils.isEmpty(s)){
                User user = User.parse(s);
                String nickName = user.screen_name;
                Constants.name = user.screen_name;
                Constants.gender = user.gender;
                Constants.location = user.location;
            }
        }

        @Override
        public void onWeiboException(WeiboException e) {
            e.printStackTrace();
            Toast.makeText(OAuthActivity.this,"获取用户个人信息 出现异常",Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (mSsoHandler != null){
            mSsoHandler.authorizeCallBack(requestCode,resultCode,data);
        }
    }


}
