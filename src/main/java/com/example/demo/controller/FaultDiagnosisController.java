package com.example.demo.controller;

import com.example.demo.algorithm.GRA;
import com.example.demo.dao.FaultSampleDAO;
import com.example.demo.dao.FaultTypeDAO;
import com.example.demo.pojo.FaultSample;
import com.example.demo.result.ResultFaultDiagnosis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FaultDiagnosisController {
    @Autowired
    FaultSampleDAO faultSampleDAO;
    @Autowired
    FaultTypeDAO faultTypeDAO;

//    故障诊断功能，使用灰度关联分析法
    @CrossOrigin
    @GetMapping("/api/faultDiagnosis")
    @ResponseBody
    public ResultFaultDiagnosis faultDiagnosis(@RequestParam("hp") String hpStr) {
        ResultFaultDiagnosis resultFaultSample = new ResultFaultDiagnosis();
        int hp = Integer.parseInt(hpStr);
        List<FaultSample> faultSamples = new ArrayList<FaultSample>();
        faultSamples = faultSampleDAO.findByHp(hp);
        GRA gra = new GRA();
        int type = gra.faultDiagnosis(faultSamples);

        return resultFaultSample;
    }
}
