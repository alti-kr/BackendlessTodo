package com.gmail.sergii_tymofieiev.backendlesstodo.data;

/*
 * @author Sergii Tymofieiev on 13.11.2018
 */
public class DataItem implements IDataItem {
    private String notes;
    private boolean isDone;
    private long timestamp;
    private String extId;
    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public Long getDate() {
        return timestamp;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }
}
