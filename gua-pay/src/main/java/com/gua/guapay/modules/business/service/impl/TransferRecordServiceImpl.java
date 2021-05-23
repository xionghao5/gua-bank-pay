package com.gua.guapay.modules.business.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gua.guapay.modules.business.entity.Pay;
import com.gua.guapay.modules.business.entity.TransferRecord;
import com.gua.guapay.modules.business.mapper.TransferRecordMapper;
import com.gua.guapay.modules.business.pojo.qo.TransferQo;
import com.gua.guapay.modules.business.pojo.vo.PayVo;
import com.gua.guapay.modules.business.service.IPayService;
import com.gua.guapay.modules.business.service.ITransferRecordService;
import com.gua.guapay.util.MoneyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-23
 */
@Service
public class TransferRecordServiceImpl extends ServiceImpl<TransferRecordMapper, TransferRecord> implements ITransferRecordService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransferRecordMapper transferRecordMapper;

    @Autowired
    private IPayService payService;

    @Override
    public PayVo transferIn(TransferQo transferQo) {

        withdrawFromBank(transferQo);

        Pay pay = transfer(transferQo);

        saveRecord(transferQo.getBankcardNumber(),pay.getPaynum(),transferQo.getMoney());

        PayVo payVo = new PayVo();
        BeanUtils.copyProperties(pay,payVo);

        return payVo;
    }

    @Override
    public PayVo takeOut(TransferQo transferQo) {
        Pay pay = out(transferQo);

        saveToBank(transferQo);

        saveRecord(pay.getPaynum(),transferQo.getBankcardNumber(),transferQo.getMoney());

        PayVo payVo = new PayVo();
        BeanUtils.copyProperties(pay,payVo);

        return payVo;
    }

    @Override
    public IPage<TransferRecord> pageTransferRecord(IPage<TransferRecord> page) {
        return transferRecordMapper.selectPage(page, null);
    }

    private void saveRecord(String transferNum,String acceptNum,String money) {
        TransferRecord transferRecord = new TransferRecord();
        transferRecord.setTransferNum(transferNum);
        transferRecord.setAcceptNum(acceptNum);
        transferRecord.setTransferMoney(new BigDecimal(money));
        LocalDateTime now = LocalDateTime.now();
        transferRecord.setCreateTime(now);
        transferRecord.setUpdateTime(now);
        transferRecordMapper.insert(transferRecord);
    }

    private void saveToBank(TransferQo transferQo) {
        String url="http://127.0.0.1:10001/transferRecord/saveMoney";
        callForBank(transferQo, url);
    }

    private Pay out(TransferQo transferQo) {
        Pay pay = payService.selectPay(transferQo);
        BigDecimal withdrawMoney = new BigDecimal(transferQo.getMoney());
        if (MoneyUtil.notEnoughMoney(pay.getMoney(), withdrawMoney)) {
            throw new RuntimeException("余额不足");
        }
        BigDecimal nowMoney = pay.getMoney().subtract(withdrawMoney);
        pay.setMoney(nowMoney);
        pay.setUpdateTime(LocalDateTime.now());
        payService.updatePay(pay);
        return pay;
    }

    private void callForBank(TransferQo transferQo, String url) {
        HttpHeaders headers = new HttpHeaders();
        //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
        headers.setContentType(MediaType.APPLICATION_JSON);
        //RestTemplate带参传的时候要用HttpEntity<?>对象传递
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", transferQo.getUsername());
        map.put("idcardnum", transferQo.getIdcardnum());
        map.put("bankcardNumber", transferQo.getBankcardNumber());
        map.put("money", transferQo.getMoney());
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        //获取3方接口返回的数据通过entity.getBody();它返回的是一个字符串；
        String result = entity.getBody();
    }

    private Pay transfer(TransferQo transferQo) {
        Pay pay = payService.selectPay(transferQo);
        BigDecimal nowMoney = pay.getMoney().add(new BigDecimal(transferQo.getMoney()));
        pay.setMoney(nowMoney);
        pay.setUpdateTime(LocalDateTime.now());
        payService.updatePay(pay);
        return pay;
    }

    private void withdrawFromBank(TransferQo transferQo) {
        String url="http://127.0.0.1:10001/transferRecord/withdrawMoney";
        callForBank(transferQo, url);
    }
}
