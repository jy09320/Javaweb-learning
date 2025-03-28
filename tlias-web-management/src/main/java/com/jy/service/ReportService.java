package com.jy.service;

import com.jy.pojo.ClazzOption;
import com.jy.pojo.DegreeOption;
import com.jy.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();

    List<Map> getEmpGenderData();

    List<Map> getStudentDegreeData();

    ClazzOption getStudentCountData();
}