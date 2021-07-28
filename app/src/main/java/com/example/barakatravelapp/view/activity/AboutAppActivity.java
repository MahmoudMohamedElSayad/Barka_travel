package com.example.barakatravelapp.view.activity;

import android.os.Bundle;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.view.fragment.splashCycle.AboutAppAndIntroFragment;

import static com.example.barakatravelapp.utils.HelperMethod.replaceFragment;


public class AboutAppActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app_cycle);
        replaceFragment(getSupportFragmentManager(), R.id.about_app_activity_fram, new AboutAppAndIntroFragment());



    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
