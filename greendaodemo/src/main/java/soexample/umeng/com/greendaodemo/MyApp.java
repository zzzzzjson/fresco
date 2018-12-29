package soexample.umeng.com.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import soexample.umeng.com.greendaodemo.mydao.DaoMaster;
import soexample.umeng.com.greendaodemo.mydao.DaoSession;

public class MyApp extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {
        //第一步创建OpenHelper类
        DaoMaster.OpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "zz.db");
        //开启一个可写数据库
        SQLiteDatabase database = openHelper.getWritableDatabase();
        //通过DaoMaster封装
        DaoMaster master = new DaoMaster(database);
        daoSession = master.newSession();
    }
    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
