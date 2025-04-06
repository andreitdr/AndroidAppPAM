package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CrudActivity extends AppCompatActivity {

    EditText editName, editSubtitle, editShortDesc, editFullDesc, editImageName, editWebUrl;
    Button btnSave, btnDelete;
    DatabaseHelper db;
    Member member;
    int memberId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);

        editName = findViewById(R.id.editName);
        editSubtitle = findViewById(R.id.editSubtitle);
        editShortDesc = findViewById(R.id.editShortDesc);
        editFullDesc = findViewById(R.id.editFullDesc);
        editImageName = findViewById(R.id.editImageName);
        editWebUrl = findViewById(R.id.editWebUrl);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        db = new DatabaseHelper(this);
        memberId = getIntent().getIntExtra("memberId", -1);

        if (memberId != -1) {
            member = db.getMember(memberId);
            if (member != null) {
                editName.setText(member.getName());
                editSubtitle.setText(member.getSubtitle());
                editShortDesc.setText(member.getShortDescription());
                editFullDesc.setText(member.getFullDescription());
                editImageName.setText(member.getImageName());
                editWebUrl.setText(member.getWebUrl());
            }
            btnDelete.setVisibility(View.VISIBLE);
        } else {
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setOnClickListener(view -> {
            String name = editName.getText().toString().trim();
            String subtitle = editSubtitle.getText().toString().trim();
            String shortDesc = editShortDesc.getText().toString().trim();
            String fullDesc = editFullDesc.getText().toString().trim();
            String imageName = editImageName.getText().toString().trim();
            String webUrl = editWebUrl.getText().toString().trim();

            if (name.isEmpty() || imageName.isEmpty()) {
                Toast.makeText(this, "Name and image name are required", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(CrudActivity.this, MainActivity.class);

            if (memberId == -1) {
                db.insertMember(new Member(0, name, subtitle, shortDesc, fullDesc, imageName, webUrl));
                Toast.makeText(this, "Member added", Toast.LENGTH_SHORT).show();
            } else {
                db.updateMember(new Member(memberId, name, subtitle, shortDesc, fullDesc, imageName, webUrl));
                Toast.makeText(this, "Member updated", Toast.LENGTH_SHORT).show();
            }
            startActivity(intent);
            finish();
        });

        btnDelete.setOnClickListener(view -> {
            if (member != null) {
                db.deleteMember(member.getId());
                Toast.makeText(this, "Member deleted", Toast.LENGTH_SHORT).show();
            }
            Intent intent = new Intent(CrudActivity.this, MainActivity.class); // Create intent for MainActivity
            startActivity(intent);
            finish();
        });
    }
}
