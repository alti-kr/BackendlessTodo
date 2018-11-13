package com.gmail.sergii_tymofieiev.backendlesstodo.data;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public interface IDataItem {
    String getNotes();

    boolean isDone();

    Long getTimestamp();

    void setNotes(String s);

    void setTimestamp(long l);

    void setDone(boolean checked);

    String getObjectId();

    void setObjectId(String objectId);
}
