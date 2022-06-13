package com.example.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        // TODO Remover mock de noticias
        List<News> news = new ArrayList<>();
        news.add(new News("Testando", "Descricao do Testando"));
        news.add(new News("Queda no campo", "Desfalque meu fi"));
        news.add(new News("Grande coisa", "Time perde contra time de apenas um jogador"));

        this.news.setValue(news);
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}