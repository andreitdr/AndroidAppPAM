package com.example.myapplication;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FullDetailActivity extends AppCompatActivity {

    TextView nameText, fullDescText;
    Button websiteButton;
    Member member;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_detail);

        nameText = findViewById(R.id.textFullName);
        fullDescText = findViewById(R.id.textFullDesc);
        websiteButton = findViewById(R.id.buttonVisitWebsite);

        db = new DatabaseHelper(this);
        int memberId = getIntent().getIntExtra("memberId", -1);
        member = db.getMember(memberId);

        if (member != null) {
            nameText.setText(member.getName());
            fullDescText.setText(member.getFullDescription());
        }

        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (member != null && member.getWebUrl() != null && !member.getWebUrl().isEmpty()) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(member.getWebUrl()));
                    startActivity(browserIntent);
                }
            }
        });
    }
}
