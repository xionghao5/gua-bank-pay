package com.gua.guabank.modules.transfar.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gua.guabank.modules.transfar.entity.User;
import com.gua.guabank.modules.transfar.mapper.UserMapper;
import com.gua.guabank.modules.transfar.pojo.qo.UserQo;
import com.gua.guabank.modules.transfar.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(UserQo userQo) {
        User user = new User();
        BeanUtils.copyProperties(userQo, user);
        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        userMapper.insert(user);
    }

    @Override
    public IPage<User> pageUser(IPage<User> page) {
        return userMapper.selectPage(page, null);
    }

    @Override
    public User getUser(UserQo userQo) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq(User.USERNAME,userQo.getUsername());
        queryWrapper.eq(User.IDCARDNUM,userQo.getIdcardnum());
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User getUserByIdcardnum(String idcardnum) {
        return userMapper.getUserByIdcardnum(idcardnum);
    }

}
