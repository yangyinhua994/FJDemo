package com.example.fjdemo.service;

import com.example.fjdemo.dto.ClosedLoopEventDTO;
import com.example.fjdemo.dto.DcqcEventStatusDto;
import com.example.fjdemo.response.Result;

public interface EventReportService {

    Result<?> childrenEventsReport(ClosedLoopEventDTO closedLoopEventDTO);

    Result<?> childrenClosedLoopEventReport(ClosedLoopEventDTO closedLoopEventDTO);

    Result<?> childrenClosedLoopDisposalInfoReport(ClosedLoopEventDTO closedLoopEventDTO);

    Result<String> flowChildrenUpdate(DcqcEventStatusDto dcqcEventStatusDto);


}
