package com.mannydev.rssalluanews.model.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mannydev.rssalluanews.R;
import com.mannydev.rssalluanews.RSSNews;
import com.mannydev.rssalluanews.model.Feed;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;


/**
 * Created by manny on 31.10.17.
 */

public class FeedsViewHolder extends RecyclerView.ViewHolder {
    private Feed feed;
    private ImageView imageLogo;
    private TextView txtName;
    private TextView txtDescription;

    public FeedsViewHolder(View itemView) {
        super(itemView);
        imageLogo = itemView.findViewById(R.id.imageLogo);
        txtName = itemView.findViewById(R.id.txtName);
        txtDescription = itemView.findViewById(R.id.txtDescription);
    }

    public void onBindView(Feed f) {
        this.feed = f;
        Picasso.with(itemView.getContext().getApplicationContext())
                .load(feed.getUrlLogo())
                .transform(new CropCircleTransformation())
                .fit()
                .centerInside()
                .into((ImageView) itemView.findViewById(R.id.imageLogo));
        txtName.setText(feed.getName());
        txtDescription.setText(feed.getDescription());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (feed.getName().equals("Оценить приложение") == false) {
                    Intent intent = new Intent(itemView.getContext().getApplicationContext(), RSSNews.class);
                    intent.putExtra("url", feed.getUrlFeed());
                    intent.putExtra("title", feed.getName());
                    intent.putExtra("logo", feed.getUrlLogo());
                    view.getContext().startActivity(intent);

                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.mannydev.rssalluanews"));
                    view.getContext().startActivity(intent);
                }
            }
        });
    }
}
