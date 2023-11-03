package com.dhc.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author donghongchen
 * @create 2023/11/1 17:32
 * @Description: 返回值包装结构
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData<T> {

    private boolean success;
    private int code;
    private String message;
    private T data;

    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<>(true, 0, "success", data);
    }

    public static <T> ResponseData<T> fail(int code, String message) {
        return new ResponseData<>(false, code, message, null);
    }

}
