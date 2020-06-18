package cn.dbdj1201.demo.service;

import cn.dbdj1201.demo.entities.ClassModel;

import java.util.List;

/**
 * @author yz1201
 * @date 2020-06-18 11:33
 **/
public interface IClassModelService {
    List<ClassModel> findAll();
}
