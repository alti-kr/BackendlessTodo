package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.text.TextUtils;

import com.gmail.sergii_tymofieiev.backendlesstodo.App;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.Constants;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.SharedPreferencesWrapper;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.Utils;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class RegistrationViewPresenter implements IRegistrationViewPresenter{
    IRegistrationView iView;
    public RegistrationViewPresenter(IRegistrationView iView){
        this.iView = iView;
    }

    @Override
    public void processPhoneNumber(String inputString) {
        if (Utils.isValidPhoneNumber(inputString)) {
            iView.onOffButton(true);
        } else {
            iView.onOffButton(false);
        }
    }

    @Override
    public void onButtonClick(String inputString) {
        // TODO confirm dialog
        if(!TextUtils.isEmpty(inputString)){
            // TODO strong hash generator or something other
            SharedPreferencesWrapper.putString(App.getContext(),Constants.SP_KEY_PHONE_AS_HASH, String.valueOf(inputString.hashCode()));
            iView.finishRegistration();
        }else {
            // TODO show alert dialog;
        }

    }
}
