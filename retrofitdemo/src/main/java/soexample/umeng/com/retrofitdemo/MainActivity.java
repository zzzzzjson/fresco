package soexample.umeng.com.retrofitdemo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.retrofitdemo.bean.LoginBean;
import soexample.umeng.com.retrofitdemo.bean.UserInfoBean;
import soexample.umeng.com.retrofitdemo.presenter.PresenterImpl;
import soexample.umeng.com.retrofitdemo.view.IView;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.et_main_name)
    EditText etMainName;
    @BindView(R.id.et_main_pw)
    EditText etMainPw;
    @BindView(R.id.button_main_login)
    Button buttonMainLogin;
    @BindView(R.id.button_main_get_user_info)
    Button buttonMainGetUserInfo;
    private PresenterImpl presenter;
    private final int TYPE_LOGIN = 0;
    private final int TYPE_UPLOAD_IMG = TYPE_LOGIN + 1;
    private final int TYPE_GET_USER_INFO = TYPE_UPLOAD_IMG + 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new PresenterImpl(this);
    }

    @OnClick({R.id.et_main_name, R.id.et_main_pw, R.id.button_main_login, R.id.button_main_get_user_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_main_name:

                break;
            case R.id.et_main_pw:
                break;
            case R.id.button_main_login:
                checkPermission(TYPE_LOGIN);
                break;
            case R.id.button_main_get_user_info:
                checkPermission(TYPE_GET_USER_INFO);
                break;
        }
    }

    private void checkPermission(int type) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, type);
            } else {
                startRequest(type);
            }
        } else {
            startRequest(type);
        }
    }

    private void startRequest(int type) {
        switch (type) {
            case TYPE_LOGIN:
                HashMap<String, String> map = new HashMap<>();
                map.put("mobile", etMainName.getText().toString().trim());
                map.put("password", etMainPw.getText().toString().trim());
                presenter.startRequest(Contacts.USER_LOGIN, map, LoginBean.class);
                break;
            case TYPE_GET_USER_INFO:
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("uid", "23489");
                presenter.startRequest(Contacts.USER_INFO, map1, UserInfoBean.class);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startRequest(requestCode);
        }
    }

    @Override
    public void success(Object user) {
        //因为是复用一个处理的  用java的一个关键字instanceof处理一下即可
        if (user instanceof LoginBean) {
            Toast.makeText(this, ((LoginBean) user).getMsg(), Toast.LENGTH_SHORT).show();
        } else if (user instanceof UserInfoBean) {
            Toast.makeText(this, ((UserInfoBean) user).getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void error(Object error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetch();
            presenter = null;
        }
    }
}
