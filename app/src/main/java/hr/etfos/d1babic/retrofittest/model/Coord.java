package hr.etfos.d1babic.retrofittest.model;

import io.realm.RealmObject;

/**
 * Created by DominikZoran on 20.10.2016..
 */
public class Coord extends RealmObject {

    private double lon;
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

}
