package com.example.demo.controller;

import com.example.demo.pojo.*;
import com.example.demo.result.ResultFault;
import com.example.demo.service.BegintimeService;
import com.example.demo.service.EnergyService;
import com.example.demo.service.ModelService;
import com.example.demo.service.MonitordataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class FaultController {
    @Autowired
    ModelService modelService;
    @Autowired
    EnergyService energyService;
    @Autowired
    BegintimeService begintimeService;
    @Autowired
    MonitordataService monitordataService;

//    实现寿命模型功能
    @CrossOrigin
    @GetMapping("/api/faultdata")
    @ResponseBody
    public ResultFault faultData(@RequestParam("type") String type) {
        System.out.println("数据获取");
        ResultFault resultFault = new ResultFault();
        List<Model> models = new ArrayList<Model>();
        models.addAll(modelService.modelList(type));
        if (models.size() != 0) {
            List<Double> times = new ArrayList<Double>();
            List<Double> rates = new ArrayList<Double>();
            for (int i = 0; i < models.size(); i++) {
                times.add(models.get(i).getTime());
                rates.add(models.get(i).getRate());
            }
            resultFault.setTimes(times);
            resultFault.setRates(rates);
            resultFault.setCode(200);
        }
        else {
            resultFault.setCode(400);
            return resultFault;
        }

        List<Energy> energys1 = new ArrayList<Energy>();
        List<Energy> energys2 = new ArrayList<Energy>();
        List<Energy> energys3= new ArrayList<Energy>();
        List<Energy> energys4 = new ArrayList<Energy>();
        energys1.addAll(energyService.energyList(type, 1));
        energys2.addAll(energyService.energyList(type, 2));
        energys3.addAll(energyService.energyList(type, 3));
        energys4.addAll(energyService.energyList(type, 4));
        if (energys1.size() != 0) {
            List<Double> d_energys1 = new ArrayList<Double>();
            List<Double> d_energys2 = new ArrayList<Double>();
            List<Double> d_energys3 = new ArrayList<Double>();
            List<Double> d_energys4= new ArrayList<Double>();
            for (int i = 0; i < energys1.size(); i++) {
                d_energys1.add(energys1.get(i).getEnergy());
            }
            for (int i = 0; i < energys2.size(); i++) {
                d_energys2.add(energys2.get(i).getEnergy());
            }
            for (int i = 0; i < energys3.size(); i++) {
                d_energys3.add(energys3.get(i).getEnergy());
            }
            for (int i = 0; i < energys4.size(); i++) {
                d_energys4.add(energys4.get(i).getEnergy());
            }
            resultFault.setEnergys1(d_energys1);
            resultFault.setEnergys2(d_energys2);
            resultFault.setEnergys3(d_energys3);
            resultFault.setEnergys4(d_energys4);
            resultFault.setCode(201);
        }
        else {
            resultFault.setCode(401);
        }
        return resultFault;
    }

    void writefile(String fileName) throws IOException {
        FileWriter fileWriter=new FileWriter(fileName);
        int [] a=new int[]{111,222,333,444,555,666};
        for (int i = 0; i < a.length; i++) {
            fileWriter.write(String.valueOf(a[i])+" ");
        }
        fileWriter.flush();
        fileWriter.close();
    }

//    实现运行状态功能
    @CrossOrigin
    @GetMapping("/api/runstate")
    @ResponseBody
    public ResultFault runState(@RequestParam("type") String type) {
        System.out.println("runstate:"+type);
        ResultFault resultFault = new ResultFault();
        Date now_date = new Date();
        List<Begintime> begintimes = new ArrayList<Begintime>();
        begintimes.addAll(begintimeService.getByType(type));
//        没有获取到时间数据，Code201
        if (begintimes.size() == 0) {
            resultFault.setCode(201);
            return resultFault;
        }
        for (int i = 0; i < begintimes.size(); i++) {
            System.out.println(begintimes.get(i).getName());
        }
        List<Runstate> runstates= new ArrayList<Runstate>();
        List<Date> dates = new ArrayList<Date>();
        for (int i = 0; i < begintimes.size(); i++) {
            Runstate tempRunstate = new Runstate();
            Date tempDate = new Date();
            tempDate.setYear(begintimes.get(i).getYear()-1900);
            tempDate.setMonth(begintimes.get(i).getMonth()-1);
            tempDate.setDate(begintimes.get(i).getDay());
            tempDate.setHours(begintimes.get(i).getHour());
            tempDate.setMinutes(begintimes.get(i).getMinute());
            tempDate.setSeconds(begintimes.get(i).getSecond());
            long mm = Math.abs(now_date.getTime() - tempDate.getTime());
            double hour = (double)Math.round((mm / 3600000.0) * 10) / 10;
            int num = (int)(mm / 0.08333) % 45000;
            tempRunstate.setName(begintimes.get(i).getName());
            tempRunstate.setTime(hour);
            if ((num + 18000) > 45000)  num -= 18000;
            tempRunstate.setNum(num);
//            tempRunstate.setNum(0);
            runstates.add(tempRunstate);
            dates.add(tempDate);
        }
        for (int i = 0; i < runstates.size(); i++) {
            System.out.println(runstates.get(i).getName());
        }

        for (int i = 0; i < runstates.size(); i++) {
            List<Monitordata> monitordatas = new ArrayList<Monitordata>();
            monitordatas.addAll(monitordataService.getDatasByName(runstates.get(i).getName()));
            List<Double> datas = new ArrayList<Double>();
            for (int j = runstates.get(i).getNum(); j < runstates.get(i).getNum() + 18000; j++) {
                datas.add(monitordatas.get(j).getData());
            }
            if (type.equals("驱动端轴承")) {
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter("D:\\PycharmProjects\\detest.txt");
                    for (int j = 0; j < datas.size(); j++) {
                        fileWriter.write(String.valueOf(datas.get(j)) + "\n");
                    }
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Process proc1;
                try {
                    proc1 = Runtime.getRuntime().exec("E:\\Python36\\python.exe D:\\PycharmProjects\\emdde.py");// 执行py文件
                    //用输入输出流来截取结果
                    BufferedReader in = new BufferedReader(new InputStreamReader(proc1.getInputStream()));
                    String line = null;
                    System.out.println("故障诊断结果");
                    List<Double> results = new ArrayList<Double>();
                    while ((line = in.readLine()) != null) {
                        results.add(Double.valueOf(line));
                        System.out.println(Double.valueOf(line));
                    }
                    if (results.get(0) == 1.0) {
                        runstates.get(i).setState("正常");
                        runstates.get(i).setResult("无故障");
                    }
                    else if (results.get(0) == 2.0) {
                        runstates.get(i).setState("故障");
                        runstates.get(i).setResult("内圈故障");
                    }
                    else if (results.get(0) == 3.0) {
                        runstates.get(i).setState("故障");
                        runstates.get(i).setResult("滚动体故障");
                    }
                    else {
                        runstates.get(i).setState("故障");
                        runstates.get(i).setResult("外圈故障");
                    }
                    results.remove(0);
                    System.out.println("删除之后的size"+results.size());
                    runstates.get(i).setEnergys(results);
                    in.close();
                    proc1.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("故障诊断结束");
//                进行故障预测
                Double a = runstates.get(i).getTime();
                System.out.println("预测时间"+a);
                try {
                    String[] args = new String[] { "E:\\Python36\\python.exe", "D:\\PycharmProjects\\svrde.py", String.valueOf(a) };
                    Process proc = Runtime.getRuntime().exec(args);// 执行py文件
                    BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    String line = null;
                    while ((line = in.readLine()) != null) {
                        Double temp = Double.valueOf(line);
                        runstates.get(i).setRate(temp);
                        System.out.println(line);
                    }
                    in.close();
                    proc.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("故障预测结束");
            }
            else {
                System.out.println("进入风扇端故障预测和诊断");
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter("D:\\PycharmProjects\\fetest.txt");
                    for (int j = 0; j < datas.size(); j++) {
                        fileWriter.write(String.valueOf(datas.get(j)) + "\n");
                    }
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Process proc1;
                try {
                    proc1 = Runtime.getRuntime().exec("E:\\Python36\\python.exe D:\\PycharmProjects\\emdfe.py");// 执行py文件
                    //用输入输出流来截取结果
                    BufferedReader in = new BufferedReader(new InputStreamReader(proc1.getInputStream()));
                    String line = null;
                    System.out.println("故障诊断结果");
                    List<Double> results = new ArrayList<Double>();
                    while ((line = in.readLine()) != null) {
                        results.add(Double.valueOf(line));
                        System.out.println(Double.valueOf(line));
                    }
                    if (results.get(0) == 1.0) {
                        runstates.get(i).setState("正常");
                        runstates.get(i).setResult("无故障");
                    }
                    else if (results.get(0) == 2.0) {
                        runstates.get(i).setState("故障");
                        runstates.get(i).setResult("内圈故障");
                    }
                    else if (results.get(0) == 3.0) {
                        runstates.get(i).setState("故障");
                        runstates.get(i).setResult("滚动体故障");
                    }
                    else {
                        runstates.get(i).setState("故障");
                        runstates.get(i).setResult("外圈故障");
                    }
                    results.remove(0);
                    System.out.println("删除之后的size"+results.size());
                    runstates.get(i).setEnergys(results);
                    in.close();
                    proc1.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("故障诊断结束");
//                进行故障预测
                Double a = runstates.get(i).getTime();
                System.out.println("预测时间"+a);
                try {
                    String[] args = new String[] { "E:\\Python36\\python.exe", "D:\\PycharmProjects\\svrfe.py", String.valueOf(a) };
                    Process proc = Runtime.getRuntime().exec(args);// 执行py文件
                    BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    String line = null;
                    while ((line = in.readLine()) != null) {
                        Double temp = Double.valueOf(line);
                        runstates.get(i).setRate(temp);
                        System.out.println(line);
                    }
                    in.close();
                    proc.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("故障预测结束");
            }
        }
        resultFault.setRunstates(runstates);
        return resultFault;
    }
}
