package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.result.Result;
import com.example.demo.result.ResultUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

//    用户登录功能
    @CrossOrigin
    @PostMapping(value = "/api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {
        String username = requestUser.getUsername();
        // 对 html 标签进行转义，防止 XSS 攻击
        username = HtmlUtils.htmlEscape(username);

        User user = userService.get(username, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            return new Result(200);
        }
    }

//    用户列表和查找用户功能
    @CrossOrigin
    @GetMapping("/api/users")
    @ResponseBody
    public ResultUser users(@RequestParam("query") String requestQuery,
                            @RequestParam("pagenum") String requestPagenum,
                            @RequestParam("pagesize") String requestPagesize) {
        ResultUser resultUsers = new ResultUser();
        List<User> users = new ArrayList<User>();
        if (requestQuery.isEmpty()) {
            users.addAll(userService.list());
        }
        else {
            System.out.println("query:"+requestQuery);
            User user = userService.getByName(requestQuery);
            System.out.println(user);
            users.add(user);
        }
        if (users.size() != 0) {
            resultUsers.setCode(200);
            resultUsers.setMsg("获取用户列表成功");
        }
        else {
            resultUsers.setCode(400);
            resultUsers.setMsg("获取用户列表失败");
        }
        resultUsers.setUsers(users);
        resultUsers.setTotal(users.size());
        return resultUsers;
    }

//    添加用户功能
    @CrossOrigin
    @PostMapping(value = "/api/adduser")
    @ResponseBody
    public Result addUser(@RequestBody User requestUser) {
//        System.out.println("用户名："+requestUser.getUsername());
//        System.out.println("密码："+requestUser.getPassword());
        // 用户已存在，返回code202
        if (userService.isExist(requestUser.getUsername())) {
            return new Result(202);
        }
        // 用户不存在，新建用户成功
        else {
            userService.addOrUpdate(requestUser);
            return new Result(201);
        }
    }

//    修改用户功能
    @CrossOrigin
    @PostMapping(value = "/api/edituser")
    @ResponseBody
    public Result editUser(@RequestBody User requestUser) {
        System.out.println("用户名："+requestUser.getUsername());
        System.out.println("密码："+requestUser.getPassword());
        // 用户存在，返回code200
        if (userService.isExist(requestUser.getUsername())) {
            userService.addOrUpdate(requestUser);
            return new Result(200);
        }
        // 用户不存在，修改用户失败
        else {
            return new Result(201);
        }
    }

//    删除用户功能
    @CrossOrigin
    @GetMapping("/api/deleuser")
    @ResponseBody
    public Result deleUser(@RequestParam("username") String username) {
        System.out.println("用户名："+username);
        // 用户存在，返回code200
        if (userService.isExist(username)) {
            userService.deleteByUsername(username);
            return new Result(200);
        }
        // 用户不存在，删除用户失败
        else {
            return new Result(201);
        }
    }
}
