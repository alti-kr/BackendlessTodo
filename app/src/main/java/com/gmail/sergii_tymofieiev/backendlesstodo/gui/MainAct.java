package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gmail.sergii_tymofieiev.backendlesstodo.R;

public class MainAct extends AppCompatActivity implements IMainView{
    IMainViewPresenter presenter;
    private static int FILTER_ALL_ID = R.id.filter_all;
    private static int FILTER_DONE_ID = R.id.filter_done;
    private static int FILTER_LIVE_ID = R.id.filter_live;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        presenter = new MainViewPresenter(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.fab).setOnClickListener(presenter.getFabOnClickListener());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        setItemMenuChecked(menu.findItem(getItemMenuIdByInd(presenter.getCheckedMenuInd())));
        return true;
    }

    private int getItemMenuIdByInd(int checkedMenuInd) {
        switch (checkedMenuInd){
            case 2:{
                return FILTER_DONE_ID;
            }
            case 1:{
                return FILTER_LIVE_ID;
            }
            default:{
                return FILTER_ALL_ID;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            presenter.onSettingsClick();
            return true;
        }else if(id == FILTER_ALL_ID || id == FILTER_DONE_ID || id == FILTER_LIVE_ID){
            setItemMenuChecked(item);
            presenter.onFilterChanged(id==FILTER_DONE_ID?2:(id==FILTER_LIVE_ID?1:0));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setItemMenuChecked(MenuItem item){
        item.setChecked(true);
    }
}
