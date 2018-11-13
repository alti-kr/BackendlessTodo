package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.util.Log;
import android.view.View;

import com.gmail.sergii_tymofieiev.backendlesstodo.App;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.Constants;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.SharedPreferencesWrapper;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class MainViewPresenter implements IMainViewPresenter {
    private IMainView iView;
    private View.OnClickListener fabOnClickListener;
    public MainViewPresenter(IMainView iView){
        this.iView = iView;
    }


    @Override
    public View.OnClickListener getFabOnClickListener() {
        if(fabOnClickListener == null){
            fabOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFabClick();
                }
            };

        }
        return fabOnClickListener;
    }

    @Override
    public void onSettingsClick() {

    }

    @Override
    public int getCheckedMenuInd() {
        return SharedPreferencesWrapper.getInt(App.getContext(),Constants.SP_KEY_FILTER_INDEX,0);
    }

    @Override
    public void onFilterChanged(int indFilter) {
        SharedPreferencesWrapper.putInt(App.getContext(),Constants.SP_KEY_FILTER_INDEX,indFilter);
    }

    private void onFabClick() {
        Log.d("My_log","onFabClick");
    }
}
/*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/