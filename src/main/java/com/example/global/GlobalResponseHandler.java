package com.example.global;

import com.example.userss.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // یعنی برای همه‌ی متدها اعمال شود
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        // اگر خروجی خودش از نوع ApiResponse باشد، دوباره wrap نکن
        if (body instanceof ApiResponse) {
            return body;
        }

        // اگر رشته ساده بود (برای جلوگیری از خطای type mismatch)
        if (body instanceof String) {
            try {
                return new ObjectMapper().writeValueAsString(
                        new ApiResponse<>("success", "OK", body)
                );
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        // در حالت عادی خروجی را داخل ApiResponse بسته‌بندی کن
        return new ApiResponse<>("success", "OK", body);
    }
}
