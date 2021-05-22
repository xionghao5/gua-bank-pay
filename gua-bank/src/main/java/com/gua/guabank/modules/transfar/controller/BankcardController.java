package com.gua.guabank.modules.transfar.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gua.guabank.modules.transfar.entity.Bankcard;
import com.gua.guabank.modules.transfar.pojo.qo.BankcardQo;
import com.gua.guabank.modules.transfar.pojo.qo.PageQo;
import com.gua.guabank.modules.transfar.pojo.qo.UserQo;
import com.gua.guabank.modules.transfar.service.IBankcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-22
 */
@RestController
@RequestMapping("/bankcard")
public class BankcardController {


    @Autowired
    private IBankcardService bankcardService;

    @PostMapping("/addBankcard")
    public String addBankcard(@RequestBody BankcardQo bankcardQo) {
        bankcardService.addBankcard(bankcardQo);
        return "注册银行卡成功";
    }

    @PostMapping("/getBankCard")
    public List<Bankcard> getBankcard(@RequestBody UserQo userQo) {
        return bankcardService.getBankcard(userQo);
    }

    @PostMapping("/getBankCardByBankcardNumber")
    public Bankcard getBankCardByBankcardNumber(@RequestBody BankcardQo bankcardQo) {
        return bankcardService.getBankCardByBankcardNumber(bankcardQo);
    }

    @PostMapping("/pageBankcard")
    public IPage<Bankcard> pageBankcard(@RequestBody PageQo pageQo) {
        return bankcardService.pageBankcard(pageQo);
    }

}

