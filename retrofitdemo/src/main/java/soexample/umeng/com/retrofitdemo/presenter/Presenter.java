package soexample.umeng.com.retrofitdemo.presenter;

import java.util.Map;

public interface Presenter {
    void startRequest(String url, Map<String,String>map,Class clazz);
}
