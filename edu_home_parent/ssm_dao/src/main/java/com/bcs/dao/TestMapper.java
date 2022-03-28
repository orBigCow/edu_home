package com.bcs.dao;

import com.bcs.domain.Test;

import java.util.List;

public interface TestMapper {

    //查询所有Test
    public List<Test> findAllTest();

}
