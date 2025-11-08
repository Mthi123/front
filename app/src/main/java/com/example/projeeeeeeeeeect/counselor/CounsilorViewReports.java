package com.example.projeeeeeeeeeect.counselor;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projeeeeeeeeeect.R;

import java.util.ArrayList;
import java.util.List;

public class CounsilorViewReports extends AppCompatActivity {
    private ListView reportsListView;
    private List<String> dummyReports; // Placeholder for database reports

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsilor_view_reports);

        reportsListView = findViewById(R.id.reportsListView);

        // TODO: Replace dummyReports with actual database fetch
        dummyReports = new ArrayList<>();
        dummyReports.add("Report 1: Harassment");
        dummyReports.add("Report 2: Assault");
        dummyReports.add("Report 3: Stalking");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dummyReports
        );
        reportsListView.setAdapter(adapter);

        Toast.makeText(this, "Reports loaded (dummy data)", Toast.LENGTH_SHORT).show();
    }
}