package hr.etfos.d1babic.retrofittest.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DominikZoran on 11.10.2016..
 */
public interface GetWeatherAPI {

    @GET("/data/2.5/weather")
    Call<DataModel> getWeatherFromAPI(@Query("q")String city, @Query("APPID")String apiKey);
}
