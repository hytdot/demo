package com.example.demo.result;

import com.example.demo.pojo.Data;

import java.util.List;

public class ResultData {
    private int code;
    private List<Data> datas;

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public List<Data> getDatas() {
        return datas;
    }
}
