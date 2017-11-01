package com.mannydev.rssalluanews.model.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mannydev.rssalluanews.R;
import com.mannydev.rssalluanews.model.Feed;

import java.util.List;

/**
 * Created by manny on 31.10.17.
 */

public class FeedsAdapter extends RecyclerView.Adapter<FeedsViewHolder> {

    private List<Feed> list;

    public FeedsAdapter(List<Feed> list) {
        this.list = list;
    }

    @Override
    public FeedsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed_constraint, parent, false);
        return new FeedsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FeedsViewHolder holder, int position) {
        holder.onBindView(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
