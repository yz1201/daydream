package cn.dbdj1201.springcode.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author yz1201
 * @date 2020-07-02 13:55
 **/
@Data
@NoArgsConstructor
@Table(name = "tb_user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}