package com.gua.guapay.modules.business.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PayVo {
    private String username;
    /**
     * 支付账号
     */
    private String paynum;

    private BigDecimal money;

    private LocalDateTime updateTime;
}
