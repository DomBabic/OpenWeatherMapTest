package hr.etfos.d1babic.retrofittest.model;

import io.realm.RealmObject;

/**
 * Created by DominikZoran on 20.10.2016..
 */
public class Wind extends RealmObject {

    private double speed;
    private double deg;
    private String direction;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection() {

        String holder = "";
        if(deg >= 11.25 && deg <= 33.75)
            holder = "NNE";
        else if(deg >= 33.75 && deg <= 56.25)
            holder = "NE";
        else if(deg >= 56.25 && deg <= 78.75)
            holder = "ENE";
        else if(deg >= 78.75 && deg <= 101.25)
            holder ="E";
        else if(deg >= 101.25 && deg <= 123.75)
            holder = "ESE";
        else if(deg >= 123.75 && deg <= 146.25)
            holder = "SE";
        else if(deg >= 146.25 && deg <= 168.75)
            holder = "SSE";
        else if(deg >= 168.75 && deg <= 191.25)
            holder = "S";
        else if(deg >= 191.25 && deg <= 213.75)
            holder = "SSW";
        else if(deg >= 213.75 && deg <= 236.25)
            holder = "SW";
        else if(deg >= 236.25 && deg <= 258.75)
            holder = "WSW";
        else if(deg >= 258.75 && deg <= 281.25)
            holder = "W";
        else if(deg >= 281.25 && deg <= 303.75)
            holder = "WNW";
        else if(deg >= 303.75 && deg <= 326.25)
            holder = "NW";
        else if(deg >= 326.25 && deg <= 348.75)
            holder = "NNW";
        else if(deg >= 348.75 && deg <= 11.25)
            holder = "N";

        this.direction = holder;
    }
}
