package com.gmail.sergii_tymofieiev.backendlesstodo.data;

import android.text.TextUtils;

import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.persistence.QueryOptions;
import com.gmail.sergii_tymofieiev.backendlesstodo.App;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.Constants;
import com.gmail.sergii_tymofieiev.backendlesstodo.common.SharedPreferencesWrapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class DataItemFactory {
    private static final String KEY_ID = "objectId";
    private static final String KEY_NOTES = "notes";
    private static final String KEY_INNER_OWNER = "innerOwnerId";
    private static final String KEY_IS_DONE = "isDone";
    private static final String KEY_TIMESTAMP = "timestamp";

    public static ArrayList<IDataItem> parseJSON(String response) {
        ArrayList<IDataItem> items = new ArrayList<>();
        IDataItem tDataItem;
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray dataSource = new JSONArray("{" + response + "}");
                if (dataSource == null) {
                    throw new JSONException("absent features teg");
                }

                for (int i = 0; i < dataSource.length(); i++) {
                    if (dataSource.isNull(i)) {
                        throw new JSONException("Wrong content in artist");
                    }
                    tDataItem = new DataItem();
                    JSONObject item = dataSource.getJSONObject(i);
                    tDataItem.setDone(item.getBoolean(KEY_IS_DONE));
                    tDataItem.setNotes(item.getString(KEY_NOTES));
                    tDataItem.setTimestamp(item.getLong(KEY_TIMESTAMP));
                    tDataItem.setObjectId(item.getString(KEY_ID));
                    items.add(tDataItem);

                }
            } catch (JSONException e) {
                // TODO something
                e.printStackTrace();
            }
        }
        return items;
    }

    public static ArrayList<IDataItem> parseResponse(List<Map> response) {
        ArrayList<IDataItem> items = new ArrayList<>();
        IDataItem tDataItem;
        for (Map item : response) {
            tDataItem = new DataItem();
            tDataItem.setDone((Boolean) item.get(KEY_IS_DONE));
            tDataItem.setNotes((String) item.get(KEY_NOTES));
            tDataItem.setTimestamp(((Double) item.get(KEY_TIMESTAMP)).longValue());
            tDataItem.setObjectId((String) item.get(KEY_ID));
            items.add(tDataItem);

        }
        return items;
    }


    public enum SORT_DIRECTION {TIME_DESC, TIME_ASC}

    ;

    public static HashMap dataItemToMap(IDataItem dataItem) {
        if (dataItem == null) {
            return null;
        }
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(dataItem.getObjectId())) {
            map.put(KEY_ID, dataItem.getObjectId());
        }
        map.put(KEY_NOTES, dataItem.getNotes());
        map.put(KEY_IS_DONE, dataItem.isDone());
        map.put(KEY_TIMESTAMP, dataItem.getTimestamp());
        map.put(KEY_INNER_OWNER, SharedPreferencesWrapper.getString(App.getContext(), Constants.SP_KEY_PHONE_AS_HASH, ""));
        return map;

    }

    public static BackendlessDataQuery getBackendlessDataQuery(int filter, SORT_DIRECTION sortDirection) {
        if (sortDirection == null) {
            sortDirection = SORT_DIRECTION.TIME_DESC;
        }
        QueryOptions queryOptions = new QueryOptions();
        List<String> sortBy = new ArrayList<String>();
        if (sortDirection == SORT_DIRECTION.TIME_DESC) {
            sortBy.add(KEY_TIMESTAMP + " DESC");
        } else {
            sortBy.add(KEY_TIMESTAMP);
        }
        queryOptions.setSortBy(sortBy);

        String whereClause = KEY_INNER_OWNER + " = " + SharedPreferencesWrapper.getString(App.getContext(), Constants.SP_KEY_PHONE_AS_HASH, "");
        if (filter != 0) {
            whereClause = whereClause + " and " + KEY_IS_DONE + " = " + (filter == 2);
        }
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);
        dataQuery.setQueryOptions(queryOptions);
        return dataQuery;
    }

    public static DataQueryBuilder getDataQueryBuilder(int filter, SORT_DIRECTION sortDirection) {
        if (sortDirection == null) {
            sortDirection = SORT_DIRECTION.TIME_DESC;
        }


        String sortBy;
        if (sortDirection == SORT_DIRECTION.TIME_DESC) {
            sortBy = KEY_TIMESTAMP + " DESC";
        } else {
            sortBy = KEY_TIMESTAMP;
        }
        String whereClause = KEY_INNER_OWNER + " = '" + SharedPreferencesWrapper.getString(App.getContext(), Constants.SP_KEY_PHONE_AS_HASH, "") + "'";
        if (filter != 0) {
            whereClause = whereClause + " and " + KEY_IS_DONE + " = " + (filter == 2);
        }
        DataQueryBuilder dataQueryBuilder = DataQueryBuilder.create();
        dataQueryBuilder.setWhereClause(whereClause);
        dataQueryBuilder.addSortBy(sortBy);
        return dataQueryBuilder;
    }

}
