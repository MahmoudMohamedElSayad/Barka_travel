package com.example.barakatravelapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.barakatravelapp.R;
import com.example.barakatravelapp.view.fragment.splashCycle.AboutAppAndIntroFragment;
import com.example.barakatravelapp.view.fragment.splashCycle.SplashFragment;

import butterknife.BindView;

import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadBoolean;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.LoadUserData;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.REMEMBER_ME;
import static com.example.barakatravelapp.data.local.SharedPreferencesManger.clean;
import static com.example.barakatravelapp.utils.HelperMethod.replaceFragment;
import static com.example.barakatravelapp.utils.HelperMethod.replaceFragmentWithAnimation;

public class SplashCycleActivity extends BaseActivity {
//    @BindView(R.id.progressbar)
//    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cycle);
//        clean(this);
        replaceFragment(getSupportFragmentManager(), R.id.splash_activity_fram, new SplashFragment());
        splashScreen();

    }

    private void splashScreen() {
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                if (LoadUserData(SplashCycleActivity.this) != null && LoadBoolean(SplashCycleActivity.this, REMEMBER_ME)) {
                    startActivity(new Intent(SplashCycleActivity.this, HomeCycleActivity.class));
                    finish();
                }else {
//                            replaceFragment(getActivity().getSupportFragmentManager(), R.id.splash_activity_fram, new AboutAppAndIntroFragment());
//                }
//                replaceFragmentWithAnimation(getSupportFragmentManager(), R.id.splash_activity_fram, new AboutAppAndIntroFragment(), "b");
                    startActivity(new Intent(SplashCycleActivity.this, AboutAppActivity.class));

                    finish();
                }
            }
        };
        handler.postDelayed(r, 3000);

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
