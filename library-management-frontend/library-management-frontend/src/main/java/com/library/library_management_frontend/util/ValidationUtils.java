package com.library.library_management_frontend.util;

public class ValidationUtils {
    public static boolean validateBookFields(String title, String author, String isbn) {
        return title != null && !title.trim().isEmpty() &&
                author != null && !author.trim().isEmpty() &&
                isbn != null && !isbn.trim().isEmpty();
    }
}
