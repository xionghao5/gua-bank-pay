package com.gua.guabank.modules.transfar.pojo.qo;

import lombok.Data;

@Data
public class TransferQo {
    private BankcardQo tansferBankcard;
    private BankcardQo acceptBankcard;
    private String money;
}
