package com.gua.guapay.modules.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gua.guapay.modules.business.entity.Pay;
import com.gua.guapay.modules.business.pojo.qo.PageQo;
import com.gua.guapay.modules.business.pojo.qo.PayQo;
import com.gua.guapay.modules.business.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private IPayService payService;

    @PostMapping("/addPay")
    public Pay addPay(@RequestBody PayQo payQo){
        return payService.addPay(payQo);
    }

    @PostMapping("/pagePay")
    public IPage<Pay> pagePay(@RequestBody PageQo pageQo){
        IPage<Pay> page = new Page<>(pageQo.getPageNo(),pageQo.getPageSize());
        return payService.pagePay(page);
    }


}

