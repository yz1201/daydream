package cn.dbdj1201.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yz1201
 * @date 2020-07-31 18:16
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //用户id
    private Integer id;
    //用户名
    private String username;
    //用户密码
    private String password;
}
