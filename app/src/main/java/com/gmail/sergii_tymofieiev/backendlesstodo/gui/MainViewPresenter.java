package com.gmail.sergii_tymofieiev.backendlesstodo.gui;

import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.persistence.QueryOptions;
import com.gmail.sergii_tymofieiev.backendlesstodo.App;
import com.gmail.sergii_tymofieiev.backendlesstodo.R;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.Constants;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.SharedPreferencesWrapper;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.Utils;
import com.gmail.sergii_tymofieiev.backendlesstodo.data.DataItem;
import com.gmail.sergii_tymofieiev.backendlesstodo.data.DataItemFactory;
import com.gmail.sergii_tymofieiev.backendlesstodo.data.IDataItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class MainViewPresenter implements IMainViewPresenter {
    private TodoItemsAdapter itemsListAdapter;
    private IMainView iView;
    private View.OnClickListener fabOnClickListener;
    private RecyclerItemTouchHelper.RecyclerItemTouchHelperListener recyclerItemTouchHelperListener;
    private EditItemView editView;
    private TodoItemsAdapter.ItemsAdapterListener adapterListListener;

    public MainViewPresenter(IMainView iView){
        this.iView = iView;
        Backendless.setUrl(Constants.SERVER_URL);
        Backendless.initApp(App.getContext(), Constants.APPLICATION_ID, Constants.API_KEY);
        Backendless.Persistence.mapTableToClass( "InvertersData", DataItem.class );
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
    public void makeContent() {
        DataQueryBuilder dataQueryBuilder = DataItemFactory.getDataQueryBuilder(getCheckedMenuInd(), DataItemFactory.SORT_DIRECTION.TIME_ASC);

        Backendless.Data.of(Constants.DATA_TABLE_NAME).find( dataQueryBuilder,
                new AsyncCallback<List<Map>>()
                {
                    @Override
                    public void handleResponse( List<Map> response ){
                        processResponse(response);

                    }

                    @Override
                    public void handleFault( BackendlessFault fault ){
                        // TODO something
                        Log.d("My_log","handleFault = "+ fault);
                        // use the getCode(), getMessage() or getDetail() on the fault object
                        // to see the details of the error
                    }
                });
    }

    private void processResponse(List<Map> response) {
        ArrayList<IDataItem> itemsList = DataItemFactory.parseResponse(response);
        itemsListAdapter.addItems(itemsList);
        itemsListAdapter.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.Adapter getItemsListAdapter() {
        if(itemsListAdapter == null){
            itemsListAdapter = new TodoItemsAdapter();
            itemsListAdapter.setItemsAdapterListener(getAdapterListListener());
        }
        return itemsListAdapter;
    }

    private TodoItemsAdapter.ItemsAdapterListener getAdapterListListener() {
        if(adapterListListener == null){
            adapterListListener = new TodoItemsAdapter.ItemsAdapterListener() {
                @Override
                public void onItemClick(Object itemData, int position) {
                    showEditView((IDataItem) itemData);
                }
            };
        }
        return adapterListListener;
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
        editView = null;
        Log.d("My_log","dataItem = "+ dataItem.getNotes());
        final IDataStore<Map> testTableDataStore = Backendless.Data.of(Constants.DATA_TABLE_NAME);
        testTableDataStore.save(DataItemFactory.dataItemToMap(dataItem), new AsyncCallback<Map>() {
            @Override
            public void handleResponse(final Map response) {
               // subscribeForObjectUpdate(response, testTableDataStore);

//                updateButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        updateValue(response, testTableDataStore);
//                    }
//                });

                Log.d("My_log","Object has been saved in the real-time database");
                changeSavedValue(response);

            }

            @Override
            public void handleFault(BackendlessFault fault) {
               // MainActivity.this.handleFault(fault);
            }
        });
    }

    private void changeSavedValue(Map response) {
        Log.d("My_log","response = "+ response);
    }

    private void onDialogCancel() {
        editView = null;
    }
}
