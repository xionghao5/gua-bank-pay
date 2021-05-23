package com.gua.guapay.modules.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gua.guapay.common.MoneyConstant;
import com.gua.guapay.modules.business.entity.Pay;
import com.gua.guapay.modules.business.mapper.PayMapper;
import com.gua.guapay.modules.business.pojo.qo.PayQo;
import com.gua.guapay.modules.business.pojo.qo.TransferQo;
import com.gua.guapay.modules.business.service.IPayService;
import com.gua.guapay.util.PayNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-23
 */
@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements IPayService {

    @Autowired
    private PayMapper payMapper;

    @Override
    public Pay addPay(PayQo payQo) {
        Pay pay = new Pay();

        pay.setUsername(payQo.getUsername());
        pay.setIdcardnum(payQo.getIdcardnum());
        pay.setPaynum(PayNumberUtil.generateBankcardNumber());
        pay.setMoney(MoneyConstant.DEFAULT_MONEY);

        LocalDateTime now = LocalDateTime.now();
        pay.setCreateTime(now);
        pay.setUpdateTime(now);

        payMapper.insert(pay);
        return pay;
    }

    @Override
    public IPage<Pay> pagePay(IPage<Pay> page) {
        return payMapper.selectPage(page, null);
    }

    @Override
    public Pay selectPay(TransferQo transferQo) {
        QueryWrapper<Pay> queryWrapper = Wrappers.query();
        queryWrapper.eq(Pay.USERNAME,transferQo.getUsername());
        queryWrapper.eq(Pay.IDCARDNUM,transferQo.getIdcardnum());
        return payMapper.selectOne(queryWrapper);
    }

    @Override
    public void updatePay(Pay pay) {
        payMapper.updateById(pay);
    }
}
