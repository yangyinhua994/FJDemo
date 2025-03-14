package com.example.fjdemo.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 三级平台-造成影响
 * @author MaXuan
 * @version 1.0.0
 * @create 2025/3/5
 */
@Data
public class Effect {
    /**
     * 涉及总人数
     */
    private Integer peopleNumber;
    /**
     * 死亡人数
     */
    private Integer deathNumber;
    /**
     * 失踪人数
     */
    private String missingNumber;
    /**
     * 受伤人数
     */
    private String injuredNumber;
    /**
     * 经济损失
     */
    private BigDecimal economicLoss;
    /**
     * 其他
     */
    private String otherEffects;
}
