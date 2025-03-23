package com.example.fjdemo.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson2.JSONObject;
import com.example.fjdemo.dto.ClosedLoopEventDTO;
import com.example.fjdemo.dto.DcqcEventStatusDto;
import com.example.fjdemo.response.Result;
import com.example.fjdemo.service.EventReportService;
import com.example.fjdemo.util.HttpUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Data
@Service
@Slf4j
@ConfigurationProperties(prefix = "app")
public class EventReportServiceImpl implements EventReportService {

    private String childrenAppCode;
    private String childrenAppSecret;
    private String eventsReportUrl;
    private String disposalInfoReportUrl;

    private String workshopAppCode;
    private String workshopAppSecret;
    private String workshopAppToken;

    @Override
    public Result<?> childrenEventsReport(ClosedLoopEventDTO closedLoopEventDTO) {
        return HttpUtil.post(eventsReportUrl, getClosedLoopReportHeader(childrenAppCode, childrenAppSecret), JSONObject.toJSONString(closedLoopEventDTO));
    }

    @Override
    public Result<?> childrenDisposalInfoReport(ClosedLoopEventDTO closedLoopEventDTO) {
        return HttpUtil.post(disposalInfoReportUrl, getClosedLoopReportHeader(childrenAppCode, childrenAppSecret), JSONObject.toJSONString(closedLoopEventDTO));
    }

    @Override
    public Result<String> flowChildrenUpdate(DcqcEventStatusDto dcqcEventStatusDto) {
        log.info("接收到Dcqc三级平台儿童事件状态更新数据:{}", dcqcEventStatusDto.toString());
        Result<String> result = new Result<>();
        result.setCode(200);
        result.setData("事件更新成功");
        result.setServiceSuccess(Boolean.TRUE);
        result.setRequestId("");
        log.info("返回到Dcqc事件状态更新接口结果:{}", result);
        return result;
    }


    public Map<String, String> getClosedLoopReportHeader(String closedLoopAppCode, String closedLoopAppSecret) {
        long timestamp = System.currentTimeMillis();
        String signature = DigestUtil.sha256Hex(timestamp + closedLoopAppSecret);
        Map<String, String> header = new HashMap<>();
        header.put("appCode", closedLoopAppCode);
        header.put("timestamp", String.valueOf(timestamp));
        header.put("signature", signature);
        return header;
    }

}
