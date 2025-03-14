package com.example.fjdemo.controller.dto;

import lombok.Data;

import java.util.List;

/**
 * 三级平台-历史事项单数据
 * @author MaXuan
 * @version 1.0.0
 * @create 2025/3/5
 */
@Data
public class TaskOrder {
    /**
     * 事项ID
     */
    private String taskCode;

    /**
     * 事项名称
     */
    private String taskName;

    /**
     * 复杂表单对象
     */
    private String cloudpivot;

    /**
     * 事项单ID
     */
    private String taskOrderId;

    /**
     * 事项所属组织ID
     */
    private String taskDeptId;

    /**
     * 事项单处置状态, 0-失败 1-成功
     */
    private String taskOrderDealStatus;

    /**
     * 事项单完结状态, 0-失败 1-成功
     */
    private String taskOrderEndStatus;

    /**
     * 事件单处置状态描述
     */
    private String taskOrderDealDesc;

    /**
     * 事项单数据输出
     */
    private List<TaskOrderOutput> taskOrderOutputArray;

    /**
     * 事件单处置环节
     */
    private List<Log> logList;

}
