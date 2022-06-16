package com.example.soccernews.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccernews.databinding.NewsItemBinding;
import com.example.soccernews.domain.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final View.OnClickListener favoriteListener;

    public NewsAdapter(List<News> news, View.OnClickListener favoriteListener){
        this.news = news;
        this.favoriteListener = favoriteListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.getTitle());
        holder.binding.tvDescription.setText(news.getDescription());

        // ADD IMAGE FROM API
        Picasso.get().load(news.getImage())
                .fit()
                .into(holder.binding.ivThumbnail);

        // OPEN LINK FROM API
        holder.binding.btOpenLink.setOnClickListener(view ->{
            Intent browserIntent = new Intent(Intent.ACTION_VIEW);
            browserIntent.setData(Uri.parse(news.getLink()));
            holder.itemView.getContext().startActivity(browserIntent);
        });

        // SHARE BUTTON OF NEWS | native function
        holder.binding.ivShare.setOnClickListener(view ->{
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, news.getLink());
            holder.itemView.getContext().startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        // FAVORITE BUTTON OF NEWS | evento instanciado pelo fragment
        holder.binding.ivFavorite.setOnClickListener( this.favoriteListener);

    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
