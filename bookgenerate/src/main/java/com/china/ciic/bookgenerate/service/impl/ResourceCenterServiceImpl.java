package com.china.ciic.bookgenerate.service.impl;

import com.china.ciic.bookgenerate.common.datasource.DatabaseContextHolder;
import com.china.ciic.bookgenerate.common.datasource.DatabaseType;
import com.china.ciic.bookgenerate.dao.entity.ResourceCenter;
import com.china.ciic.bookgenerate.dao.mapper.ResourceCenterMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by once on 2017/5/3.
 */
@Service
public class ResourceCenterServiceImpl implements ResourceCenterMapper {

    @Resource
    ResourceCenterMapper centerMapper;

    @Override
    public List<ResourceCenter> getBookWithOutTxt(Long readerId) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.STUDY_MANAGE);
        PageHelper.startPage(1,20);
        return centerMapper.getBookWithOutTxt(readerId);
    }

    @Override
    public ResourceCenter findById(Long bookId) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.STUDY_MANAGE);
        return centerMapper.findById(bookId);
    }

    @Override
    public int getBookTypeById(Long id) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.STUDY_MANAGE);
        return centerMapper.getBookTypeById(id);
    }
}
