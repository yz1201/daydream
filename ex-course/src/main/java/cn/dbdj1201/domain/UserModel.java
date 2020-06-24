package cn.dbdj1201.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yz1201
 * @date 2020-06-24 11:31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel implements Serializable {

    private Long userId;
    private String username;
    private String password;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
