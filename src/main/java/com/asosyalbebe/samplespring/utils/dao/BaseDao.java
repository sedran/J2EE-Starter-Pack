package com.asosyalbebe.samplespring.utils.dao;

import java.util.List;

/**
 * @author sedrik
 * 
 */
public interface BaseDao {
    public <T> List<T> executeNamedSqlQuery(String queryName, Class<T> clz, Object... args);

    public void flush();
}
