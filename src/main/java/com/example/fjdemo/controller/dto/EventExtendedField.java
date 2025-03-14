package com.example.fjdemo.controller.dto;

import lombok.Data;

/**
 * 三级平台-扩展字段
 * @author MaXuan
 * @version 1.0.0
 * @create 2025/3/5
 */
@Data
public class EventExtendedField {
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 字段英文
     */
    private String fieldCode;
    /**
     * 字段值
     */
    private String fieldValue;
    /**
     * 字段值描述
     */
    private String fieldValueDesc;
}
