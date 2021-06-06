package com.gua.guabank.modules.transfar.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gua.guabank.modules.transfar.entity.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-22
 */
public interface UserMapper extends BaseMapper<User> {

    User getUserByIdcardnum(String idcardnum);
}
