package app.android.gaf004.listview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import app.android.gaf004.listview.models.Fruta;
import app.android.gaf004.listview.R;

import static android.view.LayoutInflater.from;

public class FruitAdapter extends BaseAdapter {


    private Context context;
    private int layout;
    private List<Fruta> names;

    public FruitAdapter(Context context, int layout, List<Fruta> names){
        this.context = context;
        this.layout = layout;
        this.names = names;
    }
    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        //Comprobamos que la vista es nula para inflarla
        if(convertView == null) {
            //Pillamos el LayoutInflater
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Asignamos a la vista el leyout
            convertView = layoutInflater.inflate(layout, null);
            holder.textView = (TextView) convertView.findViewById(R.id.textViewName);
            holder.textViewOrigen = (TextView) convertView.findViewById(R.id.textViewOrigen);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);

        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Fruta f = (Fruta) getItem(position);
        holder.textView.setText(String.valueOf(f.getNombre()));
        holder.textViewOrigen.setText(String.valueOf("Origen: "+f.getOrigen()));
        holder.imageView.setImageDrawable(f.getIcono());

        return convertView;
    }

    public class ViewHolder{
        private TextView textView;
        private TextView textViewOrigen;
        private ImageView imageView;
    }


}
