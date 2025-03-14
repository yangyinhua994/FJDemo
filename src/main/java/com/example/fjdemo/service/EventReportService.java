package com.example.fjdemo.service;

import com.example.fjdemo.controller.dto.ClosedLoopEventDTO;
import com.example.fjdemo.response.Result;

public interface EventReportService {

    Result<?> closedLoopEvent(ClosedLoopEventDTO closedLoopEventDTO);

    Result<?> closedLoopDisposalInformation(ClosedLoopEventDTO closedLoopEventDTO);
}
