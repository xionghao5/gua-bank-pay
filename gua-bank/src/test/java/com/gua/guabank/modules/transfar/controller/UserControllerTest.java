package com.gua.guabank.modules.transfar.controller;

import com.gua.guabank.modules.transfar.entity.User;
import com.gua.guabank.modules.transfar.pojo.qo.UserQo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void addUser() {
        UserQo userQo = new UserQo();
        int randow = new Random().nextInt(1000000);
        userQo.setUsername("老王" + randow);
        userQo.setIdcardnum("421126" + randow);
        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/user/addUser", userQo, String.class);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        ResponseEntity<User> r = testRestTemplate.getForEntity("/user/getUserByIdcardnum?idcardnum={idcardnum}", User.class, userQo.getIdcardnum());
        User u = r.getBody();
        Assert.assertEquals(userQo.getUsername(), u.getUsername());
        Assert.assertEquals(userQo.getIdcardnum(), u.getIdcardnum());

    }
}