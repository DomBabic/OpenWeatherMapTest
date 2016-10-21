package hr.etfos.d1babic.retrofittest.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataModel extends RealmObject{

    private Main main;
    private Coord coord;
    private Wind wind;

    @PrimaryKey
    private String name;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}