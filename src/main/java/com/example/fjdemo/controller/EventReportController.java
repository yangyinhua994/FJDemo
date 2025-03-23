package com.example.fjdemo.controller;

import com.alibaba.fastjson2.JSON;
import com.example.fjdemo.dto.ClosedLoopDisposalInformation;
import com.example.fjdemo.dto.ClosedLoopEvent;
import com.example.fjdemo.dto.ClosedLoopEventDTO;
import com.example.fjdemo.dto.DcqcEventStatusDto;
import com.example.fjdemo.response.Result;
import com.example.fjdemo.service.EventReportService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;


/**
 * 事件上报接口
 * @author: YangYinHua
 * @date: 2025/03/14
 **/
@RestController
@Slf4j
@RequestMapping("/oot/remote/fj")
@RequiredArgsConstructor
@Valid
public class EventReportController {

    private final EventReportService eventReportService;

    /**
     * 自闭环事件
     * @param closedLoopEventDTO closedLoopEventDTO
     * @return Result
     */
    @PostMapping("/childrenEventsReport")
    public Result<?> childrenEventsReport(@Validated(ClosedLoopEvent.class) @RequestBody ClosedLoopEventDTO closedLoopEventDTO) {
        return eventReportService.childrenEventsReport(closedLoopEventDTO);
    }


    /**
     * 自闭环处置信息
     * @param closedLoopEventDTO closedLoopEventDTO
     * @return Result
     */
    @PostMapping("/childrenDisposalInfoReport")
    public Result<?> childrenDisposalInfoReport(@Validated(ClosedLoopDisposalInformation.class) @RequestBody ClosedLoopEventDTO closedLoopEventDTO) {
        return eventReportService.childrenDisposalInfoReport(closedLoopEventDTO);
    }

    @PostMapping("/flowChildrenUpdate")
    public Result<String> flowChildrenUpdate(@RequestBody DcqcEventStatusDto dcqcEventStatusDto) {
        return eventReportService.flowChildrenUpdate(dcqcEventStatusDto);
    }


    public static String generateEventNumber() {
        // 获取当前时间的毫秒数
        long currentTimeMillis = Instant.now().toEpochMilli();

        // 将毫秒数转换为字符串
        String timestampStr = Long.toString(currentTimeMillis);

        // 截取前 14 位
        String timestamp14 = timestampStr.substring(0, Math.min(14, timestampStr.length()));

        // 生成 eventNum
        String eventNum = "SHCHID" + timestamp14 + "000001";

        return eventNum;
    }

    @PostConstruct
    public void init() {
        String eventNum = generateEventNumber();
        log.info("事件编号:{}", eventNum);
        String jsonStr = "{\n" +
                "                    \"sourceSystemCode\": \"xitongbiaoshi\",\n" +
                "                    \"eventNum\": \"" + eventNum + "\",\n" +
                "                    \"eventTitle\": \"儿童孤儿认定\",\n" +
                "                    \"eventType\": \"ac-011501\",\n" +
                "                    \"eventContent\": \"儿童孤儿认定\",\n" +
                "                    \"happenTime\": \"2023-05-20 15:03:59\",\n" +
                "                    \"eventLevel\": \"1\",\n" +
                "                    \"eventAddress\": \"奉节县白帝城街道\",\n" +
                "                    \"belongCounty\": \"500101\",\n" +
                "                    \"belongStreet\": \"500236001\",\n" +
                "                    \"belongCommunity\": \"500101001002\",\n" +
                "                    \"belongGrid\": \"500101001002003\",\n" +
                "                    \"lon\": \"30.591879293725974\",\n" +
                "                    \"lat\": \"104.08113365696528\",\n" +
                "                    \"listFile\": [\n" +
                "                        {\n" +
                "                            \"url\": \"http://xxx.jpg\",\n" +
                "                            \"fileType\": \"01\",\n" +
                "                            \"attachmentType\": \"0\",\n" +
                "                            \"fileName\": \"xxx.jpg\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"url\": \"base64,/9j/4AAQSkZJ...\",\n" +
                "                            \"fileType\": \"02\",\n" +
                "                            \"attachmentType\": \"0\",\n" +
                "                            \"fileName\": \"现场视频.mp4\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"appealName\": \"王梅\",\n" +
                "                    \"appealTel\": \"15381190000\",\n" +
                "                    \"appealTime\": \"2025-03-23 10:20:00\",\n" +
                "                }";
        ClosedLoopEventDTO closedLoopEventDTO = JSON.to(ClosedLoopEventDTO.class, jsonStr);
        Result<?> result = childrenEventsReport(closedLoopEventDTO);
        log.info("返回到自闭环事件上报接口结果:{}", result);
    }

}
