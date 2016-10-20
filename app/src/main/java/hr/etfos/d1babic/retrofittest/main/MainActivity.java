package hr.etfos.d1babic.retrofittest.main;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hr.etfos.d1babic.retrofittest.R;
import hr.etfos.d1babic.retrofittest.main.ListViewAdapter.Adapter;
import hr.etfos.d1babic.retrofittest.model.Coord;
import hr.etfos.d1babic.retrofittest.model.DataModel;
import hr.etfos.d1babic.retrofittest.model.GetWeatherAPI;
import hr.etfos.d1babic.retrofittest.model.Main;
import hr.etfos.d1babic.retrofittest.model.Wind;
import hr.etfos.d1babic.retrofittest.presentation.Presenter;
import hr.etfos.d1babic.retrofittest.presentation.PresenterImpl;
import hr.etfos.d1babic.retrofittest.view.MainView;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.input_box)
    EditText inputBox;

    @OnClick(R.id.button_search)
    public void fetchData() {
        if(!inputBox.getText().toString().isEmpty() && isNetworkAvailable()) {

            String cityInput = inputBox.getText().toString();

            retrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            getWeatherAPI = retrofit.create(GetWeatherAPI.class);
            call = getWeatherAPI.getWeatherFromAPI("" + cityInput, KEY_API);

            call.enqueue(new Callback<DataModel>() {
                @Override
                public void onResponse(Call<DataModel> call, final Response<DataModel> response) {

                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            initDataObject();
                            presenter.setData(data);
                            presenter.setResponse(response);
                            //TODO: Update ListView with data collected.
                            //TODO: If data already exist, update with latest information
                        }
                    });
                }

                @Override
                public void onFailure(Call<DataModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Failed to collect data!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @BindView(R.id.city_listview)
    ListView cityListView;

    private final static String KEY_API = "ed916196c822944545567e785f44d10f";

    private Retrofit retrofit;
    private GetWeatherAPI getWeatherAPI;
    private Call<DataModel> call;

    private DataModel data;
    private Main main;
    private Coord coord;
    private Wind wind;

    private Realm realm;

    private Presenter presenter;
    private Adapter adapter;

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
        initPresenter();
        initAdapter();
        //TODO: Adapter, display data in listview
    }

    private void initPresenter() {
        presenter = new PresenterImpl();
        presenter.setView(this);
    }

    private void initAdapter() {
        adapter = new Adapter();
        cityListView.setAdapter(adapter);
        if(!realm.isEmpty()) {
            presenter.getRealmData();
        }
    }

    private boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void initDataObject() {
        data = realm.createObject(DataModel.class);
        main = realm.createObject(Main.class);
        coord = realm.createObject(Coord.class);
        wind = realm.createObject(Wind.class);
        data.setMain(main);
        data.setCoord(coord);
        data.setWind(wind);
    }

    @Override
    public void setAdapterData(List<DataModel> data) {
        adapter.setAdapterItems(data);
    }
}
