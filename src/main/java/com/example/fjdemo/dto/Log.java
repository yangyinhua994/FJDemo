package com.example.fjdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *三级平台-事项单处置环节
 * @author MaXuan
 * @version 1.0.0
 * @create 2025/3/5
 */
@Data
public class Log {
    /**
     * 是否为最后一条日志
     */
    @NotNull(message = "isLastTaskOrderLog cannot be null")
    private Boolean isLastTaskOrderLog;

    /**
     * 执行时间
     */
    @NotNull(message = "executeTime cannot be null")
    private String executeTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    /**
     * 处置意见
     */
    @NotBlank(message = "executeContent cannot be null")
    private String executeContent;

    /**
     * 事项名称
     */
    @NotBlank(message = "executeNode cannot be null")
    private String executeNode;

    /**
     * 处置环节ID
     */
    private String eventProcessId;

    /**
     * 处置人ID
     */
    private String executorId;

    /**
     * 处置人姓名
     */
    private String executor;

    /**
     * 处置人手机号
     */
    private String executorPhone;
    /**
     * 处置部门ID
     */
    private String executeDeptId;
    /**
     * 处置部门
     */
    private String executeDept;

    /**
     * 附件信息
     */
    private List<File> listFile;

    /**
     * 附件访问地址（推荐）或base64编码（不推荐）
     */
    private String url;

    /**
     * 文件类型【01：图片，02：视频，03：音频，99：其他】
     */
    private String fileType;

    /**
     * 附件种类【0：处置前1：处置后5：处置中】
     */
    private String attachmentType;

    /**
     * 文件名称【例如xxx.jpg，必须包含文件后缀】
     */
    private String fileName;
}
