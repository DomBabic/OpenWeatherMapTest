package hr.etfos.d1babic.retrofittest.model;

import io.realm.RealmObject;

public class DataModel extends RealmObject{

    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}