package com.nequi.franchises_api.shared.response;

public record StandardResponse<T>(
        boolean success,
        String message,
        T data
) {

    public static <T> StandardResponse<T> success(String message, T data) {
        return new StandardResponse<>(true, message, data);
    }
}
