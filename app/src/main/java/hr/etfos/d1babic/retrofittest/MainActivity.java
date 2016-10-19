package hr.etfos.d1babic.retrofittest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.etfos.d1babic.retrofittest.model.DataModel;
import hr.etfos.d1babic.retrofittest.model.GetWeatherAPI;
import hr.etfos.d1babic.retrofittest.model.Main;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.humidity)
    TextView humidity;

    @BindView(R.id.pressure)
    TextView pressure;

    @BindView(R.id.temperature)
    TextView temperature;

    private final static String KEY_API = "ed916196c822944545567e785f44d10f";

    Retrofit retrofit;
    GetWeatherAPI getWeatherAPI;
    Call<DataModel> call;
    DataModel data;
    Main main;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(isNetworkAvailable()) {
            retrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create()).build();

            getWeatherAPI = retrofit.create(GetWeatherAPI.class);
            call = getWeatherAPI.getWeatherFromAPISyncAndAsync(45.81, 15.98, KEY_API);
            call.enqueue(new Callback<DataModel>() {
                @Override
                public void onResponse(Call<DataModel> call, final Response<DataModel> response) {

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            if(realm.where(DataModel.class).findFirst() == null) {
                                data = realm.createObject(DataModel.class);
                                main = realm.createObject(Main.class);
                                data.setMain(main);

                            } else {
                                data = realm.where(DataModel.class).findFirst();
                            }
                            setRealmObjectData(response);
                            updateUI(data);
                        }
                    });
                }

                private void setRealmObjectData(Response<DataModel> response) {

                    data.getMain().setTemp(response.body().getMain().getTemp());
                    data.getMain().setHumidity(response.body().getMain().getHumidity());
                    data.getMain().setPressure(response.body().getMain().getPressure());
                }

                @Override
                public void onFailure(Call<DataModel> call, Throwable t) {
                    displayDataIfExists(realm.where(DataModel.class).findFirst());
                }
            });
        } else {
            displayDataIfExists(realm.where(DataModel.class).findFirst());
        }
    }

    private void displayDataIfExists(DataModel data) {
        if(data != null) {
            updateUI(data);
        }
    }

    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void updateUI(DataModel body) {
        if(body != null) {
            temperature.append("" + body.getMain().getTemp());
            humidity.append("" + body.getMain().getHumidity());
            pressure.append(""+ body.getMain().getPressure());
        }
    }
}
