package com.mannydev.rssalluanews.model.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mannydev.rssalluanews.R;
import com.mannydev.rssalluanews.RSS.RssItem;
import com.mannydev.rssalluanews.WebActivity;

import java.util.ArrayList;

/**
 * Адаптер для отображения новостей
 */

public class MyAdapter extends BaseAdapter {
    private final Context ctx;
    private final LayoutInflater lInflater;
    private final ArrayList<RssItem> objects;

    public MyAdapter(Context context, ArrayList<RssItem> itemsList) {
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
            view = lInflater.inflate(R.layout.item_constraint, parent, false);
        }


        final RssItem item = getRss(position);

        // заполняем View в пункте списка данными из Feed:
        ((TextView) view.findViewById(R.id.txtDate)).setText(item.getPubDate().toString());
        ((TextView) view.findViewById(R.id.txtTitle)).setText(item.getTitle().trim());
        ((TextView) view.findViewById(R.id.txtDescription))
                .setText(item.getDescription()
                        .replace("<p>", "")
                        .replace("</p>", "").replace("&quot;", "").trim());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx, WebActivity.class);
                intent.putExtra("url", item.getLink());
                view.getContext().startActivity(intent);

            }
        });

        return view;
    }

    // Новость по позиции
    private RssItem getRss(int position) {
        return ((RssItem) getItem(position));
    }
}
