package com.example.fjdemo.controller;

import com.alibaba.fastjson2.JSON;
import com.example.fjdemo.dto.*;
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
     * 儿童事件上报
     * @param closedLoopEventDTO closedLoopEventDTO
     * @return Result
     */
    @PostMapping("/childrenEventsReport")
    public Result<?> childrenEventsReport(@Validated(Event.class) @RequestBody ClosedLoopEventDTO closedLoopEventDTO) {
        return eventReportService.childrenEventsReport(closedLoopEventDTO);
    }


    /**
     * 儿童自闭环事件上报
     * @param closedLoopEventDTO closedLoopEventDTO
     * @return Result
     */
    @PostMapping("/childrenClosedLoopEventReport")
    public Result<?> childrenClosedLoopEventReport(@Validated(ClosedLoopEvent.class) @RequestBody ClosedLoopEventDTO closedLoopEventDTO) {
        return eventReportService.childrenClosedLoopEventReport(closedLoopEventDTO);
    }


    /**
     * 儿童自闭环处置信息上报
     * @param closedLoopEventDTO closedLoopEventDTO
     * @return Result
     */
    @PostMapping("/childrenClosedLoopDisposalInfoReport")
    public Result<?> childrenClosedLoopDisposalInfoReport(@Validated(ClosedLoopDisposalInformation.class) @RequestBody ClosedLoopEventDTO closedLoopEventDTO) {
        return eventReportService.childrenClosedLoopDisposalInfoReport(closedLoopEventDTO);
    }

    /**
     * 上报事件处理结果回调
     * @param dcqcEventStatusDto
     * @return
     */
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
        log.info("事件编号: {}", eventNum);
        String jsonStr = "{\n" +
                "    \"sourceSystemCode\": \"xitongbiaoshi\",\n" +
                "    \"eventNum\": \"" + eventNum + "\",\n" +
                "    \"eventTitle\": \"孤儿儿童上报\",\n" +
                "    \"eventType\": \"ac-011501\",\n" +
                "    \"eventContent\": \"孤儿儿童上报\",\n" +
                "    \"happenTime\": \"2023-05-20 15:03:59\",\n" +
                "    \"eventLevel\": \"1\",\n" +
                "    \"eventAddress\": \"重庆是奉节县白帝城街道\",\n" +
                "    \"belongCounty\": \"500101\",\n" +
                "    \"belongStreet\": \"500236001\",\n" +
                "    \"belongCommunity\": \"500101001002\",\n" +
                "    \"belongGrid\": \"500101001002003\",\n" +
                "    \"lon\": \"30.591879293725974\",\n" +
                "    \"lat\": \"104.08113365696528\",\n" +
                "    \"linkAddress\": \"\",\n" +
                "    \"dealContent\": \"请协调处理\",\n" +
                "    \"remark\": \"无备注信息\",\n" +
                "    \"listFile\": [\n" +
                "        {\n" +
                "            \"url\": \"http://xxx.jpg\",\n" +
                "            \"fileType\": \"01\",\n" +
                "            \"attachmentType\": \"0\",\n" +
                "            \"fileName\": \"xxx.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"url\": \"base64,/9j/4AAQSkZJ...\",\n" +
                "            \"fileType\": \"02\",\n" +
                "            \"attachmentType\": \"0\",\n" +
                "            \"fileName\": \"现场视频.mp4\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"appealName\": \"王梅\",\n" +
                "    \"appealTel\": \"15381190000\",\n" +
                "    \"appealTime\": \"2025-03-23 12:03:59\",\n" +
                "    \"effects\": {\n" +
                "        \"peopleNumber\": 0,\n" +
                "        \"deathNumber\": 0,\n" +
                "        \"injuredNumber\": 0,\n" +
                "        \"economicLoss\": 0,\n" +
                "        \"otherEffects\": \"\"\n" +
                "    },\n" +
                "    \"suggestDealUnit\": [\n" +
                "        {\n" +
                "            \"orgId\": \"GO_a1f118b613311g\",\n" +
                "            \"orgName\": \"奉节县公安局\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"copyToUnit\": [\n" +
                "        {\n" +
                "            \"orgId\": \"\",\n" +
                "            \"orgName\": \"\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"eventExtendedFieldList\": [],\n" +
                "    \"taskOrderOutPuts\": [\n" +
                "        {\n" +
                "            \"taskCode\": \"f83b4f0d-9856-4ec6-bb62-fe7819576fea\",\n" +
                "            \"taskName\": \"孤儿儿童上报\",\n" +
                "            \"taskDeptId\": \"GO_1694818531113616111\",\n" +
                "            \"taskOrderId\": \"863a9dda-1c6f-424a-9d47-d7dfe31kk99c\",\n" +
                "            \"taskOrderOutputArray\": [\n" +
                "                {\n" +
                "                    \"fieldCode\": \"illegalNum\",\n" +
                "                    \"fieldName\": \"重庆是奉节县白帝城\",\n" +
                "                    \"fieldValue\": [\n" +
                "                        \"1\"\n" +
                "                    ],\n" +
                "                    \"addToExtends\": false\n" +
                "                }\n" +
                "            ],\n" +
                "            \"logList\": [\n" +
                "                {\n" +
                "                    \"eventProcessId\": \"log_65cfe038-babd85fd32df\",\n" +
                "                    \"executorId\": \"2338\",\n" +
                "                    \"executor\": \"王⼩⻁\",\n" +
                "                    \"executorPhone\": \"134222929291\",\n" +
                "                    \"executeDeptId\": \"GO_6bc2a6922dc9473281ec91bee12a8ce0\",\n" +
                "                    \"executeDept\": \"重庆是奉节县白帝城\",\n" +
                "                    \"executeTime\": \"2025-03-23 12:03:59\",\n" +
                "                    \"executeContent\": \"y已核实\",\n" +
                "                    \"listFile\": [],\n" +
                "                    \"isLastTaskOrderLog\": true\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        ClosedLoopEventDTO closedLoopEventDTO = JSON.to(ClosedLoopEventDTO.class, jsonStr);
        Result<?> result = childrenEventsReport(closedLoopEventDTO);
        log.info("儿童事件上报接口结果:{}", result);
    }

}
