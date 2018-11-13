package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.view.View;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public interface IMainViewPresenter {
    View.OnClickListener getFabOnClickListener();

    void onSettingsClick();

    int getCheckedMenuInd();

    void onFilterChanged(int indFilter);
}
