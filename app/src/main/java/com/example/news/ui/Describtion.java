package com.example.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.contract.PresenterContract.BaiseInterFacePresenter;
import com.example.news.model.Article;
import com.squareup.picasso.Picasso;

public class Describtion extends AppCompatActivity{

    ImageView pic;
    TextView date,title,dec,link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_describtion);

        date = findViewById(R.id.desc_date);
        pic = findViewById(R.id.imageView5);
        title = findViewById(R.id.desc_title);
        dec = findViewById(R.id.desc_describtion);
        link = findViewById(R.id.desc_link);

        Bundle b = getIntent().getExtras();
        if (b.isEmpty()){
            Toast.makeText(this,"net error",Toast.LENGTH_LONG).show();
        }else {
            Article article = (Article) b.getSerializable("data");
            date.setText("Time :"+" "+ article.getPublishedAt());
            title.setText(article.getTitle());
            dec.setText(article.getDescription());
            link.setText("Website :" +" "+ article.getUrl());
            Picasso.get().load(article.getUrlToImage()).into(pic);
        }
    }
}
