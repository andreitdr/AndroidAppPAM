package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SimpleDetailActivity extends AppCompatActivity {

    TextView nameText, shortDescText;
    ImageView imageView;
    Button detailsButton, editButton; // Added editButton
    DatabaseHelper db;
    Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_detail);

        nameText = findViewById(R.id.textName);
        shortDescText = findViewById(R.id.textShortDesc);
        imageView = findViewById(R.id.imageViewDetail);
        detailsButton = findViewById(R.id.buttonMore);
        editButton = findViewById(R.id.buttonEdit); // Find the new button

        db = new DatabaseHelper(this);
        int memberId = getIntent().getIntExtra("memberId", -1);
        member = db.getMember(memberId);

        if (member != null) {
            nameText.setText(member.getName());
            shortDescText.setText(member.getShortDescription());
            int imageRes = getResources().getIdentifier(member.getImageName(), "drawable", getPackageName());
            imageView.setImageResource(imageRes);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SimpleDetailActivity.this, CrudActivity.class);
                    intent.putExtra("memberId", member.getId()); // Pass member ID for editing
                    startActivity(intent);
                }
            });

            detailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SimpleDetailActivity.this, FullDetailActivity.class);
                    intent.putExtra("memberId", member.getId());
                    startActivity(intent);
                }
            });
        }
    }
}