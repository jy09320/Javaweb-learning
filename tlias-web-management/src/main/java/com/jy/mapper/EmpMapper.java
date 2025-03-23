package com.jy.mapper;

import com.jy.pojo.Emp;
import com.jy.pojo.EmpQueryParam;

import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
//    //查询总记录数
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public long count();
//    //分页查询
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);
//      @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc ")
//      public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
           List<Emp> list(EmpQueryParam empQueryParam);
    @Options(useGeneratedKeys = true,keyProperty = "id")//获取生成的主键
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) " +
            "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteById(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("gender")
    List<Map> countEmpGenderData();
    @Select("select * from emp")
    List<Emp> all();

    List<Emp> getBydeptId(Integer id);
}
