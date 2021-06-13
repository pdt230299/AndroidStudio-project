package com.example.btl;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;

import java.util.List;

public class Planet_Adapter extends BaseAdapter {
    private Context context; // bối cảnh gọi
    private int layout;     // layout
    private List<Planet> listPlanet;

    public Planet_Adapter(Context context, int layout, List<Planet> listPlanet) {
        this.context = context;
        this.layout = layout;
        this.listPlanet = listPlanet;
    }

    @Override
    public int getCount() {
        return listPlanet.size();
    }

    @Override
    public Object getItem(int position) {
        return listPlanet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView txtName,txtDes;
        ImageView imageView;
        ConstraintLayout layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { // position ?
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder(); // tùy chỉnh một View theo ý của bạn. Bạn có thể làm chủ được các đối tượng do bạn tạo ra.
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);

            viewHolder.txtName = convertView.findViewById(R.id.textViewName);
            viewHolder.imageView = convertView.findViewById(R.id.imageView);
            viewHolder.txtDes = convertView.findViewById(R.id.textViewDes);
            viewHolder.layout = convertView.findViewById(R.id.layout_item);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Planet planet = listPlanet.get(position);
        viewHolder.txtName.setText(planet.getName());
        String url = planet.getUrl();
        Glide.with(context).load(url).into(viewHolder.imageView);

        if (planet.getMota().length() > 80){
            String textMota = planet.getMota().substring(0,80);
            textMota += "...";
            viewHolder.txtDes.setText(textMota);
        }

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailPlanet.class);
                intent.putExtra("planet", planet);
                context.startActivity(intent);

            }
        });

        return convertView;
    }

}