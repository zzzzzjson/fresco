package soexample.umeng.com.retrofitdemo.model;

import com.google.gson.Gson;

import java.util.Map;

import soexample.umeng.com.retrofitdemo.callback.MyCallback;
import soexample.umeng.com.retrofitdemo.utils.RetrofitUtils;

public class ModelImpl implements Model {
    private MyCallback myCallback;
    @Override
    public void requestData(String url, Map<String, String> map, final Class clazz, final MyCallback callback) {
        this.myCallback=callback;
        //一个小小的建造者模式带给大家
        RetrofitUtils.getInstance().post(url, map).setHttpListener(new RetrofitUtils.HttpListener() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, clazz);
                callback.success(o);
            }

            @Override
            public void onError(String error) {
               callback.error(error);
            }
        });

    }
}
