package com.example.projeeeeeeeeeect;

import android.os.Bundle;
import android.widget.Toast; // New Import

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeeeeeeeeeect.Adapters.ArticleAdapter;
import com.example.projeeeeeeeeeect.Models.Article; // Import Article Model

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call; // New Import
import retrofit2.Callback; // New Import
import retrofit2.Response; // New Import

public class Articles extends AppCompatActivity {

    private RecyclerView recyclerarticles;
    private ArticleAdapter adapter;
    private List<Article> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

        recyclerarticles = findViewById(R.id.recyclerArticles);
        recyclerarticles.setLayoutManager(new LinearLayoutManager(this));

        articleList = new ArrayList<>();
        adapter = new ArticleAdapter(this, articleList);
        recyclerarticles.setAdapter(adapter);

        loadArticles(); // Calls the API method
    }

    private void loadArticles() {
        // 1. Get the API service
        ApiService apiService = RetrofitClient.getApiService();

        // 2. Prepare the API call to fetch articles
        Call<List<Article>> call = apiService.getArticles();

        // 3. Execute the call asynchronously
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    articleList.clear();
                    articleList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    Toast.makeText(Articles.this, "Articles loaded successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Articles.this, "Failed to load articles. Code: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(Articles.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}