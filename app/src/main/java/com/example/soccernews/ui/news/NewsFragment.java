package com.example.soccernews.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.soccernews.MainActivity;
import com.example.soccernews.databinding.FragmentNewsBinding;
import com.example.soccernews.ui.adapter.NewsAdapter;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            binding.rvNews.setAdapter(new NewsAdapter(news, updatedNews -> {
                MainActivity activity = (MainActivity) getActivity();
                if(activity != null){
                    activity.getDb().newsDao().save(updatedNews);
                }
            }));
        });

        newsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state){
                case DOING:
                    //TODO iniciar swipeRefreshLayout (Loading)
                    break;
                case DONE:
                    //TODO finalizar swipeRefreshLayout (Loading)
                    break;
                case ERROR:
                    //TODO finazilar swipeRefreshLayout (Loading)
                    //TODO Mostrar um erro genérico.
            }

        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}