package com.example.fjdemo.util;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSON;
import com.example.fjdemo.response.Result;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author YangYinHua
 * @version 1.0.0
 * @create 2025/3/14
 */
@Component
@Slf4j
@RequiredArgsConstructor
@Order(1)
public class HttpUtil {

    private static final OkHttpClient okHttpClient = getUnsafeOkHttpClient();

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
        try (Response response = okHttpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String string = response.body().string();
                log.info("请求成功,code： {}，url： {}", string, url);
                return JSON.parseObject(string, Result.class);
            } else {
                log.error("请求失败,code： {}，url： {}", response.code(), url);
            }
        } catch (IOException e) {
            message += e.getMessage();
            log.error(message);
        }
        return Result.fail(message);
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // 创建一个不验证证书链的信任管理器
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // 安装信任管理器
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);

            // 设置连接超时和读取超时
            builder.connectTimeout(5, TimeUnit.SECONDS);
            builder.readTimeout(5, TimeUnit.SECONDS);

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
