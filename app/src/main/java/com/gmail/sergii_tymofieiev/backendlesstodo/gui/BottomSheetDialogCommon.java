package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gmail.sergii_tymofieiev.backendlesstodo.App;
import com.gmail.sergii_tymofieiev.backendlesstodo.R;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.Utils;


/**
 * Created by Sergii Tymofieiev on 18.01.2018.
 */

public class BottomSheetDialogCommon extends BottomSheetDialogFragment implements View.OnClickListener {
    protected IDialogClickListener dialogClickListener;
    protected RelativeLayout headerContainer;
    protected LinearLayout contentContainer;
    protected LinearLayout buttonContainer;
    protected boolean isCancelable = false;
    protected Button button0, button1;
    protected LinearLayout headerImageContainer0;
    protected int headerVisibility = View.VISIBLE;
    protected int buttonsVisibility = View.VISIBLE;
    protected View mainView;
    protected String buttonText0 = "";
    protected String buttonText1 = "";
    protected boolean callDismissInListener;
    protected TextView headerTextView;
    protected String headerText;

    protected void setContent() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_image_container_0: {
                if (dialogClickListener != null) {
                    dialogClickListener.onHeaderButton0Click();
                }
                callDismissInListener = false;
                break;
            }
            case R.id.button_0:
                if (dialogClickListener != null) {
                    dialogClickListener.onButton0Click();
                }
                callDismissInListener = false;
                break;
            case R.id.button_1:
                if (dialogClickListener != null) {
                    dialogClickListener.onButton1Click();
                }
                callDismissInListener = false;
                break;
        }
        dismiss();
    }


    @Override
    public void setupDialog(Dialog dialog, int style) {
        callDismissInListener = true;
        mainView = View.inflate(getContext(), R.layout.dialog_bottom_sheet_common, null);
        dialog.setContentView(mainView);
        dialog.setCancelable(false);
        ((View) mainView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        headerContainer = mainView.findViewById(R.id.header_container);
        headerImageContainer0 = mainView.findViewById(R.id.header_image_container_0);
        headerTextView = mainView.findViewById(R.id.dial_header_text);
        contentContainer = mainView.findViewById(R.id.content_container);
        buttonContainer = mainView.findViewById(R.id.button_container);
        button0 = mainView.findViewById(R.id.button_0);
        button1 = mainView.findViewById(R.id.button_1);

        headerImageContainer0.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        setCancelable(isCancelable);

        if(!TextUtils.isEmpty(headerText)){
            headerTextView.setText(headerText);
        }

        button0.setText(buttonText0);

        if (TextUtils.isEmpty(buttonText1)) {
            button1.setVisibility(View.GONE);
        } else {
            button1.setText(buttonText1);
        }
        headerContainer.setVisibility(headerVisibility);
        buttonContainer.setVisibility(buttonsVisibility);
        setContent();
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(((View) mainView.getParent()));
        bottomSheetBehavior.setPeekHeight(Utils.getScreenHeight(App.getContext()));
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (dialogClickListener != null && callDismissInListener) {
            dialogClickListener.onDismiss();
        }

    }

    public static class Builder {
        protected int headerVisibility = View.VISIBLE;
        protected int buttonsVisibility = View.VISIBLE;
        protected IDialogClickListener dialogClickListener;
        protected boolean isCancelable = false;
        protected String headerText;
        protected String buttonText0;
        protected String buttonText1;

        public Builder setHeaderVisibility(int visibility) {
            headerVisibility = visibility;
            return this;
        }

        public Builder setButtonsVisibility(int visibility) {
            buttonsVisibility = visibility;
            return this;
        }

        public Builder setCancelable(boolean isCancelable) {
            this.isCancelable = isCancelable;
            return this;
        }

        public Builder setOnClickListener(IDialogClickListener dialogClickListener) {
            this.dialogClickListener = dialogClickListener;
            return this;
        }
        public Builder setHeaderText(String headerText) {
            this.headerText = headerText;
            return this;
        }
        public Builder setButtonText0(String buttonText) {
            this.buttonText0 = buttonText;
            return this;
        }

        public Builder setButtonText1(String buttonText) {
            this.buttonText1 = buttonText;
            return this;
        }


    }

    public interface IDialogClickListener {
        void onHeaderButton0Click();

        void onButton0Click();

        void onButton1Click();

        void onDismiss();
    }

}
