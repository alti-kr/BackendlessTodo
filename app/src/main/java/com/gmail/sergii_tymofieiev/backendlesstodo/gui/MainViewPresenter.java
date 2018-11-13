package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.gmail.sergii_tymofieiev.backendlesstodo.App;
import com.gmail.sergii_tymofieiev.backendlesstodo.R;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.Constants;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.SharedPreferencesWrapper;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.Utils;
import com.gmail.sergii_tymofieiev.backendlesstodo.data.IDataItem;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class MainViewPresenter implements IMainViewPresenter {
    private TodoItemsAdapter itemsListAdapter;
    private IMainView iView;
    private View.OnClickListener fabOnClickListener;
    private RecyclerItemTouchHelper.RecyclerItemTouchHelperListener recyclerItemTouchHelperListener;
    private EditItemView editView;

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

    @Override
    public RecyclerView.Adapter getItemsListAdapter() {
        if(itemsListAdapter == null){
            itemsListAdapter = new TodoItemsAdapter();
        }
        return itemsListAdapter;
    }

    @Override
    public RecyclerItemTouchHelper.RecyclerItemTouchHelperListener getRecyclerItemTouchHelperListener() {
        if (recyclerItemTouchHelperListener == null) {
            recyclerItemTouchHelperListener = new RecyclerItemTouchHelper.RecyclerItemTouchHelperListener() {

                @Override
                public void onSwiped(Object itemData, int position) {
                    //rejectExchange((ExchangeJettonAdapter.ExchangeJettonItem) itemData);
                }
            };
        }
        return recyclerItemTouchHelperListener;
    }

    private void onFabClick() {
        showEditView(null);
    }

    private void showEditView(IDataItem dataItem){
        if(editView != null){
            return;
        }
        editView = new EditItemView.Builder()
                .setCancelable(false)
                .setHeaderVisibility(View.VISIBLE)
                .setButtonsVisibility(View.VISIBLE)
                .setButtonText0(Utils.getStringById(R.string.btn_ok))
                .setButtonText1(Utils.getStringById(R.string.btn_cancel))
                .setHeaderText(Utils.getStringById(R.string.edit_item_header))
               .setDataItem(dataItem)
                .setOnClickListener(new EditItemView.IEditViewClickListener() {
                    @Override
                    public void onHeaderButton0Click() {
                        onDialogCancel();
                    }

                    @Override
                    public void onButton0Click() {

                    }

                    @Override
                    public void onButton1Click() {
                        onDialogCancel();
                    }

                    @Override
                    public void onDismiss() {
                        onDialogCancel();
                    }

                    @Override
                    public void onButton0Click(IDataItem dataItem) {
                        updateItem(dataItem);
                    }
                })
                .build();
        FragmentManager fragmentManager = iView.getActivity().getSupportFragmentManager();
        if (fragmentManager != null) {
            editView.show(fragmentManager, "BottomConfirmPhoneDialog");
        }
    }

    private void updateItem(IDataItem dataItem) {
        Log.d("My_log","dataItem = "+ dataItem.getNotes());
    }

    private void onDialogCancel() {
    }
}
