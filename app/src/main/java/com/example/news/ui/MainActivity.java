package com.example.news.ui;

import android.graphics.Color;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.contract.AddFragment;
import com.example.news.contract.PresenterContract.CategoryContract;
import com.example.news.model.News;
import com.example.news.R;
import com.example.news.adapter.TabsAdapter;
import com.example.news.presenter.BusinessPresenter;
import com.example.news.presenter.HealthPresenter;
import com.example.news.presenter.SciencePresenter;
import com.example.news.presenter.SportPresenter;
import com.example.news.presenter.TechnologyPresenter;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements CategoryContract {

    ProgressBar loading;
    AddFragment addFragment;
    TabsAdapter tabsAdapter;
    ViewPager viewPager;
    private DrawerLayout drawerLayout;
    private NavigationView nav;
    private BottomNavigationView bottom_nav;
    private ActionBarDrawerToggle mDrawerToggle;
    News sportNews,scienceNews,healthNews,technologyNews,businessNews;

    public void setAddFragment(AddFragment addFragment) {
        this.addFragment = addFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabsAdapter = new TabsAdapter(getSupportFragmentManager());

        SportPresenter sportPresenter = new SportPresenter(this);
        sportPresenter.getSport();

        BusinessPresenter businessPresenter = new BusinessPresenter(this);
        businessPresenter.getBusiness();

        HealthPresenter healthPresenter = new HealthPresenter(this);
        healthPresenter.getHealth();

        TechnologyPresenter technologyPresenter = new TechnologyPresenter(this);
        technologyPresenter.getTechnology();

        SciencePresenter sciencePresenter = new SciencePresenter(this);
        sciencePresenter.getScience();

        initui();
        initSideMenu();
        itemMenu();
    }

    public void initui(){
        loading = findViewById(R.id.progress_home);
        viewPager = findViewById(R.id.viewPager_home);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(tabsAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        bottom_nav.setSelectedItemId(R.id.action_all_news);
                        break;
                    case 1:
                        bottom_nav.setSelectedItemId(R.id.action_profile);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        bottom_nav = findViewById(R.id.bottom_nav);
        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.action_all_news:
                        viewPager.setCurrentItem(0,true);
                        break;
                    case R.id.action_profile:
                        viewPager.setCurrentItem(1,true);
                        break;
                }
                return true;
            }
        });
    }

    private void initSideMenu() {
        drawerLayout = findViewById(R.id.drawerLayout2);
        nav = findViewById(R.id.nav);

        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        mDrawerToggle.getDrawerArrowDrawable().setColor(Color.WHITE);

        drawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();

        //get header view
        View headerView = nav.getHeaderView(0);
        TextView textView_name = headerView.findViewById(R.id.textView_name);
        CircleImageView profile_image = headerView.findViewById(R.id.profile_image);

        GoogleSignInAccount account = getIntent().getParcelableExtra("user account");
        if (account != null){
            textView_name.setText(account.getDisplayName());
            Glide.with(MainActivity.this).load(account.getPhotoUrl()).into(profile_image);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void itemMenu() {
        nav = findViewById(R.id.nav);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.sport:
                        getSport(sportNews);
                        break;
                    case R.id.business:
                        getBusiness(businessNews);
                        break;
                    case R.id.health:
                        getHealth(healthNews);
                        break;
                    case R.id.technology:
                        getTechnology(technologyNews);
                        break;
                    case R.id.science:
                        getScience(scienceNews);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void getSport(News sportNews) {
        this.sportNews=sportNews;
        addFragment.add(sportNews);
    }
    @Override
    public void getBusiness(News businessNews) {
        this.businessNews=businessNews;
        addFragment.add(businessNews);
    }
    @Override
    public void getTechnology(News technologyNews) {
        this.technologyNews=technologyNews;
        addFragment.add(technologyNews);
    }
    @Override
    public void getHealth(News healthNews) {
        this.healthNews=healthNews;
        addFragment.add(healthNews);
    }
    @Override
    public void getScience(News scienceNews) {
        this.scienceNews=scienceNews;
        addFragment.add(scienceNews);
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
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
