package soexample.umeng.com.retrofitimagedemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import soexample.umeng.com.retrofitimagedemo.utils.RetrofitUtils;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.UpLoad_Btn)
    Button UpLoadBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.UpLoad_Btn)
    public void onViewClicked() {
        Map<String,String> map=new HashMap<>();
        map.put("uid","23585");
        File file = new File(Environment.getExternalStorageDirectory() + "/Pictures/e.jpg");
        //上传图片需要MultipartBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RetrofitUtils.getInstance().upLoadImage(Contacts.UP_LOAD_IMAGE,map,body).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Log.e("onSuccess",jsonStr);
            }

            @Override
            public void onError(String error) {
                Log.e("onError",error);
            }
        });
    }
}
