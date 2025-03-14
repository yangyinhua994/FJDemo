package com.example.fjdemo.controller.dto;

import lombok.Data;

/**
 * 三级平台-附件
 * @author MaXuan
 * @version 1.0.0
 * @create 2025/3/5
 */
@Data
public class File {
    /**
     * 附件访问地址
     */
    private String url;
    /**
     * ⽂件类型【01图⽚; 02视频; 03音频; 99其他】
     */
    private String fileType;
    /**
     * 附件种类【0处置前；1处置后；5处置中】
     */
    private String attachmentType;
    /**
     * 文件名称
     */
    private String fileName;
}
