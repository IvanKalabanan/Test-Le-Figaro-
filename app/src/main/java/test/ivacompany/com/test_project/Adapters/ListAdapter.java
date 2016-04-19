package test.ivacompany.com.test_project.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import test.ivacompany.com.test_project.R;
import test.ivacompany.com.test_project.entity.List_Articles;

public class ListAdapter extends ArrayAdapter<List_Articles> {

    private final Activity activity;
    private final ArrayList<List_Articles> entries;

    public ListAdapter(final Activity a, final int textViewResourceId, final ArrayList<List_Articles> entries) {

        super(a, textViewResourceId, entries);
        this.entries = entries;
        activity = a;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_details_view, parent, false);
            holder = new ViewHolder();
            holder.txt_itemTitle = (TextView) v.findViewById(R.id.itemTitle);
            holder.txt_itemSubtitle = (TextView) v.findViewById(R.id.item_subtitle);
            holder.itemImage = (ImageView) v.findViewById(R.id.photo);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        List_Articles articles = entries.get(position);
        if (articles != null) {
            holder.txt_itemTitle.setText(articles.title);
            holder.txt_itemSubtitle.setText(articles.subtitle);
            ImageLoader imageLoader = ImageLoader.getInstance();
            imageLoader.init(ImageLoaderConfiguration.createDefault(getContext()));
            if(!articles.link.isEmpty()) {
                imageLoader.displayImage(articles.link, holder.itemImage);
            } else {
                holder.itemImage.setImageResource(R.drawable.no_image);
            }
        }
        return v;

    }

    static class ViewHolder {
       public TextView txt_itemTitle;
        public TextView txt_itemSubtitle;
        public ImageView itemImage;
    }
}
