package com.example.demo.algorithm;

import com.example.demo.pojo.FaultSample;

import java.util.ArrayList;
import java.util.List;

public class GRA {
    List<FaultSample> faultSamples = new ArrayList<FaultSample>();
    int m = 100;    //一个故障有多少个采集点
    int n;    //故障情况种类
    int[] typeResult = new int[n+1];
    double[][] DE = new double[m+1][n+1];   //驱动端数据
    double[][] FE = new double[m+1][n+1];   //风扇端数据
    double[][] dist = new double[m+1][n+1];      //参照和各个情况的差值
    double minDist = 100000;
    double maxDist = 0;
    double resolutionCoefficient = 0.5;    //分辨系数ρ
    double[][] correlationCoefficient = new double[m+1][n+1];   //关联系数ξ
    double[] correlationDegree = new double[n+1];   //关联度
    double maxResult = 0;
    int faultIndex = 0;

//    初始化
    public void init() {
        n = faultSamples.size() / m;
        int t = 0;
        for (int i = 0; i < n+1; i++) {
            for (int k = 1; k < m+1; k++) {
                FaultSample faultSample = faultSamples.get(t);
                DE[k][i] = faultSample.getDe_time();
                FE[k][i] = faultSample.getFe_time();
                typeResult[i] = faultSample.getType_id();
                t++;
            }
        }
        return;
    }

//    无量纲化
    public void nondimensionalize() {
        double[] averageDE = new double[n+1];
        double[] averageFE = new double[n+1];
        for (int i = 0; i < n+1; i++) {
            double sumDE = 0;
            double sumFE = 0;
            for (int k = 1; k < m+1; k++) {
                sumDE += DE[k][i];
                sumFE += FE[k][i];
            }
            averageDE[i] = sumDE / m;
            averageFE[i] = sumFE / m;
        }
        for (int k = 1; k < m+1; k++) {
            for (int i = 0; i < n+1; i++) {
                DE[k][i] = DE[k][i] / averageDE[i];
                FE[k][i] = FE[k][i] / averageFE[i];
            }
        }
        return;
    }

//    求参照差值dist以及dist的最小值和最大值
    public void calculateDist() {
        for (int k = 1; k < m+1; k++) {
            for (int i = 1; i < n+1; i++) {
                double temp = (DE[k][0]-DE[k][i])*(DE[k][0]-DE[k][i]) + (FE[k][0]-FE[k][i])*(FE[k][0]-FE[k][i]);
                dist[k][i] = Math.sqrt(temp);
                if (dist[k][i] < minDist)   minDist = dist[k][i];
                if (dist[k][i] > maxDist)   maxDist = dist[k][i];
            }
        }
        return;
    }

//    灰色关联度分析
    public void greyRelation() {
        for (int k = 1; k < m+1; k++) {
            for (int i = 1; i < n+1; i++) {
                double temp = minDist + resolutionCoefficient * maxDist;
                correlationCoefficient[k][i] = temp / (dist[k][i] + resolutionCoefficient * maxDist);
            }
        }
        for (int i = 1; i < n+1; i++) {
            double sum = 0;
            for (int k = 1; k < m+1; k++) {
                sum += correlationCoefficient[k][i];
            }
            correlationDegree[i] = sum / m;
            if (correlationDegree[i] > maxResult) {
                maxResult = correlationDegree[i];
                faultIndex = i;
            }
        }
        return;
    }

//    故障诊断
    public int faultDiagnosis(List<FaultSample> faultSamples) {
        this.faultSamples = faultSamples;
        init();
        nondimensionalize();
        calculateDist();
        greyRelation();
        System.out.println("故障类型：" + typeResult[faultIndex]);
        return typeResult[faultIndex];
    }
}
