package com.library.library_management_frontend.response;

import java.util.List;

public class PageResponse<T> {
    private List<T> content;
    private int totalPages;

    public List<T> getContent() {
        return content;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
