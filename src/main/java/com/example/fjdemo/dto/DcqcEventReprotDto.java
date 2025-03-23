package com.example.fjdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 三级平台-事件上报
 * @author YaYinHua
 * @version 1.0.0
 * @create 2025/3/14
 */
@Data
public class DcqcEventReprotDto implements Serializable {


    /**
     * 应用识别码
     */
    @NotBlank(message = "sourceSystemCode cannot be null")
    private String sourceSystemCode;

    /**
     * 事件单的唯一标识
     */
    @NotBlank(message = "eventNum cannot be null")
    private String eventNum;

    /**
     * 事件单的标题信息
     */
    @NotBlank(message = "eventTitle cannot be null")
    private String eventTitle;

    /**
     * 事件单类型
     */
    @NotBlank(message = "eventType cannot be null")
    private String eventType;

    /**
     * 事发内容
     */
    @NotBlank(message = "eventContent cannot be null")
    private String eventContent;

    /**
     * 事发时间
     */
    private String happenTime;

    /**
     * 事件单等级 0-一般 1-较大；2-重大 3-特别重大
     */
    private String eventLevel;

    /**
     * 事发地址
     */
    private String eventAddress;

    /**
     * 所属区县编码
     */
    @NotBlank(message = "belongCounty cannot be null")
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
     * 其他事发地址
     */
    private String linkAddress;

    /**
     * 办理要求
     */
    private String dealContent;

    /**
     * 事件单备注
     */
    private String remark;

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
     * 造成影响
     */
    private Effect effects;

    /**
     * 建议处置部门
     */
    private List<DealUnit> suggestDealUnit;

    /**
     * 抄送单位
     */
    private List<DealUnit> copyToUnit;

    /**
     * 扩展字段列表
     */
    private List<EventExtendedField> eventExtendedFieldList;

    /**
     * 测试标志
     */
    private Boolean isTest;

    /**
     * 事件截止日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date limitDate;

    /**
     * 历史事项单
     */
    private List<TaskOrder> taskOrderOutPuts;

    /**
     * 涉及镇街
     */
    private List<String> relationStreet;

}
