package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.gmail.sergii_tymofieiev.backendlesstodo.App;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.Constants;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.SharedPreferencesWrapper;

import org.w3c.dom.Text;

public class SplashViewPresenter implements ISplashViewPresenter {
    private static final int WHAT = 0;
    private static final long TIMEOUT = 3000;
    ISplashView iView;
    public SplashViewPresenter(ISplashView iView){
        this.iView = iView;
    }

    @Override
    public void onResume() {
        workHandler.sendEmptyMessageDelayed(WHAT,TIMEOUT);
    }


    Handler workHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                afterSplash();
            }
        }
    };

    private void afterSplash() {
        Intent startIntent;
        if(TextUtils.isEmpty(SharedPreferencesWrapper.getString(App.getContext(),Constants.SP_KEY_PHONE_AS_HASH,""))) {
            startIntent = new Intent(App.getContext(), RegistrationAct.class);
        } else {
            startIntent = new Intent(App.getContext(), MainAct.class);
        }
        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        iView.startView(startIntent);
    }


}
