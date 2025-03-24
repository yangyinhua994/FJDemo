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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;


/**
 * 事件上报接口
 *
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
     *
     * @param closedLoopEventDTO closedLoopEventDTO
     * @return Result
     */
    @PostMapping("/childrenEventsReport")
    public Result<?> childrenEventsReport(@Validated(Event.class) @RequestBody ClosedLoopEventDTO closedLoopEventDTO) {
        return eventReportService.childrenEventsReport(closedLoopEventDTO);
    }


    /**
     * 儿童自闭环事件上报
     *
     * @param closedLoopEventDTO closedLoopEventDTO
     * @return Result
     */
    @PostMapping("/childrenClosedLoopEventReport")
    public Result<?> childrenClosedLoopEventReport(@Validated(ClosedLoopEvent.class) @RequestBody ClosedLoopEventDTO closedLoopEventDTO) {
        return eventReportService.childrenClosedLoopEventReport(closedLoopEventDTO);
    }


    /**
     * 儿童自闭环处置信息上报
     *
     * @param closedLoopEventDTO closedLoopEventDTO
     * @return Result
     */
    @PostMapping("/childrenClosedLoopDisposalInfoReport")
    public Result<?> childrenClosedLoopDisposalInfoReport(@Validated(ClosedLoopDisposalInformation.class) @RequestBody ClosedLoopEventDTO closedLoopEventDTO) {
        return eventReportService.childrenClosedLoopDisposalInfoReport(closedLoopEventDTO);
    }

    /**
     * 上报事件处理结果回调
     *
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
        String eventNum = "SCCHID" + timestamp14 + "000001";

        return eventNum;
    }

    @PostConstruct
    public void init() {
        childrenClosedLoopEventReport();
    }

    private void childrenEventsReport() {
        Date now = new Date();
        String eventNum = generateEventNumber();
        log.info("事件编号: {}", eventNum);
        String jsonStr = """
                {
                    "sourceSystemCode": "xitongbiaoshi",
                    "eventTitle": "孤儿儿童上报",
                    "eventType": "ac-011501",
                    "eventContent": "孤儿儿童上报",
                    "happenTime": "2023-05-20 15:03:59",
                    "eventLevel": "1",
                    "eventAddress": "重庆是奉节县白帝城街道",
                    "belongCounty": "500101",
                    "belongStreet": "500236001",
                    "belongCommunity": "500101001002",
                    "belongGrid": "500101001002003",
                    "lon": "30.591879293725974",
                    "lat": "104.08113365696528",
                    "linkAddress": "",
                    "dealContent": "请协调处理",
                    "remark": "无备注信息",
                    "listFile": [
                        {
                            "url": "http://xxx.jpg",
                            "fileType": "01",
                            "attachmentType": "0",
                            "fileName": "xxx.jpg"
                        },
                        {
                            "url": "base64,/9j/4AAQSkZJ...",
                            "fileType": "02",
                            "attachmentType": "0",
                            "fileName": "现场视频.mp4"
                        }
                    ],
                    "appealName": "王梅",
                    "appealTel": "15381190000",
                    "appealTime": "2025-03-23 12:03:59",
                    "effects": {
                        "peopleNumber": 0,
                        "deathNumber": 0,
                        "injuredNumber": 0,
                        "economicLoss": 0,
                        "otherEffects": ""
                    },
                    "suggestDealUnit": [
                        {
                            "orgId": "GO_a1f118b613311g",
                            "orgName": "奉节县公安局"
                        }
                    ],
                    "copyToUnit": [
                        {
                            "orgId": "",
                            "orgName": ""
                        }
                    ],
                    "eventExtendedFieldList": [],
                    "taskOrderOutPuts": [
                        {
                            "taskCode": "f83b4f0d-9856-4ec6-bb62-fe7819576fea",
                            "taskName": "孤儿儿童上报",
                            "taskDeptId": "GO_1694818531113616111",
                            "taskOrderId": "863a9dda-1c6f-424a-9d47-d7dfe31kk99c",
                            "taskOrderOutputArray": [
                                {
                                    "fieldCode": "illegalNum",
                                    "fieldName": "重庆是奉节县白帝城",
                                    "fieldValue": [
                                        "1"
                                    ],
                                    "addToExtends": false
                                }
                            ],
                            "logList": [
                                {
                                    "eventProcessId": "log_65cfe038-babd85fd32df",
                                    "executorId": "2338",
                                    "executor": "王⼩⻁",
                                    "executorPhone": "134222929291",
                                    "executeDeptId": "GO_6bc2a6922dc9473281ec91bee12a8ce0",
                                    "executeDept": "重庆是奉节县白帝城",
                                    "executeTime": "2025-03-23 12:03:59",
                                    "executeContent": "y已核实",
                                    "listFile": [],
                                    "isLastTaskOrderLog": true
                                }
                            ]
                        }
                    ]
                }""";
        ClosedLoopEventDTO closedLoopEventDTO = JSON.to(ClosedLoopEventDTO.class, jsonStr);
        closedLoopEventDTO.setEventNum(eventNum);
        closedLoopEventDTO.setAppealTime(now);
        Result<?> result = childrenEventsReport(closedLoopEventDTO);
        log.info("儿童事件上报接口结果:{}", result);
    }

    private static String sourceSystemCode = "A50023600042595202501009014";
    private static String eventNum = generateEventNumber();

    private void childrenClosedLoopEventReport() {
        String jsonStr = """
                {
                                    "eventTitle": "儿童自闭环测试",
                                    "eventType": "SC135708",
                                     "happenTime": "2025-03-24 10:00:02",
                                    "eventContent": "儿童自闭环测试",
                                    "eventLevel": "1",
                                    "eventAddress": "奉节县夔州街道",
                                    "belongCounty": "500236",
                                    "belongStreet": "500236004",
                                }""";

        ClosedLoopEventDTO closedLoopEventDTO = JSON.to(ClosedLoopEventDTO.class, jsonStr);
        closedLoopEventDTO.setSourceSystemCode(sourceSystemCode);
        closedLoopEventDTO.setEventNum(eventNum);
        Result<?> result = childrenClosedLoopEventReport(closedLoopEventDTO);
        log.info("儿童自闭环接口上传事件编号:{}", closedLoopEventDTO.getEventNum());
        log.info("儿童自闭环接口上传结果:{}", result);

//=========================================================

        jsonStr = """
                {
                  "endStatus": "0",
                  "taskOrderOutPuts": [
                    {
                      "taskOrderEndStatus": "1",
                      "taskOrderId": "756a82ed-3e00-4ee3-9f19-14b5b1dbe8b6",
                      "taskCode": "462e0c09-6d61-418c-be3e-a0e600b5c964",
                      "logList": [
                        {
                          "executorPhone": "15923250701",
                          "isLastTaskOrderLog": "true",
                          "executeContent": "已上报",
                          "eventProcessId": "ef9bc762-5c7a-42f6-a0d3-e6e6116d2546",
                          "executeDept": "事务科",
                          "executeNode": "上报需受理事件",
                          "executeTime": "2025-03-18 17:27:03"
                        }
                      ],
                      "taskOrderDealStatus": "1",
                      "taskName": "上报需受理事件",
                      "taskOrderOutputArray": [
                        {
                          "isAddToExtends": "false"
                        }
                      ]
                    }
                  ],
                  "eventNum": "SHGKPW202503181631518755559"
                }""";
        test(jsonStr);

        jsonStr = """
                {
                  "endStatus": "1",
                  "taskOrderOutPuts": [
                    {
                      "taskOrderEndStatus": "1",
                      "taskOrderId": "f726b1c7-ac04-4e1e-9b01-3277e25b10e0",
                      "taskCode": "f3ea3d32-5ebb-4e9e-b7f5-a52cfefa3e0a",
                      "logList": [
                        {
                          "executorPhone": "15923250701",
                          "isLastTaskOrderLog": "true",
                          "executeContent": "已查看",
                          "eventProcessId": "13cc5c63-cf74-4814-8827-4fcc33dab42b",
                          "executeDept": "事务科",
                          "executeNode": "查看所上报的事件",
                          "executeTime": "2025-03-18 17:28:11"
                        }
                      ],
                      "taskOrderDealStatus": "1",
                      "taskName": "查看所上报的事件",
                      "taskOrderOutputArray": [
                        {
                          "isAddToExtends": "false"
                        }
                      ]
                    }
                  ],
                  "eventNum": "SHGKPW202503181631518755559"
                }""";
        test(jsonStr);

        jsonStr = """
                {
                  "endStatus": "1",
                  "taskOrderOutPuts": [
                    {
                      "taskOrderEndStatus": "1",
                      "taskOrderId": "f726b1c7-ac04-4e1e-9b01-3277e25b10e1",
                      "taskCode": "dee66f56-ea15-4ba1-a441-25c7753bef39",
                      "logList": [
                        {
                          "executorPhone": "15923250701",
                          "isLastTaskOrderLog": "true",
                          "executeContent": "已受理",
                          "eventProcessId": "13cc5c63-cf74-4814-8827-4fcc33dab42b",
                          "executeDept": "事务科",
                          "executeNode": "受理上报的事件",
                          "executeTime": "2025-03-18 17:28:11"
                        }
                      ],
                      "taskOrderDealStatus": "1",
                      "taskName": "受理上报的事件",
                      "taskOrderOutputArray": [
                        {
                          "isAddToExtends": "false"
                        }
                      ]
                    }
                  ],
                  "eventNum": "SHGKPW202503181631518755559"
                }""";
        test(jsonStr);
    }

    private void test(String jsonStr) {
        ClosedLoopEventDTO closedLoopEventDTO = JSON.to(ClosedLoopEventDTO.class, jsonStr);
        closedLoopEventDTO.setEventNum(eventNum);
        closedLoopEventDTO.setSourceSystemCode(sourceSystemCode);
        for (TaskOrder taskOrderOutPut : closedLoopEventDTO.getTaskOrderOutPuts()) {
            taskOrderOutPut.setTaskOrderId(UUID.randomUUID().toString());
            for (Log log1 : taskOrderOutPut.getLogList()) {
                log1.setEventProcessId(UUID.randomUUID().toString());
            }
        }
        childrenClosedLoopDisposalInfoReport(closedLoopEventDTO);
    }

}
