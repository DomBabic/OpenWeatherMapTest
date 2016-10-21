package hr.etfos.d1babic.retrofittest.ui.main.ListViewAdapter;

import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hr.etfos.d1babic.retrofittest.R;

/**
 * Created by DominikZoran on 20.10.2016..
 */
public class ViewHolder {

    @BindView(R.id.temperature)
    TextView temperature;

    @BindView(R.id.humidity)
    TextView humidity;

    @BindView(R.id.pressure)
    TextView pressure;

    @BindView(R.id.city)
    TextView city;

    @BindView(R.id.lat)
    TextView lat;

    @BindView(R.id.lon)
    TextView lon;

    @BindView(R.id.speed)
    TextView speed;

    @BindView(R.id.direction)
    TextView direction;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
