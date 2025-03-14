package com.example.fjdemo.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson2.JSONObject;
import com.example.fjdemo.controller.dto.ClosedLoopEventDTO;
import com.example.fjdemo.response.Result;
import com.example.fjdemo.service.EventReportService;
import com.example.fjdemo.util.HttpUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Data
@Service
@ConfigurationProperties(prefix = "app")
public class EventReportServiceImpl implements EventReportService {

    private String closedLoopAppCode;
    private String closedLoopAppSecret;
    private String closedLoopEventsReportUrl;
    private String closedLoopDisposalInfoReportUrl;

    private String workshopAppCode;
    private String workshopAppSecret;
    private String workshopAppToken;

    private String childrenAppCode;
    private String childrenAppSecret;

    @Override
    public Result<?> closedLoopEvent(ClosedLoopEventDTO closedLoopEventDTO) {
        return HttpUtil.post(closedLoopEventsReportUrl, getClosedLoopReportHeader(), JSONObject.toJSONString(closedLoopEventDTO));
    }

    @Override
    public Result<?> closedLoopDisposalInformation(ClosedLoopEventDTO closedLoopEventDTO) {
        return HttpUtil.post(closedLoopDisposalInfoReportUrl, getClosedLoopReportHeader(), JSONObject.toJSONString(closedLoopEventDTO));
    }

    public Map<String, String> getClosedLoopReportHeader() {
        long timestamp = System.currentTimeMillis();
        String signature = DigestUtil.sha256Hex(timestamp + closedLoopAppSecret);
        Map<String, String> header = new HashMap<>();
        header.put("appCode", closedLoopAppCode);
        header.put("timestamp", String.valueOf(timestamp));
        header.put("signature", signature);
        return header;
    }

}
