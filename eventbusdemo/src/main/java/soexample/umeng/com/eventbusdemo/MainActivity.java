package soexample.umeng.com.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import soexample.umeng.com.eventbusdemo.eventbean.EventMessage;
import soexample.umeng.com.eventbusdemo.utils.TypeUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private TextView get_Content;

    /**
     * MainActivity.java
     * author Z
     * created 2018/12/26
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        btn_one = (Button) findViewById(R.id.btn_one);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_three = (Button) findViewById(R.id.btn_three);

        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        get_Content = (TextView) findViewById(R.id.get_Content);
        get_Content.setOnClickListener(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getInfo(EventMessage message) {
        if (message.getEventType() != 0 && message.getEventType() == TypeUtils.MAIN_EVENT) {
          finish();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
           EventBus.getDefault().postSticky(new EventMessage("啦啦啦","嘻嘻",18,TypeUtils.MAIN_EVENT));
           startActivity(new Intent(this,EventBusDemoActivity.class));
                break;
            case R.id.btn_two:

                break;
            case R.id.btn_three:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
