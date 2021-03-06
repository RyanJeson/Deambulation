package com.ym.deambulation;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.ym.deambulation.di.component.ApiComponent;
import com.ym.deambulation.di.component.DaggerApiComponent;
import com.ym.deambulation.di.module.ApiModule;
import com.ym.deambulation.utils.AppUtils;
import com.ym.deambulation.utils.CrashHandler;
import com.ym.deambulation.utils.LogUtils;
import com.ym.deambulation.utils.NetworkUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * ┏┓   ┏┓
 *┏┛┻━━━┛┻┓
 *┃       ┃
 *┃   ━   ┃
 *┃ ┳┛ ┗┳ ┃
 *┃       ┃
 *┃   ┻   ┃
 *┃       ┃
 *┗━┓   ┏━┛
 *  ┃   ┃  神兽保佑
 *  ┃   ┃  永无BUG！
 *  ┃   ┗━━━┓
 *  ┃       ┣┓
 *  ┃       ┏┛
 *  ┗┓┓┏━┳┓┏┛
 *   ┃┫┫ ┃┫┫
 *   ┗┻┛ ┗┻┛
 *
 * @author: ym
 * date: 2018/9/10
 * desc: APP启动页面
 */
public class DeambulationApplication extends Application {
    private static DeambulationApplication mContext;
    private ApiComponent mApiComponent;
    private Set<Activity> mActivities;

    /**
     * 天气token
     */
    public static String token = "hIQzmm3L67tu-uHA";

    /**
     * 经度
     */
    public static String longitude;

    /**
     * 纬度
     */
    public static String latitude;

    @Override
    public void onCreate() {
        super.onCreate();
        initLogic();
        mContext = this;
        initComponent();
        initCrashHandler();
        LeakCanary.install(this);
    }

    /**
     * 初始化网络组件
     */
    private void initComponent() {
        mApiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .build();
    }

    /**
     * 添加Activity
     *
     * @param activity activity
     */
    public void addActivity(Activity activity) {
        if (mActivities == null) {
            mActivities = new HashSet<>();
        }
        mActivities.add(activity);
    }

    /**
     * 删除Activity
     *
     * @param activity activity
     */
    public void removeActivity(Activity activity) {
        if (mActivities == null) {
            mActivities = new HashSet<>();
        }
        mActivities.remove(activity);
    }


    /**
     * 初始化工具类
     */
    private void initLogic() {
        //App工具类
        AppUtils.init(this);
        //Log工具类
        LogUtils.init(this);
        //网络工具类
        NetworkUtils.startNetService(this);
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }

    /**
     * 获取Application
     */
    public static DeambulationApplication getInstance() {
        return mContext;
    }


    /**
     * 初始化崩溃日志
     */
    private void initCrashHandler() {
        CrashHandler.getInstance().init(this);
    }

}
