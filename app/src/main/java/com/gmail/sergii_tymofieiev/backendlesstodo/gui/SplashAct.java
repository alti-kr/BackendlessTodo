package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.gmail.sergii_tymofieiev.backendlesstodo.R;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class SplashAct extends Activity implements ISplashView {
    ISplashViewPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_view);
        presenter = new SplashViewPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void startView(Intent startIntent) {
        if (startIntent != null) {
            startActivity(startIntent);
            finish();
        }
    }
}
