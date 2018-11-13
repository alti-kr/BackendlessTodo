package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.sergii_tymofieiev.backendlesstodo.App;
import com.gmail.sergii_tymofieiev.backendlesstodo.R;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.DateUtils;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.Utils;
import com.gmail.sergii_tymofieiev.backendlesstodo.data.DataItem;
import com.gmail.sergii_tymofieiev.backendlesstodo.data.IDataItem;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class EditItemView extends BottomSheetDialogCommon {
    private IDataItem dataItem;
    private EditText notesView;
    private CheckBox checkBoxView;
    private TextView dateView;
    @Override
    protected void setContent() {
        Utils.getViewByLayoutId(App.getContext(), R.layout.item_edit_view, contentContainer, true);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        notesView = mainView.findViewById(R.id.edit_text_item);
        checkBoxView = mainView.findViewById(R.id.item_done);
        dateView = mainView.findViewById(R.id.time_item);
        if(dataItem != null){
            notesView.setText(dataItem.getNotes());
            checkBoxView.setChecked(dataItem.isDone());
            dateView.setText(DateUtils.getFormattedDateAsString(DateUtils.DateFormatCustom.ddMMyyyy_HHmm, dataItem.getDate()));
        }else {
            dateView.setText(DateUtils.getFormattedDateAsString(DateUtils.DateFormatCustom.ddMMyyyy_HHmm, System.currentTimeMillis()));
        }

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
                processData();
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

    private void processData() {
        if(TextUtils.isEmpty(notesView.getText().toString())){
            Utils.makeToast(App.getContext(), R.string.input_item_error,Toast.LENGTH_SHORT).show();
            return;
        }
        if(dataItem == null){
            dataItem = new DataItem();
        }

        dataItem.setNotes(notesView.getText().toString());
        dataItem.setTimestamp(System.currentTimeMillis());
        dataItem.setDone(checkBoxView.isChecked());

        if (dialogClickListener != null) {
            ((IEditViewClickListener)dialogClickListener).onButton0Click(dataItem);
        }
    }

    public static class Builder extends BottomSheetDialogCommon.Builder {
        private IDataItem dataItem;


        public Builder setHeaderText(String headerText) {
            super.setHeaderText(headerText);
            return this;
        }


        public Builder setDataItem(IDataItem dataItem) {
            this.dataItem = dataItem;
            return this;
        }
        public Builder setHeaderVisibility(int visibility) {
            super.setHeaderVisibility(visibility);
            return this;
        }
        public Builder setButtonsVisibility(int visibility) {
            super.setButtonsVisibility(visibility);
            return this;
        }

        public Builder setCancelable(boolean isCancelable) {
            super.setCancelable(isCancelable);
            return this;
        }
        public Builder setOnClickListener(IDialogClickListener dialogClickListener) {
            super.setOnClickListener(dialogClickListener);
            return this;
        }

        public Builder setButtonText0(String buttonText) {
            super.setButtonText0(buttonText);
            return this;
        }
        public Builder setButtonText1(String buttonText) {
            super.setButtonText1(buttonText);
            return this;
        }
        public EditItemView build() {
            EditItemView dialog = new EditItemView();
            dialog.isCancelable = isCancelable;
            dialog.headerVisibility = headerVisibility;
            dialog.buttonsVisibility = buttonsVisibility;
            dialog.buttonText0 = buttonText0;
            dialog.buttonText1 = buttonText1;
            dialog.headerText = headerText;
            dialog.dialogClickListener = dialogClickListener;
            dialog.dataItem = dataItem;
            return dialog;
        }
    }

    public interface IEditViewClickListener extends IDialogClickListener{
        void onButton0Click(IDataItem dataItem);
    }
}
