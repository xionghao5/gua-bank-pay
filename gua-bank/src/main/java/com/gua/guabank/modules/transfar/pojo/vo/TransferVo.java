package com.gua.guabank.modules.transfar.pojo.vo;

import com.gua.guabank.modules.transfar.entity.Bankcard;
import lombok.Data;

@Data
public class TransferVo {
    private Bankcard tansferBankcard;
    private Bankcard acceptBankcard;
}
