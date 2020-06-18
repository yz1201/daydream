package cn.dbdj1201.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yz1201
 * @date 2020-06-18 11:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_class")
public class ClassModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classNo; //班级编号
    private Integer classCounts; // 班级人数
}
