package com.example.projeeeeeeeeeect;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeeeeeeeeeect.Adapters.ResourceAdapter;
import com.example.projeeeeeeeeeect.Models.Resource;

import java.util.ArrayList;
import java.util.List;

public class Resources extends AppCompatActivity {

    private RecyclerView recyclerResources;
    private ResourceAdapter adapter;
    private List<Resource> resourceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        recyclerResources = findViewById(R.id.recyclerResources);
        recyclerResources.setLayoutManager(new LinearLayoutManager(this));

        resourceList = new ArrayList<>();
        adapter = new ResourceAdapter(this, resourceList);
        recyclerResources.setAdapter(adapter);

        loadResources();
    }

    private void loadResources() {
        // Mock resources for now
        resourceList.add(new Resource("What is GBV?", "Gender-based violence (GBV) is any harmful act ..."));
        resourceList.add(new Resource("How to Stay Safe", "Always trust your instincts and ..."));
        resourceList.add(new Resource("Emergency Contacts", "Contact 0800-123-456 or 911 in emergencies"));

        adapter.notifyDataSetChanged();
    }
}