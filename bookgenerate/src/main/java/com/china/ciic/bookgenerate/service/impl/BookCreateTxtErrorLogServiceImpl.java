package com.china.ciic.bookgenerate.service.impl;

import com.china.ciic.bookgenerate.common.datasource.DatabaseContextHolder;
import com.china.ciic.bookgenerate.common.datasource.DatabaseType;
import com.china.ciic.bookgenerate.dao.entity.BookCreateTxtErrorLog;
import com.china.ciic.bookgenerate.dao.mapper.BookCreateTxtErrorLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kakasun on 2017/5/3.
 */
@Service("logService")
public class BookCreateTxtErrorLogServiceImpl implements BookCreateTxtErrorLogMapper {
    @Resource
    BookCreateTxtErrorLogMapper logMapper;
    @Override
    public void faildAngin(Long bookid) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.WEB_READER);
        logMapper.faildAngin(bookid);
    }
    @Transactional
    @Override
    public void tryAngining(Long bookid) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.WEB_READER);
        logMapper.tryAngining(bookid);
    }

    @Override
    public List<BookCreateTxtErrorLog> findByStatus(Integer[] status) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.WEB_READER);
        return logMapper.findByStatus(status);
    }
    @Transactional
    @Override
    public void markSuccess(Long bookid) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.WEB_READER);
        logMapper.markSuccess(bookid);
    }
    @Transactional
    @Override
    public void ignoreErrors(Long[] ids) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.WEB_READER);
        logMapper.ignoreErrors(ids);
    }

    @Override
    public Integer countByBookid(Long bookId) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.WEB_READER);
        return logMapper.countByBookid(bookId);
    }

    @Override
    public BookCreateTxtErrorLog findById(Long id) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.WEB_READER);
        return logMapper.findById(id);
    }

    @Override
    public Long countByStatus(Integer[] status) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.WEB_READER);
        return logMapper.countByStatus(status);
    }
    @Transactional
    @Override
    public void save(BookCreateTxtErrorLog errorLog) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.WEB_READER);
        logMapper.save(errorLog);
    }

    @Override
    public List<BookCreateTxtErrorLog> getErrorListByStatus(Integer[] status) {
        DatabaseContextHolder.setDatabaseType(DatabaseType.WEB_READER);
        return logMapper.getErrorListByStatus(status);
    }
}
