package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.result.ResultUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

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
            System.out.println("listnum="+users.size());
        }
        else {
            User user = userService.getByName(requestQuery);
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
}
