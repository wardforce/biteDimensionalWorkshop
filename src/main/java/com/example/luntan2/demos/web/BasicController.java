/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.luntan2.demos.web;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.luntan2.entity.User;
import com.example.luntan2.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import javafx.scene.canvas.GraphicsContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@MapperScan("com.example.luntan2.mapper")
@Controller
public class BasicController {
    @Autowired
    private UserMapper userMapper;


    // http://127.0.0.1:8080/hello?username=lisi
    @RequestMapping(value="/hello",method = RequestMethod.GET)
    @ResponseBody
    public String hello(User user) {
        System.out.println(user);
        return "Hello " + user.getUsername();
    }

    // http://127.0.0.1:8080/logup注册
    @RequestMapping(value = "/logup",method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation("注册")
    public User user(String username, String password_hash) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        
        User user = new User();
        user.setUsername(username);// 用户名
        user.setPasswordHash(password_hash);// 密码

        userMapper.insert(user); // 调用 insert 方法

        return user;
    }

    // http://127.0.0.1:8080/save_user?name=newName&age=11
    @RequestMapping("/save_user")
    @ResponseBody
    public String saveUser(User u) {

        return "";
    }

    // http://127.0.0.1:8080/html
    @RequestMapping("/html")
    public String html(){
        return "index.html";
    }

    @ModelAttribute
    public void parseUser(@RequestParam(name = "name", defaultValue = "unknown user") String name
            , @RequestParam(name = "age", defaultValue = "12") Integer age, User user) {

    }
}
