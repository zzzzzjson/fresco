package soexample.umeng.com.retrofitdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SpUtils {
    private static final String SP_NAME = "lvxx";

    /**
     * 保存
     * @param context 上下文
     * @param key key值
     * @param value value值
     */
    public static void save(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 获取字符串
     * @param context
     * @param key
     * @param defultValue 默认值
     * @return
     */
    public static String getString(Context context, String key, String defultValue){
        return context.getSharedPreferences(SP_NAME, MODE_PRIVATE).getString(key, defultValue);
    }
}
