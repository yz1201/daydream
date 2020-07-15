package cn.dbdj1201.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yz1201
 * @date 2020-07-13 10:40
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    // 总记录数
    private int totalCount;
    // 总页码
    private int totalPage;
    // 每页的数据
    private List<T> list;
    //当前页码
    private int currentPage;
    //每页显示的记录数
    private int rows;
}
