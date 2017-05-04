package com.china.ciic.bookgenerate.service.impl;

import com.china.ciic.bookgenerate.common.datasource.DatabaseContextHolder;
import com.china.ciic.bookgenerate.common.datasource.DatabaseType;
import com.china.ciic.bookgenerate.dao.mapper.ReaderRcMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by once on 2017/5/3.
 */
@Service
public class ReaderRcServiceImpl implements ReaderRcMapper {
    @Resource
    ReaderRcMapper rc;

    @Transactional
    @Override
    public void updateMaxresourceid(Long maxresourceid, Long id) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.STUDY_MANAGE);
        rc.updateMaxresourceid(maxresourceid,id);
    }
}
