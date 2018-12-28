package soexample.umeng.com.retrofitdemo.view;

public interface IView<T> {
    void success(T user);
    void error(T error);
}
