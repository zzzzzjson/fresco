package soexample.umeng.com.retrofitimagedemo;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;
//用来处理接口
public interface ApiService {
    @Multipart
    @POST
    Observable<ResponseBody> upLoadImage(@Url String url, @QueryMap Map<String,String> map, @Part MultipartBody.Part file);
}

