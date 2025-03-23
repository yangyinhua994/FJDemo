package com.example.fjdemo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * 三级平台-事项单数据
 * @author MaXuan
 * @version 1.0.0
 * @create 2025/3/5
 */
@Data
public class TaskOrderOutput {

    /**
     * 字段英文
     */
    private String fieldCode;

    /**
     *字段中文
     */
    private String fieldName;

    /**
     *字段值
     */
    private List<String> fieldValue;

    /**
     *是否纳入事件单扩展字段
     */
    @NotNull(message = "isAddToExtends cannot be null", groups = ClosedLoopDisposalInformation.class)
    private Boolean isAddToExtends;

}
