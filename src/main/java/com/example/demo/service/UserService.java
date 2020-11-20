package com.example.demo.service;

import com.example.demo.dao.UserDAO;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    }

    public List<User> list() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return userDAO.findAll(sort);
    }

    public void deleteById(int id) {
        userDAO.deleteById(id);
    }
}
