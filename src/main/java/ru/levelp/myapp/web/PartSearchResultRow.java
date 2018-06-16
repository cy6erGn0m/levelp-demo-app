package ru.levelp.myapp.web;

public class PartSearchResultRow {
    private String partId;
    private String title;

    public PartSearchResultRow(String partId, String title) {
        this.partId = partId;
        this.title = title;
    }

    public PartSearchResultRow() {
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
