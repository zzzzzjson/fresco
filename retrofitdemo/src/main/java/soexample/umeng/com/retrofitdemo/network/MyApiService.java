package soexample.umeng.com.retrofitdemo.network;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

//用来处理接口
public interface MyApiService {
    //Retrofit+rxjava
    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String,String> map);
    @POST
    Observable<ResponseBody> post(@Url String url,@QueryMap Map<String,String> map);
}
