package com.gua.guabank.modules.transfar.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gua.guabank.modules.transfar.entity.Bankcard;
import com.gua.guabank.modules.transfar.pojo.qo.BankcardQo;
import com.gua.guabank.modules.transfar.pojo.qo.PageQo;
import com.gua.guabank.modules.transfar.pojo.qo.UserQo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-22
 */
public interface IBankcardService extends IService<Bankcard> {

    List<Bankcard> getBankcard(UserQo userQo);

    void addBankcard(BankcardQo bankcardQo);

    IPage<Bankcard> pageBankcard(PageQo pageQo);

    Bankcard getBankCardByBankcardNumber(BankcardQo bankcardQo);
}
