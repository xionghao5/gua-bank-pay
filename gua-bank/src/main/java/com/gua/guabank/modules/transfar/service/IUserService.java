package com.gua.guabank.modules.transfar.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gua.guabank.modules.transfar.entity.User;
import com.gua.guabank.modules.transfar.pojo.qo.UserQo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-22
 */
public interface IUserService extends IService<User> {

    void addUser(UserQo userQo);

    IPage<User> pageUser(IPage<User> page);

    User getUser(UserQo userQo);
}
