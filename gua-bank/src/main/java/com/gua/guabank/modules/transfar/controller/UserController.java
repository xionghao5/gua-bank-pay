package com.gua.guabank.modules.transfar.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gua.guabank.modules.transfar.entity.User;
import com.gua.guabank.modules.transfar.pojo.qo.PageQo;
import com.gua.guabank.modules.transfar.pojo.qo.UserQo;
import com.gua.guabank.modules.transfar.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author auto-genergator
 * @since 2021-05-22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserQo userQo){
        userService.addUser(userQo);
        return "注册成功";
    }

    @PostMapping("/pageUser")
    public IPage<User> pageUser(@RequestBody PageQo pageQo){
        IPage<User> page = new Page<>(pageQo.getPageNo(),pageQo.getPageSize());
        return userService.pageUser(page);
    }

    @PostMapping("/getUser")
    public User getUser(@RequestBody UserQo userQo){
        return userService.getUser(userQo);
    }

    @GetMapping("/getUserByIdcardnum")
    public User getUserByIdcardnum(String idcardnum){
        return userService.getUserByIdcardnum(idcardnum);
    }

}

