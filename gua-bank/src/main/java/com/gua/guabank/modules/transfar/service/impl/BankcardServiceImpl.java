package com.gua.guabank.modules.transfar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gua.guabank.common.MoneyConstant;
import com.gua.guabank.modules.transfar.entity.Bankcard;
import com.gua.guabank.modules.transfar.entity.User;
import com.gua.guabank.modules.transfar.mapper.BankcardMapper;
import com.gua.guabank.modules.transfar.pojo.qo.BankcardQo;
import com.gua.guabank.modules.transfar.pojo.qo.PageQo;
import com.gua.guabank.modules.transfar.pojo.qo.UserQo;
import com.gua.guabank.modules.transfar.service.IBankcardService;
import com.gua.guabank.modules.transfar.service.IUserService;
import com.gua.guabank.util.BankcardNumberUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-22
 */
@Service
public class BankcardServiceImpl extends ServiceImpl<BankcardMapper, Bankcard> implements IBankcardService {

    @Autowired
    private BankcardMapper bankcardMapper;

    @Autowired
    private IUserService userService;

    @Override
    public void addBankcard(BankcardQo bankcardQo) {
        UserQo userQo = new UserQo();
        BeanUtils.copyProperties(bankcardQo,userQo);
        User user = userService.getUser(userQo);
        if(user == null){
            throw new RuntimeException("用户不存在");
        }
        Bankcard bankcard = new Bankcard();
        bankcard.setUid(user.getId());
        bankcard.setMoney(MoneyConstant.DEFAULT_MONEY);
        bankcard.setBankcardNumber(BankcardNumberUtil.generateBankcardNumber());
        LocalDateTime now = LocalDateTime.now();
        bankcard.setCreateTime(now);
        bankcard.setUpdateTime(now);
        bankcardMapper.insert(bankcard);
    }

    @Override
    public IPage<Bankcard> pageBankcard(PageQo pageQo) {
        IPage<Bankcard> page = new Page<>(pageQo.getPageNo(),pageQo.getPageSize());
        return bankcardMapper.selectPage(page, null);
    }

    @Override
    public Bankcard getBankCardByBankcardNumber(BankcardQo bankcardQo) {
        UserQo userQo = new UserQo();
        BeanUtils.copyProperties(bankcardQo,userQo);
        User user = userService.getUser(userQo);
        QueryWrapper<Bankcard> queryWrapper = Wrappers.query();
        queryWrapper.eq(Bankcard.UID,user.getId());
        queryWrapper.eq(Bankcard.BANKCARD_NUMBER,bankcardQo.getBankcardNumber());
        return bankcardMapper.selectOne(queryWrapper);
    }

    @Override
    public List<Bankcard> getBankcard(UserQo userQo) {
        User user = userService.getUser(userQo);
        if(user == null){
            throw new RuntimeException("用户不存在");
        }
        QueryWrapper<Bankcard> queryWrapper = Wrappers.query();
        queryWrapper.eq(Bankcard.UID,user.getId());
        return bankcardMapper.selectList(queryWrapper);
    }
}
