package basic.services;

import basic.model.ReturnCode;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;

/**
 *  全局接口
 * @param <T>
 */
@Data
@Builder
@Slf4j
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private T data;

    public static <T> ResultResponse success(T data) {
        ResultResponse response = ResultResponse.builder().code(ReturnCode.SUCCESS.getCode()).message(ReturnCode.SUCCESS.getMessage()).data(data).build();
        log.info("Success API Response: {}", response.toString());
        return response;
    }

    public static ResultResponse success() {
        ResultResponse response = ResultResponse.builder().code(0).message("ok").build();
        log.info("success API Response: {}", response.toString());
        return response;
    }

    public static <T> ResultResponse fail(int code, String message) {
        ResultResponse response = ResultResponse.builder().code(code).message(message).build();
        log.error("Failed API Response: {}", response.toString());
        return response;
    }

    public static <T> ResultResponse fail(ReturnCode returnCode) {
        return fail(returnCode.getCode(), returnCode.getMessage());
    }

    public static <T> ResultResponse fail() {
        return fail(ReturnCode.FAILED);
    }

    public static <T> ResultResponse fail(String message) {
        return fail(ReturnCode.FAILED.getCode(), message);
    }
}
