package com.example.fjdemo.controller;

import com.example.fjdemo.controller.dto.ClosedLoopDisposalInformation;
import com.example.fjdemo.controller.dto.ClosedLoopEvent;
import com.example.fjdemo.controller.dto.ClosedLoopEventDTO;
import com.example.fjdemo.response.Result;
import com.example.fjdemo.service.EventReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 事件上报接口
 * @author: YangYinHua
 * @date: 2025/03/14
 **/
@RestController
@Slf4j
@RequestMapping("/eventReport")
@RequiredArgsConstructor
@Valid
public class EventReportController {

    private final EventReportService eventReportService;

    /**
     * 自闭环事件
     * @param closedLoopEventDTO closedLoopEventDTO
     * @return Result
     */
    @PostMapping("/closedLoopEvent")
    public Result<?> closedLoopEvent(@Validated(ClosedLoopEvent.class) @RequestBody ClosedLoopEventDTO closedLoopEventDTO) {
        return eventReportService.closedLoopEvent(closedLoopEventDTO);
    }


    /**
     * 自闭环处置信息
     * @param closedLoopEventDTO closedLoopEventDTO
     * @return Result
     */
    @PostMapping("/closedLoopDisposalInformation")
    public Result<?> closedLoopDisposalInformation(@Validated(ClosedLoopDisposalInformation.class) @RequestBody ClosedLoopEventDTO closedLoopEventDTO) {
        return eventReportService.closedLoopDisposalInformation(closedLoopEventDTO);
    }

    /**
     * 车间事件
     * @return Result
     */
    @PostMapping("/workshopEvent")
    public Result<?> workshopEvent() {
        return Result.success();
    }

    /**
     * 车间事件状态更新
     * @return Result
     */
    @PostMapping("/workshopEventStatus")
    public Result<?> workshopEventStatus() {
        return Result.success();
    }

    /**
     * 儿童事件
     * @return Result
     */
    @PostMapping("/childrenEvent")
    public Result<?> childrenEvent() {
        return Result.success();
    }


    /**
     * 儿童事件状态更新
     * @return Result
     */
    @PostMapping("/childrenEventStatus")
    public Result<?> childrenEventStatus() {
        return Result.success();
    }

}
