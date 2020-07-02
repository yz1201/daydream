package cn.dbdj1201.springcode.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author yz1201
 * @date 2020-07-02 15:14
 **/
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"cn.dbdj1201.springcode.bean.Color","cn.dbdj1201.springcode.bean.Red","cn.dbdj1201.springcode.bean.Yellow"};
    }
}
