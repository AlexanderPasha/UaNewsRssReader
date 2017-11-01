package com.mannydev.rssalluanews.model.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mannydev.rssalluanews.R;
import com.mannydev.rssalluanews.RSSNews;
import com.mannydev.rssalluanews.model.Feed;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Адаптер для отображения новостных лент
 */

public class FeedAdapter extends BaseAdapter {
    private final Context ctx;
    private final LayoutInflater lInflater;
    private final ArrayList<Feed> objects;

    public FeedAdapter(Context context, ArrayList<Feed> itemsList) {
        ctx = context;
        objects = itemsList;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.item_feed_constraint, parent, false);
        }

        final Feed item = getRss(position);

        // заполняем View в пункте списка данными из FeedList:
        Picasso.with(ctx).load(item.getUrlLogo())
                .fit()
                .centerInside()
                .into((ImageView) view.findViewById(R.id.imageLogo));
        ((TextView) view.findViewById(R.id.txtName)).setText(item.getName());
        ((TextView) view.findViewById(R.id.txtDescription)).setText(item.getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getName().equals("Оценить приложение") == false) {
                    Intent intent = new Intent(ctx, RSSNews.class);
                    intent.putExtra("url", item.getUrlFeed());
                    intent.putExtra("title", item.getName());
                    intent.putExtra("logo", item.getUrlLogo());
                    view.getContext().startActivity(intent);

                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.mannydev.rssalluanews"));
                    view.getContext().startActivity(intent);
                }

            }
        });

        return view;
    }

    // Feed по позиции
    private Feed getRss(int position) {
        return ((Feed) getItem(position));
    }
}
