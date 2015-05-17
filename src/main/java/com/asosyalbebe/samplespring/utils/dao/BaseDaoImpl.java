package com.asosyalbebe.samplespring.utils.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.util.StringUtils;

@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {

    protected transient Log log = LogFactory.getLog(getClass());

    @Autowired
    public void init(SessionFactory sessionFactory) {
	setSessionFactory(sessionFactory);
    }

    protected void saveOrUpdate(Object transientInstance) {
	getHibernateTemplate().saveOrUpdate(transientInstance);
    }

    protected Serializable save(Object transientInstance) {
	currentSession().setFlushMode(FlushMode.AUTO);
	return getHibernateTemplate().save(transientInstance);
    }

    protected void update(Object transientInstance) {
	getHibernateTemplate().update(transientInstance);
    }

    protected void delete(Object persistentInstance) {
	getHibernateTemplate().delete(persistentInstance);
    }

    protected void deleteAll(Collection<?> entities) {
	HibernateTemplate ht = getHibernateTemplate();
	ht.deleteAll(entities);
    }

    protected Object findById(Class<?> entityClass, Serializable id) {
	return getHibernateTemplate().get(entityClass, id);
    }

    protected <T> T findEntityById(Class<T> cls, Serializable id) {
	return (T) findById(cls, id);
    }

    protected <T> List<T> findEntityByProperty(Class<T> cls, String property, Object value) {
	return findEntityByProperty(cls, property, value, false);
    }

    protected <T> List<T> findEntityByProperty(Class<T> cls, String property, Object value, boolean cacheable) {
	return findEntityByProperty(cls, property, value, cacheable, null);
    }

    protected <T> List<T> findEntityByProperty(Class<T> cls, String property, Object value, boolean cacheable, String cacheRegion) {
	return findEntityByProperty(cls, property, value, cacheable, cacheRegion, null, true);
    }

    protected <T> List<T> findEntityByProperty(Class<T> cls, String property, Object value, String order) {
	return findEntityByProperty(cls, property, value, false, null, order, true);
    }

    protected <T> List<T> findEntityByProperty(Class<T> cls, String property, Object value, String order, boolean isAscending) {
	return findEntityByProperty(cls, property, value, false, null, order, isAscending);
    }

    protected <T> List<T> findEntityByProperty(Class<T> cls, String property, Object value, boolean cacheable, String cacheRegion, String order, boolean isAscending) {
	Criteria criteria = currentSession().createCriteria(cls);
	criteria.add(Restrictions.eq(property, value));
	criteria.setCacheable(cacheable);
	if (cacheable && cacheRegion != null) {
	    criteria.setCacheRegion(cacheRegion);
	}
	if (order != null) {
	    if (isAscending)
		criteria.addOrder(Order.asc(order));
	    else
		criteria.addOrder(Order.desc(order));
	}
	return criteria.list();
    }

    protected <T> T findEntityByPropertyUnique(Class<T> cls, String property, Object value) {
	return findEntityByPropertyUnique(cls, property, value, false);
    }

    protected Criteria createCriteria(Class<?> cls, String property, Object value, boolean cacheable) {
	Criteria criteria = currentSession().createCriteria(cls);
	criteria.add(Restrictions.eq(property, value));
	criteria.setCacheable(cacheable);
	return criteria;
    }

    protected Criteria createCriteria(Class<?> cls, String[] properties, Object[] values, boolean cacheable, String order, boolean isAscending) {
	Criteria criteria = currentSession().createCriteria(cls);
	int k = Math.min(properties.length, values.length);
	for (int i = 0; i < k; i++) {
	    criteria.add(Restrictions.eq(properties[i], values[i]));
	}
	if (order != null) {
	    if (isAscending)
		criteria.addOrder(Order.asc(order));
	    else
		criteria.addOrder(Order.desc(order));
	}
	criteria.setCacheable(cacheable);
	return criteria;
    }

    protected <T> T findEntityByPropertyUnique(Class<T> cls, String property, Object value, boolean cacheable) {
	Criteria criteria = createCriteria(cls, property, value, cacheable);
	return (T) criteria.uniqueResult();
    }

    protected <T> T findEntityByPropertyUnique(Class<T> cls, String[] properties, Object[] values) {
	return findEntityByPropertyUnique(cls, properties, values, false);
    }

    protected <T> T findEntityByPropertyUnique(Class<T> cls, String[] properties, Object[] values, boolean cacheable) {
	Criteria criteria = currentSession().createCriteria(cls);
	int k = Math.min(properties.length, values.length);
	for (int i = 0; i < k; i++) {
	    criteria.add(Restrictions.eq(properties[i], values[i]));
	}
	criteria.setCacheable(cacheable);
	return (T) criteria.uniqueResult();
    }

    protected <T> List<T> findEntityByProperty(Class<T> cls, String[] properties, Object[] values) {
	return findEntityByProperty(cls, properties, values, false, null, true);
    }

    protected <T> List<T> findEntityByProperty(Class<T> cls, String[] properties, Object[] values, String order) {
	return findEntityByProperty(cls, properties, values, false, order, true);
    }

    protected <T> List<T> findEntityByProperty(Class<T> cls, String[] properties, Object[] values, String order, boolean isAscending) {
	return findEntityByProperty(cls, properties, values, false, order, isAscending);
    }

    protected <T> List<T> findEntityByProperty(Class<T> cls, String[] properties, Object[] values, boolean cacheable, String order, boolean isAscending) {
	Criteria criteria = createCriteria(cls, properties, values, cacheable, order, isAscending);
	return criteria.list();
    }

    protected <T> List<T> findAllEntities(Class<T> cls) {
	return findAllEntities(cls, 0, false);
    }

    protected <T> List<T> findAllEntities(Class<T> cls, int maxResults, boolean cacheable) {
	return findAllEntities(cls, maxResults, cacheable, null, null, false);
    }

    protected <T> List<T> findAllEntities(Class<T> cls, String orderProperty, boolean isAscending) {
	return findAllEntities(cls, 0, false, null, orderProperty, isAscending);
    }

    protected <T> List<T> findAllEntities(Class<T> cls, int maxResults, boolean cacheable, String cacheRegion, String orderProperty, boolean isAscending) {
	Criteria criteria = currentSession().createCriteria(cls);
	criteria.setCacheable(cacheable);
	if (maxResults > 0)
	    criteria.setMaxResults(maxResults);
	if (cacheable && cacheRegion != null) {
	    criteria.setCacheRegion(cacheRegion);
	}
	if (orderProperty != null) {
	    if (isAscending)
		criteria.addOrder(Order.asc(orderProperty));
	    else
		criteria.addOrder(Order.desc(orderProperty));
	}
	return criteria.list();
    }

    public <T> List<T> executeNamedSqlQuery(String queryName, Class<T> clz, Object... args) {
	SQLQuery qry = (SQLQuery) currentSession().getNamedQuery(queryName);
	if (args != null) {
	    int paramPos = 0;
	    for (Object arg : args) {
		qry.setParameter(paramPos++, arg);
	    }
	}
	qry.setResultTransformer(Transformers.aliasToBean(clz));
	return qry.list();
    }

    protected <T> List<T> findEntityByPropertyOrder(Class<T> cls, String orderProperty) {
	Criteria criteria = currentSession().createCriteria(cls);
	if (StringUtils.hasText(orderProperty))
	    criteria.addOrder(Order.asc(orderProperty));
	return criteria.list();
    }

    public void flush() {
	currentSession().flush();
    }

    public long getTotalRowCount(Query query, Map<String, Object> params) {
	String hql = query.getQueryString();
	return getTotalRowCountHql(hql, params);
    }

    public long getTotalRowCountHql(String hql, Map<String, Object> params) {
	// this method should use for does not include 'groupby'
	String countSql = "select count(*) from " + org.apache.commons.lang3.StringUtils.substringAfter(hql, "from");
	Query countQuery = currentSession().createQuery(countSql);

	for (String key : params.keySet()) {
	    countQuery.setParameter(key, params.get(key));
	}

	List<Long> listCounter = (List<Long>) countQuery.list();
	return listCounter.get(0);
    }

    public long getTotalRowCountSql(String sql, Map<String, Object> params) {

	// this method should use for does not include 'groupby'
	String countSql = "select count(*) from " + org.apache.commons.lang3.StringUtils.substringAfter(sql, "from");
	Query countQuery = currentSession().createSQLQuery(countSql);

	for (String key : params.keySet()) {
	    countQuery.setParameter(key, params.get(key));
	}

	List<Long> listCounter = (List<Long>) countQuery.list();
	return Long.valueOf("" + listCounter.get(0));
    }

    public Integer getTotalRowCount(Criteria crt) {
	CriteriaImpl cImpl = (CriteriaImpl) crt;
	// Save original Projection and ResultTransformer
	Projection originalProjection = cImpl.getProjection();
	ResultTransformer originalResultTransformer = cImpl.getResultTransformer();

	crt.setProjection(Projections.projectionList().add(Projections.rowCount()));
	Long totalCount = (Long) crt.list().get(0);

	crt.setProjection(originalProjection);
	crt.setResultTransformer(originalResultTransformer);

	return totalCount != null ? totalCount.intValue() : 0;
    }
}
