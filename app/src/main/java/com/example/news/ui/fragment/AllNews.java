package com.example.news.ui.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.news.contract.AddFragment;
import com.example.news.contract.PresenterContract.ArticleInterFace;
import com.example.news.model.Article;
import com.example.news.adapter.ListAdapter;
import com.example.news.presenter.ArticlePresenter;
import com.example.news.ui.MainActivity;
import com.example.news.model.News;
import com.example.news.R;
import java.util.List;

public class AllNews extends Fragment implements AddFragment, ArticleInterFace {

    View viewAll;
    RecyclerView recyclerView;
    ListAdapter adapter;
    List<Article> list;
    ProgressBar loading;
    ArticlePresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewAll = inflater.inflate(R.layout.all_news, container, false);
        InitUi(viewAll);
        return viewAll;
    }

    public void InitUi(View view){
        loading = view.findViewById(R.id.progressBar_all);
        recyclerView = view.findViewById(R.id.recycler_all);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        presenter = new ArticlePresenter(this);
        presenter.getArticles();
        ((MainActivity)getActivity()).setAddFragment(this);

    }

    @Override
    public void stopLoading() {
        loading.setVisibility(View.GONE);
    }

    @Override
    public void startLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void noInternetConnection(String message) {
        Toast.makeText(getActivity(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void add(News news) {
        list = news.getArticles();
        adapter = new ListAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void getArticle(List<Article> list) {
        adapter = new ListAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
    }

}
