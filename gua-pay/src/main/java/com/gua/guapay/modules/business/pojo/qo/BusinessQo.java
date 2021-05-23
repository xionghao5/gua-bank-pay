package com.gua.guapay.modules.business.pojo.qo;

import lombok.Data;

@Data
public class BusinessQo {
    private PayQo transfer;
    private PayQo accept;
    private String money;
}
