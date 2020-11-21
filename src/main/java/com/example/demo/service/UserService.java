package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;

    public boolean isExist(String username) {
        User user = getByName(username);
        return null != user;
    }

    public User getByName(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username, String password){
        return userDAO.getByUsernameAndPassword(username, password);
    }

    public void addOrUpdate(User user) {
        userDAO.save(user);
//        System.out.println("查看save函数的返回值是多少");
//        System.out.println(userDAO.save(user));
    }

    public List<User> list() {
        return userDAO.findAll();
    }

    public void deleteByUsername(String id) {
        userDAO.deleteById(id);
    }
}
