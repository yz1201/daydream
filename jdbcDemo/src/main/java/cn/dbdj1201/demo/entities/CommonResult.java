package cn.dbdj1201.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tyz1201
 * @datetime 2020-05-16 23:05
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {
    private Integer code;   //http状态码
    private String message; //返回信息
    private T data;         //实体数据

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}