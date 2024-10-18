package com.csse.mycare.common;

import com.csse.mycare.common.constants.ErrorCodes;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public record BaseResponse<T>(
        T data,
        Boolean success,
        String errorCode,
        String message
) {
    public BaseResponse(T data) {
        this(data, true, null, null);
    }

    public BaseResponse(Boolean success) {
        this(null, success, null, null);
    }

    public BaseResponse(Boolean success, String errorCode) {
        this(null, success, errorCode, null);
    }

    public BaseResponse(T data, Boolean success) {
        this(data, success, null, null);
    }

    public BaseResponse(T data, Boolean success, String errorCode) {
        this(data, success, errorCode, null);
    }

    public BaseResponse(ErrorCodes errorCode) {
        this(null, false, errorCode.getCode(), errorCode.getMessage());
    }

    public BaseResponse(T data, Boolean success, String errorCode, String message) {
        this.data = data;
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
    }
}
