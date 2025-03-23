package com.jy.controller;

import com.jy.pojo.ClazzOption;
import com.jy.pojo.DegreeOption;
import com.jy.pojo.JobOption;
import com.jy.pojo.Result;
import com.jy.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计各个职位的员工人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各个职位的员工人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计各个性别的员工人数");
        List<Map> genderlist = reportService.getEmpGenderData();
        return Result.success(genderlist);
    }
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计各个学历的学生人数");
        List<Map> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }
    @GetMapping("/studentCountData")
    public Result getStudentCountData(){
      log.info("统计各个班级的学生人数");
      ClazzOption clazzOption = reportService.getStudentCountData();
      return Result.success(clazzOption);
    }
}