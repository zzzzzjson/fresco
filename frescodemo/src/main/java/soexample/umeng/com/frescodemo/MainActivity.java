package soexample.umeng.com.frescodemo;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class MainActivity extends AppCompatActivity {
    private String gifUrl = "http://img.soogif.com/qiiUcher0DGOHk39ugCWUAA5nWvy57JE.gif";
    private SimpleDraweeView Image_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        Uri uri = Uri.parse("https://m.360buyimg.com//n0//jfs//t9004//210//1160833155//647627//ad6be059//59b4f4e1N9a2b1532.jpg");
//       draweeView = findViewById(R.id.Image_view);
//       draweeView.setImageURI(uri);
        //设置支持JPEG渐进式展示(从模糊到清晰)
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();

        //  GIF动画图片
        BaseControllerListener<ImageInfo> baseControllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                animatable.start();
            }

            //加载失败
            @Override
            public void onFailure(String id, Throwable throwable) {
                Toast.makeText(MainActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
            }
        };
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)//播放GIF图片
                .setControllerListener(baseControllerListener)
                .setImageRequest(imageRequest)
                .build();
        Image_view.setController(controller);
    }

    private void initView() {
        Image_view = (SimpleDraweeView) findViewById(R.id.Image_view);
    }
}
