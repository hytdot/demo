package com.example.demo.controller;

import com.example.demo.algorithm.GRA;
import com.example.demo.dao.FaultSampleDAO;
import com.example.demo.dao.FaultTypeDAO;
import com.example.demo.dao.RealtimeDataDAO;
import com.example.demo.pojo.FaultSample;
import com.example.demo.pojo.FaultType;
import com.example.demo.pojo.RealtimeData;
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
    @Autowired
    RealtimeDataDAO realtimeDataDAO;

//    故障诊断功能，使用灰度关联分析法
    @CrossOrigin
    @GetMapping("/api/faultDiagnosis")
    @ResponseBody
    public ResultFaultDiagnosis faultDiagnosis(@RequestParam("hp") String hpStr) {
        ResultFaultDiagnosis resultFaultSample = new ResultFaultDiagnosis();
        int hp = Integer.parseInt(hpStr);
        List<FaultSample> faultSamples = new ArrayList<FaultSample>();
//        插入实时监测数据
        List<RealtimeData> realtimeDatas = realtimeDataDAO.findAll();
        for (int i = 0; i < realtimeDatas.size(); i++) {
            FaultSample faultSample = new FaultSample();
            faultSample.setType_id(0);
            faultSample.setDe_time(realtimeDatas.get(i).getDe_time());
            faultSample.setFe_time(realtimeDatas.get(i).getFe_time());
            faultSamples.add(faultSample);
        }
//        插入故障案例数据
        faultSamples.addAll(faultSampleDAO.findByHp(hp));
//        使用GRA进行故障诊断
        GRA gra = new GRA();
        int type = gra.faultDiagnosis(faultSamples);
        FaultType faultType = faultTypeDAO.findByType_id(type);

// TODO       输出到前端
        return resultFaultSample;
    }
}
