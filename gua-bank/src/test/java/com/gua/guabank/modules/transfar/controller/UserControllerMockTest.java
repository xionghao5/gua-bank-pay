package com.gua.guabank.modules.transfar.controller;

import com.gua.guabank.modules.transfar.pojo.qo.UserQo;
import com.gua.guabank.modules.transfar.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerMockTest {
    @Mock
    private IUserService userService;

    /**
     * 这里要使用org.junit.Test,不能用org.junit.jupiter.api.Test
     */
    @Test
    public void addUser() {
        UserQo userQo = new UserQo();
        userQo.setUsername("老王");
        userQo.setIdcardnum("4211261999901");
        userService.addUser(userQo);
    }
}