package hr.etfos.d1babic.retrofittest.presentation;

import hr.etfos.d1babic.retrofittest.model.DataModel;
import hr.etfos.d1babic.retrofittest.view.MainView;
import retrofit2.Response;

/**
 * Created by DominikZoran on 20.10.2016..
 */
public interface Presenter {

    void setData(DataModel data);
    void setResponse(Response<DataModel> response);

    void setRealmObjectData();

    void setView(MainView view);

    void getRealmData();
}
