package com.example.projeeeeeeeeeect.counselor;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projeeeeeeeeeect.R;

public class PublishResource extends AppCompatActivity {

    private EditText titleEditText, contentEditText;
    private Button publishBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_resource);

        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        publishBtn = findViewById(R.id.publishBtn);

        publishBtn.setOnClickListener(v -> {
            String title = titleEditText.getText().toString().trim();
            String content = contentEditText.getText().toString().trim();

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Please enter title and content", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: Push resource to database
            Toast.makeText(this, "Resource published (dummy)", Toast.LENGTH_SHORT).show();
            titleEditText.setText("");
            contentEditText.setText("");
        });
    }
}
