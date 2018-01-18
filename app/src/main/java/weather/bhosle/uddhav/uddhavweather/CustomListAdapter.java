package weather.bhosle.uddhav.uddhavweather;

/**
 * Created by bhosl on 1/18/2018.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itemname;
    //private final Integer[] imgid;
    private final Integer[] imgid;
    private final String[] description;

    public CustomListAdapter(Activity context, String[] itemname, Integer[] imgid, String[] description) {
        super(context, R.layout.list_days, itemname);
        this.context=context;
        this.itemname=itemname;
        this.imgid = imgid;
        this.description = description;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_days, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.day);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.fcttext);
        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
       // Glide.with(this.getContext())                 // <--this being the current activity I'm trying to show the gif in.
       //         .load(imgurl)
       //         .fitCenter()
        //        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
        //        .into(imageView);
        //imageView.setImageBitmap(bmp[position]);

        extratxt.setText(description[position]);
        return rowView;
    };
}

