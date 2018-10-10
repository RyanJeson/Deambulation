package com.ym.quickrun.network.api;

import com.ym.quickrun.bean.FutureWeatherBean;
import com.ym.quickrun.bean.LocalWeatherBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author: ym  作者 E-mail: 15622113269@163.com
 * date: 2018/10/7
 * desc: 天气接口
 */
public interface WeatherService {

    @GET("v2/{token}/{latitude},{longitude}/realtime.json")
    Flowable<LocalWeatherBean> getLocalWeather(
            @Path("token") String token,
            @Path("latitude") String latitude,
            @Path("longitude") String longitude
    );



    @GET("v2/{token}/{latitude},{longitude}/forecast.json")
    Flowable<FutureWeatherBean> getFutureWeather(
            @Path("token") String token,
            @Path("latitude") String latitude,
            @Path("longitude") String longitude
    );
}
