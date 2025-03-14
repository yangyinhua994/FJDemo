package com.example.fjdemo.util;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.example.fjdemo.response.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author YangYinHua
 * @version 1.0.0
 * @create 2025/3/14
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class HttpUtil {

    private final OkHttpClient okHttpClient;
    private static OkHttpClient client;

    @PostConstruct
    public void init() {
        client = okHttpClient;
    }

    private static final MediaType MEDIA_TYPE_JSON = MediaType.get("application/json; charset=utf-8");

    public static Result<?> post(@NotNull(message = "请求地址不能为空") String url, Map<String, String> headers, String jsonBody) {
        Request.Builder requestBuilder;
        if (ObjectUtil.isNotEmpty(jsonBody)) {
            try {
                RequestBody requestBody = RequestBody.create(jsonBody, MEDIA_TYPE_JSON);
                requestBuilder = new Request.Builder()
                        .url(url)
                        .post(requestBody);
            } catch (Exception e) {
                String message = e.getMessage();
                log.error("解析body参数失败：{}", message);
                return null;
            }

        } else {
            requestBuilder = new Request.Builder()
                    .url(url);
        }


        // 设置请求头
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }

        Request request = requestBuilder.build();
        String message = "调用远端服务失败：";

        // 发送请求并获取响应
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return JSON.parseObject(response.body().string(), Result.class);
            } else {
                log.error("请求失败,code： {}，url： {}", response.code(), url);
            }
        } catch (IOException e) {
            message += e.getMessage();
            log.error(message);
        }
        return Result.fail(message);
    }
}
