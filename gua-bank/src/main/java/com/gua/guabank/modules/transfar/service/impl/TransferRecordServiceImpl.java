package com.gua.guabank.modules.transfar.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gua.guabank.common.MoneyConstant;
import com.gua.guabank.modules.transfar.entity.Bankcard;
import com.gua.guabank.modules.transfar.entity.TransferRecord;
import com.gua.guabank.modules.transfar.entity.User;
import com.gua.guabank.modules.transfar.mapper.TransferRecordMapper;
import com.gua.guabank.modules.transfar.pojo.qo.BankcardQo;
import com.gua.guabank.modules.transfar.pojo.qo.MoneyQo;
import com.gua.guabank.modules.transfar.pojo.qo.TransferQo;
import com.gua.guabank.modules.transfar.pojo.qo.UserQo;
import com.gua.guabank.modules.transfar.pojo.vo.TransferVo;
import com.gua.guabank.modules.transfar.service.IBankcardService;
import com.gua.guabank.modules.transfar.service.ITransferRecordService;
import com.gua.guabank.modules.transfar.service.IUserService;
import com.gua.guabank.util.MoneyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-22
 */
@Service
public class TransferRecordServiceImpl extends ServiceImpl<TransferRecordMapper, TransferRecord> implements ITransferRecordService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IBankcardService bankcardService;

    @Autowired
    private TransferRecordMapper transferRecordMapper;

    @Override
    public String saveMoney(MoneyQo moneyQo) {

        BankcardQo bankcardQo = new BankcardQo();
        BeanUtils.copyProperties(moneyQo, bankcardQo);
        Bankcard card = bankcardService.getBankCardByBankcardNumber(bankcardQo);

        BigDecimal money = card.getMoney().add(new BigDecimal(moneyQo.getMoney()));
        LocalDateTime now = LocalDateTime.now();
        updateBankCard(card, money, now);

        saveRecord(card.getId(), card.getId(), money, now);

        return money.toString();
    }

    @Override
    public String withdrawMoney(MoneyQo moneyQo) {
        BankcardQo bankcardQo = new BankcardQo();
        BeanUtils.copyProperties(moneyQo, bankcardQo);
        Bankcard card = bankcardService.getBankCardByBankcardNumber(bankcardQo);

        BigDecimal withdrawMoney = new BigDecimal(moneyQo.getMoney());
        if (MoneyUtil.notEnoughMoney(card.getMoney(), withdrawMoney)) {
            throw new RuntimeException("余额不足");
        }
        BigDecimal money = card.getMoney().subtract(withdrawMoney);
        LocalDateTime now = LocalDateTime.now();
        updateBankCard(card, money, now);
        saveRecord(card.getId(), card.getId(), money, now);

        return money.toString();
    }

    @Override
    public TransferVo transfer(TransferQo transferQo) {
        Bankcard transferBankcard = bankcardService.getBankCardByBankcardNumber(transferQo.getTansferBankcard());
        Bankcard acceptBankcard = bankcardService.getBankCardByBankcardNumber(transferQo.getAcceptBankcard());

        BigDecimal transferMoney = new BigDecimal(transferQo.getMoney());
        if (MoneyUtil.notEnoughMoney(transferBankcard.getMoney(), transferMoney)) {
            throw new RuntimeException("余额不足");
        }

        LocalDateTime now = LocalDateTime.now();
        BigDecimal transferNowMoney = transferBankcard.getMoney().subtract(transferMoney);
        updateBankCard(transferBankcard, transferNowMoney, now);

        BigDecimal acceptNowMoney = acceptBankcard.getMoney().add(transferMoney);
        updateBankCard(acceptBankcard, acceptNowMoney, now);

        saveRecord(transferBankcard.getId(), acceptBankcard.getId(), transferMoney, now);

        TransferVo transferVo = new TransferVo();
        transferVo.setTansferBankcard(transferBankcard);
        transferVo.setAcceptBankcard(acceptBankcard);
        return transferVo;
    }

    private void updateBankCard(Bankcard card, BigDecimal money, LocalDateTime now) {
        card.setMoney(money);
        card.setUpdateTime(now);
        bankcardService.updateById(card);
    }

    private void saveRecord(Long transferCardId, Long acceptCardId, BigDecimal money, LocalDateTime now) {
        TransferRecord record = new TransferRecord();
        record.setTransferCardId(transferCardId);
        record.setAcceptCardId(acceptCardId);
        record.setTransferMoney(money);
        record.setCreateTime(now);
        record.setUpdateTime(now);
        transferRecordMapper.insert(record);
    }
}
