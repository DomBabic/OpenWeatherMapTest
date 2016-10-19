package hr.etfos.d1babic.retrofittest.model;

import io.realm.RealmObject;

/**
 * Created by DominikZoran on 19.10.2016..
 */
public class Main extends RealmObject{
    double temp;
    double pressure;
    int humidity;

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
