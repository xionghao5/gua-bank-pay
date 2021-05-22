package com.gua.guabank.modules.transfar.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gua.guabank.modules.transfar.entity.TransferRecord;
import com.gua.guabank.modules.transfar.pojo.qo.MoneyQo;
import com.gua.guabank.modules.transfar.pojo.qo.TransferQo;
import com.gua.guabank.modules.transfar.pojo.vo.TransferVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-22
 */
public interface ITransferRecordService extends IService<TransferRecord> {

    String saveMoney(MoneyQo moneyQo);

    String withdrawMoney(MoneyQo moneyQo);

    TransferVo transfer(TransferQo transferQo);
}
