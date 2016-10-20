package hr.etfos.d1babic.retrofittest.main.ListViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import hr.etfos.d1babic.retrofittest.R;
import hr.etfos.d1babic.retrofittest.model.DataModel;

/**
 * Created by DominikZoran on 20.10.2016..
 */
public class Adapter extends BaseAdapter {

    //TODO: ViewHolder pattern, implement once you get this working

    private final List<DataModel> dataModelList = new ArrayList<>();

    public void setAdapterItems(List<DataModel> dataList) {
        dataModelList.clear();
        dataModelList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_item_layout, viewGroup, false);
            viewHolder = new ViewHolder(view);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)view.getTag();
        }

        if(viewHolder != null) {
            viewHolder.lat.setText("Latitude: " + dataModelList.get(i).getCoord().getLat());
            viewHolder.lon.setText("Longitude: " + dataModelList.get(i).getCoord().getLon());

            viewHolder.city.setText("City: " + dataModelList.get(i).getName());

            viewHolder.temperature.setText("Temperature: " + dataModelList.get(i).getMain().getTemp());
            viewHolder.humidity.setText("Humidity: " + dataModelList.get(i).getMain().getHumidity());
            viewHolder.pressure.setText("Pressure: " + dataModelList.get(i).getMain().getPressure());

            viewHolder.speed.setText("Wind speed: " + dataModelList.get(i).getWind().getSpeed());
            viewHolder.direction.setText("Wind direction: " + dataModelList.get(i).getWind()
                    .getDirection());
        }

        return view;
    }
}
