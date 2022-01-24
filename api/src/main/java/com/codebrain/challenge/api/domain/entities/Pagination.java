package com.codebrain.challenge.api.domain.entities;

public class Pagination {

    private int page;

    public Pagination(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
