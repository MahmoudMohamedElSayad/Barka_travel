package com.example.barakatravelapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.barakatravelapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.ButterKnife;


public class HomeCycleActivity extends BaseActivity
//        implements BottomNavigationView.OnNavigationItemSelectedListener
{


//    public HomeFragment homeFragment;


    public BottomNavigationView bottomNavView;
//    private ClientData clientData;

    public HomeCycleActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cycle);
        ButterKnife.bind(this);
//        child = this;
//        homeFragment=new HomeFragment();
//        clientData = LoadUserData(this);
//        replaceFragment(getSupportFragmentManager(), R.id.home_activity_fragment,new DiscoverFragment());
        bottomNavView = (BottomNavigationView) findViewById(R.id.nav_view);
//        bottomNavView.setOnNavigationItemSelectedListener(this);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.home_activity_fragment);
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.getNavController());

    }



    private void displayView(int position) {
        switch (position) {
            case 0:
                Intent intentHome = new Intent(HomeCycleActivity.this,HomeCycleActivity.class);
                startActivity(intentHome);
                break;
            case 1:
                Intent intentHome2 = new Intent(HomeCycleActivity.this,HomeCycleActivity.class);
                startActivity(intentHome2);
                break;}}
    public void setNavigation(String visibility) {
        bottomNavView = (BottomNavigationView) findViewById(R.id.nav_view);
        if (visibility.equals("v")) {
            bottomNavView.setVisibility(View.VISIBLE);
        } else if (visibility.equals("g")) {
            bottomNavView.setVisibility(View.GONE);
        }

    }

//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.navigation_account) {
//            replaceFragment(getSupportFragmentManager(), R.id.home_activity_fragment,new AccountFragment());
//        } else if (id == R.id.navigation_hotels) {
////            if(clientData!=null) {
//                replaceFragment(getSupportFragmentManager(), R.id.home_activity_fragment, new HottelsFragment());
////            }else {
////                goToRegisterFirst(this);
////                goLogin = true;
////                backFromLogin=true;
////            }
//        } else if (id == R.id.navigation_flight) {
//            replaceFragment(getSupportFragmentManager(), R.id.home_activity_fragment, new FlightsFragment());
//
//        } else if (id == R.id.navigation_hajj) {
//            replaceFragment(getSupportFragmentManager(), R.id.home_activity_fragment, new HujjFragment());
//        }
//        else if (id == R.id.navigation_discover) {
//            replaceFragment(getSupportFragmentManager(), R.id.home_activity_fragment, new DiscoverFragment());
//        }
//
//        return true;
//    }






}
