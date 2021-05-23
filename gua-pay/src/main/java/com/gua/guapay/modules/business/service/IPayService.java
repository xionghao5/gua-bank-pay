package com.gua.guapay.modules.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gua.guapay.modules.business.entity.Pay;
import com.gua.guapay.modules.business.pojo.qo.PayQo;
import com.gua.guapay.modules.business.pojo.qo.TransferQo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-23
 */
public interface IPayService extends IService<Pay> {

    Pay addPay(PayQo payQo);

    IPage<Pay> pagePay(IPage<Pay> page);

    Pay selectPay(TransferQo transferQo);

    void updatePay(Pay pay);
}
