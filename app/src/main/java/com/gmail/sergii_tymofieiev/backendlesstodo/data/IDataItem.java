package com.gmail.sergii_tymofieiev.backendlesstodo.data;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public interface IDataItem {
    String getNotes();

    boolean isDone();

    Long getDate();

    void setNotes(String s);

    void setTimestamp(long l);

    void setDone(boolean checked);
}
