package cn.dbdj1201.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author yz1201
 * @date 2020-07-10 15:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer empno;
    private String ename;
    private String job;
    private Integer mgr;
    private Date hiredate ;
    private Double sal;
    private Double comm;
}
