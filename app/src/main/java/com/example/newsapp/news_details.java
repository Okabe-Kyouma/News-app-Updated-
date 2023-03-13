package com.example.newsapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.JsonStructure.ApiNews;
import com.squareup.picasso.Picasso;

public class news_details extends AppCompatActivity {

    ApiNews arr;
    ImageView details_image;
    TextView details_content,details_title,details_author,details_time,details_url;
    ProgressDialog dialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

       // ActionBar ab = getSupportActionBar();
        //ab.hide();




        dialog = new ProgressDialog(this);

        details_image = findViewById(R.id.details_image);
        details_content = findViewById(R.id.details_content);
        details_author = findViewById(R.id.details_author_name);
        details_time = findViewById(R.id.details_time);
        details_title = findViewById(R.id.details_title);
        details_url = findViewById(R.id.details_url);

        arr = (ApiNews) getIntent().getSerializableExtra("news");
        dialog.dismiss();

        details_content.setText(arr.getContent());
        details_title.setText(arr.getTitle());
        details_time.setText("Published Time : "+arr.getPublishedAt());
        details_author.setText("Author : "+arr.getAuthor());
        details_url.setText(arr.getUrl());

        details_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = arr.getUrl();


              Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(s));
                startActivity(intent);


            }
        });



        Picasso.get().load(arr.getUrlToImage()).into(details_image);

    }
}