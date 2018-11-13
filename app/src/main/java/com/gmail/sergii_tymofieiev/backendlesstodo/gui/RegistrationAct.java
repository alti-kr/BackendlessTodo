package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gmail.sergii_tymofieiev.backendlesstodo.App;
import com.gmail.sergii_tymofieiev.backendlesstodo.R;
/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class RegistrationAct extends FragmentActivity implements  IRegistrationView{
    private EditText editPhoneNumber;
    private Button button0;
    private IRegistrationViewPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_view);

        presenter = new RegistrationViewPresenter(this);

        editPhoneNumber = findViewById(R.id.activation_phone);
        button0 = findViewById(R.id.button_0);
        editPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.processPhoneNumber(editPhoneNumber.getText().toString());
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onButtonClick(editPhoneNumber.getText().toString());
            }
        });
        onOffButton(false);

    }

    @Override
    public void onOffButton(boolean onOff) {
        button0.setEnabled(onOff);
    }

    @Override
    public void finishRegistration() {
        Intent startIntent = new Intent(App.getContext(), MainAct.class);
        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startIntent);
        finish();
    }
}
