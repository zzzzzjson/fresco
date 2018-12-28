package soexample.umeng.com.retrofitdemo.callback;

public interface MyCallback<T>{
    void success(T user);
    void error(T error);
}
