package ru.levelp.myapp.web;

import java.util.List;

public class PartSearchResult {
    private List<PartSearchResultRow> rows;

    public PartSearchResult() {
    }

    public PartSearchResult(List<PartSearchResultRow> rows) {
        this.rows = rows;
    }

    public List<PartSearchResultRow> getRows() {
        return rows;
    }

    public void setRows(List<PartSearchResultRow> rows) {
        this.rows = rows;
    }
}
