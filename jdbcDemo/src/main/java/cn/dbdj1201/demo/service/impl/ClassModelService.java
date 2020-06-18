package cn.dbdj1201.demo.service.impl;

import cn.dbdj1201.demo.entities.ClassModel;
import cn.dbdj1201.demo.mapper.ClassModelMapper;
import cn.dbdj1201.demo.service.IClassModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yz1201
 * @date 2020-06-18 11:35
 **/
@Service
public class ClassModelService implements IClassModelService {

    @Autowired
    private ClassModelMapper classModelMapper;

    @Override
    public List<ClassModel> findAll() {
        return classModelMapper.selectAll();
    }
}
