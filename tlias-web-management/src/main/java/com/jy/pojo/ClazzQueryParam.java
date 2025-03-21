package com.jy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzQueryParam {
    String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate begin;
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    LocalDate end;
    Integer page =1;
    Integer pageSize = 10;
}
