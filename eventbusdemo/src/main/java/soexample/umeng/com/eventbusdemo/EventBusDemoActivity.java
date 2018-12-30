package soexample.umeng.com.eventbusdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import soexample.umeng.com.eventbusdemo.eventbean.EventMessage;

public class EventBusDemoActivity extends AppCompatActivity {
    private TextView Event_Text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_demo);
        initView();
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getInfo(EventMessage message){
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
        Event_Text.setText(message.toString());
    }
    private void initView() {
        Event_Text = (TextView) findViewById(R.id.Event_Text);
        Event_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean a=true;
             if (a=true){
                 EventBus.getDefault().register(EventBusDemoActivity.this);
                 a=false;
             }
            }
        });
    }
}
