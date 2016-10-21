package hr.etfos.d1babic.retrofittest.presentation;

import java.util.List;

import hr.etfos.d1babic.retrofittest.model.DataModel;
import hr.etfos.d1babic.retrofittest.view.MainView;
import io.realm.Realm;
import retrofit2.Response;

/**
 * Created by DominikZoran on 20.10.2016..
 */
public class PresenterImpl implements Presenter {

    private DataModel data;
    private Response<DataModel> response;
    private MainView view;

    @Override
    public void setData(DataModel data) {
        this.data = data;
    }

    @Override
    public void setResponse(Response<DataModel> response) {
        this.response = response;
    }

    @Override
    public void setView(MainView view) {
        this.view = view;
    }

    @Override
    public void setRealmObjectData() {
        data.getMain().setTemp(response.body().getMain().getTemp());
        data.getMain().setHumidity(response.body().getMain().getHumidity());
        data.getMain().setPressure(response.body().getMain().getPressure());

        data.getCoord().setLat(response.body().getCoord().getLat());
        data.getCoord().setLon(response.body().getCoord().getLon());

        data.getWind().setSpeed(response.body().getWind().getSpeed());
        data.getWind().setDeg(response.body().getWind().getDeg());
        data.getWind().setDirection();
    }

    @Override
    public void getRealmData() {
        Realm realm = Realm.getDefaultInstance();
        List<DataModel> data = realm.where(DataModel.class).findAll();
        view.setAdapterData(data);
    }
}