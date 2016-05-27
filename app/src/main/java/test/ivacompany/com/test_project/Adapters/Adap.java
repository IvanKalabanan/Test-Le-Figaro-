package test.ivacompany.com.test_project.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import test.ivacompany.com.test_project.R;
import test.ivacompany.com.test_project.entity.List_Articles;
import test.ivacompany.com.test_project.ui.Article_Activity;


public class Adap extends RecyclerView.Adapter<Adap.PersonViewHolder> {

    private final ArrayList<List_Articles> entries;
    private final Activity activity;

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    public Adap(final Activity a, final ArrayList<List_Articles> entries) {
        this.entries = entries;
        this.activity = a;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        List_Articles articles = entries.get(i);
        personViewHolder.itemTitle.setText(articles.title);
        personViewHolder.itemSubTitle.setText(articles.subtitle);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(activity));
        if (!articles.link.isEmpty()) {
            imageLoader.displayImage(articles.link, personViewHolder.photo);
        } else {
            personViewHolder.photo.setImageResource(R.drawable.no_image);
        }

    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cv;
        TextView itemTitle;
        TextView itemSubTitle;
        ImageView photo;

        PersonViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cv = (CardView) itemView.findViewById(R.id.cv);
            itemTitle = (TextView) itemView.findViewById(R.id.itemTitle);
            itemSubTitle = (TextView) itemView.findViewById(R.id.item_subtitle);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }
           @Override
        public void onClick(View v) {
               List_Articles articles = entries.get(getPosition());
               Intent intent = new Intent(v.getContext(), Article_Activity.class);
               intent.putExtra("id", articles.id);
               intent.putExtra("title", articles.title);
               intent.putExtra("link", articles.link);
               intent.putExtra("subtitle", articles.subtitle);
               v.getContext().startActivity(intent);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
