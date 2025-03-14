package com.example.fjdemo.controller.dto;

import lombok.Data;

/**
 * 三级平台-处置部门
 * @author MaXuan
 * @version 1.0.0
 * @create 2025/3/5
 */
@Data
public class DealUnit {

    /**
     * 处置部门ID
     */
    private String orgId;

    /**
     * 处置部门名称
     */
    private String orgName;
}
