package com.example.fjdemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 三级平台-事件状态更新
 * @author MaXuan
 * @version 1.0.0
 * @create 2025/3/6
 */
@Data
public class DcqcEventStatusDto {

    /**
     * 事件单ID
     */
    @NotBlank(message = "eventNum cannot be null")
    private String eventNum;

    /**
     * 处置环节id
     */
    private String logId;

    /**
     * 触发时间
     */
    @NotNull(message = "updateTime cannot be null")
    private Long updateTime;

}
