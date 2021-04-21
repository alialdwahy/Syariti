package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */

public class   Carsad extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<CarsI> homecar;


    public Carsad(HomeCar context, int layout, ArrayList<CarsI> homecar) {
        this.context = context;
        this.layout = layout;
        this.homecar = homecar;
    }
    public Carsad(HomeUser context, int layout, ArrayList<CarsI> homecar) {
        this.context = context;
        this.layout = layout;
        this.homecar = homecar;
    }

    @Override
    public int getCount() {
        return homecar.size();
    }

    @Override
    public Object getItem(int position) {
        return homecar.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice , txtmodel ,txttype ;


    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtmodel =(TextView) row.findViewById(R.id.textView4);
            holder.txtName = (TextView) row.findViewById(R.id.naaa);
            holder.txtPrice = (TextView) row.findViewById(R.id.textView15);
            holder.imageView = (ImageView) row.findViewById(R.id.icon);
            holder.txttype = (TextView) row.findViewById(R.id.textView14);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        CarsI list = homecar.get(position);
        holder.txtName.setText(list.getName());
        holder.txtPrice.setText(list.getPrice());
        holder.txtmodel.setText(list.getModel());
        holder.txttype.setText(list.getType());

        byte[] Image = list.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(Image, 0, Image.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;



    }
}
