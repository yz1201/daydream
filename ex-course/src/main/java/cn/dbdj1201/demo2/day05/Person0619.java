package cn.dbdj1201.demo2.day05;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yz1201
 * @date 2020-06-19 8:50
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person0619 implements Serializable {
    private String name;
    private int age;
    private String gender;
    private String username;
    private String password;
    private static final long serialVersionUID = 1L;
}
