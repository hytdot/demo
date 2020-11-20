package com.example.demo.result;

import com.example.demo.pojo.User;

import java.util.List;

public class ResultUser {
    //响应码
    private int code;
    //提示信息
    private String msg;
    //用户信息
    private List<User> users;
    //用户个数
    private int total;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
