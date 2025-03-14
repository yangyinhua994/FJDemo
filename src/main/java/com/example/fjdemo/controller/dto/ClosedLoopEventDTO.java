package com.example.fjdemo.controller.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 三级平台-自闭环事件上报
 * @author MaXuan
 * @version 1.0.0
 * @create 2025/3/5
 */
@Data
public class ClosedLoopEventDTO implements Serializable {

    /**
     * 应用识别码
     */
    @NotBlank(message = "sourceSystemCode cannot be null", groups = {ClosedLoopEvent.class, ClosedLoopDisposalInformation.class})
    private String sourceSystemCode;

    /**
     * 事件单ID
     */
    @NotBlank(message = "eventNum cannot be null", groups = {ClosedLoopEvent.class, ClosedLoopDisposalInformation.class})
    private String eventNum;
    /**
     * 事件结案状态
     */
    @NotBlank(message = "endStatus cannot be null", groups = ClosedLoopDisposalInformation.class)
    private String endStatus;

    /**
     * 事项输出
     */
    @NotNull(message = "taskOrderOutPuts cannot be null", groups = ClosedLoopDisposalInformation.class)
    private List<TaskOrder> taskOrderOutPuts;

    /**
     * 事件编码
     */
    private String sceneCode;

    /**
     * 事件单标题
     */
    @NotBlank(message = "eventTitle cannot be null", groups = ClosedLoopEvent.class)
    private String eventTitle;

    /**
     * 事件单类型
     */
    @NotBlank(message = "eventType cannot be null", groups = ClosedLoopEvent.class)
    private String eventType;

    /**
     * 事发内容
     */
    @NotBlank(message = "eventContent cannot be null", groups = ClosedLoopEvent.class)
    private String eventContent;

    /**
     * 事发时间
     */
    @NotNull(message = "happenTime cannot be null", groups = ClosedLoopEvent.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date happenTime;

    /**
     * 事件单等级
     */
    @NotBlank(message = "eventLevel cannot be null", groups = ClosedLoopEvent.class)
    private String eventLevel;

    /**
     * 事发地址
     */
    @NotBlank(message = "eventAddress cannot be null", groups = ClosedLoopEvent.class)
    private String eventAddress;

    /**
     * 所属区县编码
     */
    @NotBlank(message = "belongCounty cannot be null", groups = ClosedLoopEvent.class)
    private String belongCounty;

    /**
     * 所属街道编码
     */
    private String belongStreet;

    /**
     * 所属社区编码
     */
    private String belongCommunity;

    /**
     * 所属网格编码
     */
    private String belongGrid;

    /**
     * 经度（84坐标）
     */
    private String lon;

    /**
     * 纬度（84坐标）
     */
    private String lat;

    /**
     * 经度（2000坐标）
     */
    private String lon2000;

    /**
     * 纬度（2000坐标）
     */
    private String lat2000;

    /**
     * 附件信息
     */
    private List<File> listFile;

    /**
     * 诉求人
     */
    private String appealName;

    /**
     * 诉求人电话
     */
    private String appealTel;

    /**
     * 诉求提出时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date appealTime;

    /**
     * 办理期限
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date limitDate;

    /**
     * 测试标识【true：测试事件，：正式事件】
     */
    private Boolean isTest;

}