package com.gua.guapay.modules.business.pojo.qo;

import lombok.Data;

@Data
public class TransferQo {
    private String username;
    private String idcardnum;
    private String bankcardNumber;

    private String money;
}
