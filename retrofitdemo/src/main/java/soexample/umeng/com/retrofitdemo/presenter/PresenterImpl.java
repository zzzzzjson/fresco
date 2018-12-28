package soexample.umeng.com.retrofitdemo.presenter;

import java.util.Map;

import soexample.umeng.com.retrofitdemo.callback.MyCallback;
import soexample.umeng.com.retrofitdemo.model.ModelImpl;
import soexample.umeng.com.retrofitdemo.view.IView;

public class PresenterImpl implements Presenter {
    private IView iView;
    private ModelImpl model;

    public PresenterImpl(IView iView) {
        this.iView = iView;
        model = new ModelImpl();
    }

    @Override
    public void startRequest(String url, Map<String, String> map, Class clazz) {
        model.requestData(url, map, clazz, new MyCallback() {
            @Override
            public void success(Object user) {
                iView.success(user);
            }

            @Override
            public void error(Object error) {
                iView.error(error);
            }
        });
    }
    public void onDetch() {
        if (iView != null) {
            iView = null;
        }
        if (model != null) {
            model = null;
        }
    }
}
