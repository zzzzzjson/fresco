package soexample.umeng.com.retrofitdemo.model;

import java.util.Map;

import soexample.umeng.com.retrofitdemo.callback.MyCallback;

public interface Model{
    void requestData(String url, Map<String,String> map, Class clazz, MyCallback callback);
}
