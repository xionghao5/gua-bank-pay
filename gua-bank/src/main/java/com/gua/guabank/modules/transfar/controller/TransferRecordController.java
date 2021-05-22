package com.gua.guabank.modules.transfar.controller;


import com.gua.guabank.modules.transfar.pojo.qo.MoneyQo;
import com.gua.guabank.modules.transfar.pojo.qo.TransferQo;
import com.gua.guabank.modules.transfar.pojo.vo.TransferVo;
import com.gua.guabank.modules.transfar.service.ITransferRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-22
 */
@RestController
@RequestMapping("/transferRecord")
public class TransferRecordController {

    @Autowired
    private ITransferRecordService transferRecordService;

    @PostMapping("/saveMoney")
    public String saveMoney(@RequestBody MoneyQo moneyQo){
        return transferRecordService.saveMoney(moneyQo);
    }
    @PostMapping("/withdrawMoney")
    public String withdrawMoney(@RequestBody MoneyQo moneyQo){
        return transferRecordService.withdrawMoney(moneyQo);
    }

    @PostMapping("/transfer")
    public TransferVo transfer(@RequestBody TransferQo transferQo){
        return transferRecordService.transfer(transferQo);
    }

}

