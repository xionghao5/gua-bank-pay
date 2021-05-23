package com.gua.guapay.modules.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gua.guapay.modules.business.entity.BusinessRecord;
import com.gua.guapay.modules.business.entity.Pay;
import com.gua.guapay.modules.business.mapper.BusinessRecordMapper;
import com.gua.guapay.modules.business.pojo.qo.BusinessQo;
import com.gua.guapay.modules.business.pojo.qo.PayQo;
import com.gua.guapay.modules.business.pojo.qo.TransferQo;
import com.gua.guapay.modules.business.pojo.vo.BusinessVo;
import com.gua.guapay.modules.business.pojo.vo.PayVo;
import com.gua.guapay.modules.business.service.IBusinessRecordService;
import com.gua.guapay.modules.business.service.IPayService;
import com.gua.guapay.util.MoneyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-23
 */
@Service
public class BusinessRecordServiceImpl extends ServiceImpl<BusinessRecordMapper, BusinessRecord> implements IBusinessRecordService {

    @Autowired
    private BusinessRecordMapper businessRecordMapper;

    @Autowired
    private IPayService payService;

    @Override
    public BusinessVo business(BusinessQo businessQo) {
        Pay tranferPay = getPay(businessQo.getTransfer());

        Pay acceptPay = getPay(businessQo.getAccept());

        BigDecimal transferMoney = new BigDecimal(businessQo.getMoney());
        if (MoneyUtil.notEnoughMoney(tranferPay.getMoney(), transferMoney)) {
            throw new RuntimeException("余额不足");
        }

        LocalDateTime now = LocalDateTime.now();
        BigDecimal transferNowMoney = tranferPay.getMoney().subtract(transferMoney);
        updatePay(tranferPay, transferNowMoney, now);

        BigDecimal acceptNowMoney = acceptPay.getMoney().add(transferMoney);
        updatePay(acceptPay, acceptNowMoney, now);

        saveRecord(tranferPay.getPaynum(), acceptPay.getPaynum(), transferMoney, now);

        BusinessVo businessVo = new BusinessVo();
        PayVo transferVo = new PayVo();
        PayVo acceptVo = new PayVo();
        BeanUtils.copyProperties(tranferPay, transferVo);
        BeanUtils.copyProperties(acceptPay, acceptVo);
        businessVo.setTransfer(transferVo);
        businessVo.setAccept(acceptVo);
        return businessVo;
    }

    @Override
    public IPage<BusinessRecord> pageBusinessRecord(IPage<BusinessRecord> page) {
        return businessRecordMapper.selectPage(page, null);
    }

    private Pay getPay(PayQo accept) {
        TransferQo acceptQo = new TransferQo();
        BeanUtils.copyProperties(accept, acceptQo);
        return payService.selectPay(acceptQo);
    }

    private void updatePay(Pay pay, BigDecimal money, LocalDateTime now) {
        pay.setMoney(money);
        pay.setUpdateTime(now);
        payService.updatePay(pay);
    }

    private void saveRecord(String expensePaynum, String acceptPaynum, BigDecimal money, LocalDateTime now) {
        BusinessRecord record = new BusinessRecord();
        record.setExpensePaynum(expensePaynum);
        record.setAcceptPaynum(acceptPaynum);
        record.setBusinessMoney(money);
        record.setCreateTime(now);
        record.setUpdateTime(now);
        businessRecordMapper.insert(record);
    }

}
