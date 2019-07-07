package com.example.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.news.model.viewModel.AllNewsModel;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.news.R;
import com.example.news.model.Article;
import com.example.news.ui.Describtion;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemHolder> {
    Context context;
    List<Article> list;
    AllNewsModel model;


    public ListAdapter(Context context, List<Article> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_item,viewGroup,false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        Article article = list.get(i);
        itemHolder.bindData(article);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{

        ConstraintLayout layout;
        ImageView imageView;
        TextView title,deco,date;
        ProgressBar bar;
        NavigationView nav;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            bar = itemView.findViewById(R.id.progressBar);
            imageView = itemView.findViewById(R.id.image_news);
            title = itemView.findViewById(R.id.text_dec);
            deco = itemView.findViewById(R.id.text_title);
            layout=itemView.findViewById(R.id.layout);
            nav=itemView.findViewById(R.id.nav);
            date = itemView.findViewById(R.id.text_date);

        }

        public void bindData(final Article article){

            model = ViewModelProviders.of((FragmentActivity) context).get(AllNewsModel.class);
            title.setText(article.getTitle());
            deco.setText(article.getDescription());
            date.setText(article.getPublishedAt());
            bar.setVisibility(View.VISIBLE);
            Picasso.get().load(article.getUrlToImage()).into(imageView);
            bar.setVisibility(View.GONE);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",article);
                    Intent intent = new Intent(context, Describtion.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }

}
