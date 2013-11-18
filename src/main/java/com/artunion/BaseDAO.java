/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 27, 2013  5:53:10 PM
 */
package com.artunion;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author CloudKing
 * @param <T>
 *            BaseEntity的子类
 */
@SuppressWarnings("unchecked")
public abstract class BaseDAO<T extends BaseEntity> {
    /**
     * entityClass
     */
    Class<T> entityClass;
    /**
     * EntityManager
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * 默认构造器
     */
    public BaseDAO(){
        entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 统计数据总数
     * 
     * @return
     */
    public Integer count() {
        Object res = em.createQuery("select count(id) from " + entityClass.getSimpleName()).getSingleResult();
        return res == null ? 0 : Integer.parseInt(res.toString());
    }

    /**
     * 保存
     * 
     * @param t
     * @return
     * @throws SQLException
     *             sqlException
     */
    public void insert(final T t) throws SQLException {
        em.persist(t);
    }

    /**
     * 更新
     * 
     * @param t
     * @throws SQLException
     *             sqlexc
     */
    public void update(final T t) throws SQLException {
        em.merge(t);
    }

    /**
     * 修改根据hql names 为 null 就表示不要参数
     * 
     * @param
     * @throws SQLException
     *             sqlexc
     */
    public int updateByJPQL(final String jpql, final String[] names, final Object[] values) throws SQLException {
        Query query = em.createQuery(jpql);
        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                query.setParameter(names[i], values[i]);
            }
        }
        return query.executeUpdate();
    }

    /**
     * 修改根据hql names 为 null 就表示不要参数
     * 
     * @param
     * @throws SQLException
     *             sqlexc
     */
    public int updateByJPQL(final String jpql) throws SQLException {
        return updateByJPQL(jpql, null, null);
    }

    /**
     * 修改根据native sql
     * 
     * @param t
     * @throws SQLException
     *             sql exc
     */
    public int updateBySQL(final String sql, final String[] names, final Object[] values) throws SQLException {
        Query query = em.createNativeQuery(sql);
        for (int i = 0; i < names.length; i++) {
            query.setParameter(names[i], values[i]);
        }
        return query.executeUpdate();
    }

    /**
     * 增加根据hql names 为 null 就表示不要参数
     * 
     * @param
     * @throws SQLException
     *             sql exc
     */
    public int insertByJPQL(final String jpql, final String[] names, final Object[] values) throws SQLException {
        Query query = em.createQuery(jpql);
        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                query.setParameter(names[i], values[i]);
            }
        }
        return query.executeUpdate();
    }

    /**
     * 增加根据native sql
     * 
     * @param t
     * @throws SQLException
     *             sqlexc
     */
    public int insertBySQL(final String sql, final String[] names, final Object[] values) throws SQLException {
        Query query = em.createNativeQuery(sql);
        for (int i = 0; i < names.length; i++) {
            query.setParameter(names[i], values[i]);
        }
        return query.executeUpdate();
    }

    /**
     * 根据对象删除
     * 
     * @param t
     * @throws SQLException
     *             sqlexc
     */
    public void delete(final T t) throws SQLException {
        em.remove(t);
    }

    /**
     * 根据ID删除
     * 
     * @param id
     * @throws SQLException
     *             sqlexc
     */
    public void deleteById(final Long id) throws SQLException {
        em.remove(em.getReference(entityClass, id));
    }

    /**
     * 删除根据hql names 为 null 就表示不要参数
     * 
     * @param
     * @throws SQLException
     *             sql exc
     */
    public int deleteByJPQL(final String jpql, final String[] names, final Object[] values) throws SQLException {
        Query query = em.createQuery(jpql);
        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                query.setParameter(names[i], values[i]);
            }
        }
        return query.executeUpdate();
    }

    /**
     * 删除根据native sql
     * 
     * @param t
     * @throws SQLException
     *             sqlexc
     */
    public int deleteBySQL(final String sql, final String[] names, final Object[] values) throws SQLException {
        Query query = em.createNativeQuery(sql);
        for (int i = 0; i < names.length; i++) {
            query.setParameter(names[i], values[i]);
        }
        return query.executeUpdate();
    }

    /**
     * 删除全部
     * 
     * @param entitys
     * @throws SQLException
     *             sqlexc
     */
    public void deleteCollection(final Collection<T> entitys) throws SQLException {
        for (Object element : entitys) {
            em.remove(element);
        }
    }

    /**
     * 查找
     * 
     * @param id
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public T get(final Long id) throws SQLException {
        if (id == null) {
            return null;
        }
        return (T) em.find(entityClass, id);
    }

    /**
     * 查找 延迟加载
     * 
     * @param id
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public T load(final Long id) throws SQLException {
        if (id == null) {
            return null;
        }
        return (T) em.getReference(entityClass, id);
    }

    /**
     * 根据JPQL查询
     * 
     * @param jpql
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public T uniqueResult(final String jpql) throws SQLException {
        return (T) uniqueResultObject(jpql);
    }

    /**
     * 根据JPQL查询
     * 
     * @param jpql
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public T uniqueResult(final String jpql, final String name, final Object value) throws SQLException {
        return uniqueResult(jpql, new String[] { name }, new Object[] { value });
    }

    /**
     * 根据JPQL查询
     * 
     * @param jpql
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public T uniqueResult(final String jpql, final String[] argNames, final Object[] args) throws SQLException {
        return (T) uniqueResultObject(jpql, argNames, args);
    }

    /**
     * 根据JPQL查询
     * 
     * @param jpql
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public Object uniqueResultObject(final String jpql) throws SQLException {
        Query query = em.createQuery(jpql);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * 根据JPQL查询
     * 
     * @param jpql
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public Object uniqueResultObject(final String jpql, final String name, final Object value) throws SQLException {
        return uniqueResultObject(jpql, new String[] { name }, new Object[] { value });
    }

    /**
     * 根据JPQL查询
     * 
     * @param jpql
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public Object uniqueResultObject(final String jpql, final String[] argNames, final Object[] args)
                    throws SQLException {
        Query query = em.createQuery(jpql);
        int argNum;
        if (argNames != null && args != null) {
            argNum = argNames.length;
            if (argNum != args.length) {
                throw new SQLException("Length of paramNames array must match length of values array");
            }
            for (int i = 0; i < argNum; i++) {
                query.setParameter(argNames[i], args[i]);
            }
        }
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * 根据JPQL查询
     * 
     * @param jpql
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public T uniqueResult(final String jpql, final List<String> argNames, final List<Object> args) throws SQLException {
        return (T) uniqueResultObject(jpql, argNames, args);
    }

    /**
     * 根据JPQL查询
     * 
     * @param jpql
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public Object uniqueResultObject(final String jpql, final List<String> argNames, final List<Object> args)
                    throws SQLException {
        Query query = em.createQuery(jpql);
        int argNum;
        if (argNames != null && args != null) {
            argNum = argNames.size();
            if (argNum != args.size()) {
                throw new SQLException("Length of paramNames array must match length of values array");
            }
            for (int i = 0; i < argNum; i++) {
                query.setParameter(argNames.get(i), args.get(i));
            }
        }
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * 查询所有
     * 
     * @throws SQLException
     *             sqlexc
     */
    public List<T> list() throws SQLException {
        String jpql = "from " + entityClass.getSimpleName();
        return list(jpql, null);
    }

    /**
     * 查询所有，后面的orderbyStr，不需要跟order by ， 比如查询所有UserEntity，并且根据ID 降序，那么就是 dao.list("id desc"), 根据多个字段就用排序就用list("id desc
     * ,name desc")；
     * 
     * @throws SQLException
     *             sqlexc
     */
    public List<T> listOrderBy(final String orderbyStr) throws SQLException {
        String jpql = "from " + entityClass.getSimpleName() + " order by " + orderbyStr;
        return list(jpql, null);
    }

    /**
     * 根据JPQL查询
     * 
     * @param jpql
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public List list(final String jpql) throws SQLException {
        return list(jpql, null);
    }

    /**
     * 带一个参数的JPQL查询
     * 
     * @param jpql
     * @param value
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public List list(final String jpql, final Object value) throws SQLException {
        Object[] values = { value };
        return list(jpql, values);
    }

    /**
     * 带多个参数的JPQL查询
     * 
     * @param JPQL
     * @param values
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public List list(final String jpql, final String name, final Object value) throws SQLException {
        return list(jpql, new String[] { name }, new Object[] { value });
    }

    /**
     * 带需带参数的JPQL查询
     * 
     * @param jpql
     * @param values
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public List list(final String jpql, final Object[] values) throws SQLException {
        Query query = em.createQuery(jpql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.getResultList();
    }

    /**
     * 指定参数名的查询
     * 
     * @param jpql
     * @param argNames
     * @param args
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public List list(final String jpql, final String[] argNames, final Object[] args) throws SQLException {
        Query query = em.createQuery(jpql);
        int argNum;
        if (argNames != null && args != null) {
            argNum = argNames.length;
            if (argNum != args.length) {
                throw new SQLException("Length of paramNames array must match length of values array");
            }
            for (int i = 0; i < argNum; i++) {
                query.setParameter(argNames[i], args[i]);
            }
        }
        return query.getResultList();
    }

    /**
     * 指定参数名的查询
     * 
     * @param jpql
     * @param argNames
     * @param args
     * @return
     * @throws SQLException
     *             sqlexc
     */
    public List list(final String jpql, final List<String> argNames, final List<Object> args) throws SQLException {
        Query query = em.createQuery(jpql);
        int argNum;
        if (argNames != null && args != null) {
            argNum = argNames.size();
            if (argNum != args.size()) {
                throw new SQLException("Length of paramNames array must match length of values array");
            }
            for (int i = 0; i < argNum; i++) {
                query.setParameter(argNames.get(i), args.get(i));
            }
        }
        return query.getResultList();
    }

    /**
     * 根据SQL查询
     * 
     * @param jpql
     * @param argNames
     * @param args
     * @return
     * @throws SQLException
     *             sql exc
     */
    public List listBySQL(final String sql) throws SQLException {
        return listBySQL(sql, new String[] {}, new Object[] {});
    }

    /**
     * 根据SQL查询
     * 
     * @param sql
     * @param argNames
     * @param args
     * @return
     * @throws SQLException
     *             sql exc
     */
    public List listBySQL(final String sql, final String[] argNames, final Object[] args) throws SQLException {
        Query query = em.createNamedQuery(sql);
        int argNum;
        if (argNames != null && args != null) {
            argNum = argNames.length;
            if (argNum != args.length) {
                throw new IllegalArgumentException("Length of paramNames array must match length of values array");
            }
            for (int i = 0; i < argNum; i++) {
                query.setParameter(argNames[i], args[i]);
            }
        }
        return query.getResultList();
    }

    /**
     * 分页的JPQL查询
     * 
     * @param jpql
     * @param start
     * @param limit
     * @return
     * @throws SQLException
     *             sql exc
     */
    public List pageQuery(final Integer start, final Integer limit) throws SQLException {
        String jpql = "from " + entityClass.getSimpleName();
        return pageQuery(jpql, new Object[] {}, start, limit);

    }

    /**
     * 分页的JPQL查询
     * 
     * @param jpql
     * @param start
     * @param limit
     * @return
     * @throws SQLException
     *             sql exc
     */
    public List pageQuery(final String jpql, final Integer start, final Integer limit) throws SQLException {
        Object[] values = {};
        return pageQuery(jpql, values, start, limit);

    }

    /**
     * 带一个参数的分页JPQL查询
     * 
     * @param jpql
     * @param value
     * @param start
     * @param limit
     * @return
     * @throws SQLException
     *             sql exc
     */
    public List pageQuery(final String jpql, final Object value, final Integer start, final Integer limit)
                    throws SQLException {
        Object[] values = { value };
        return pageQuery(jpql, values, start, limit);

    }

    /**
     * 带很多参数的分页查询
     * 
     * @param jpql
     * @param values
     * @param start
     * @param limit
     * @return
     * @throws SQLException
     *             sql exc
     */
    public List pageQuery(final String jpql, final Object[] values, final Integer start, final Integer limit)
                    throws SQLException {
        Query query = em.createQuery(jpql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        query.setFirstResult(start).setMaxResults(limit);
        return query.getResultList();
    }

    /**
     * 指定参数名的分页查询
     * 
     * @param jpql
     * @param argNames
     * @param args
     * @param start
     * @param limit
     * @return
     * @throws SQLException
     *             sql exc
     */
    public List pageQuery(final String jpql, final String name, final Object value, final Integer start,
                    final Integer limit) throws SQLException {
        return pageQuery(jpql, new String[] { name }, new Object[] { value }, start, limit);

    }

    /**
     * 指定参数名的分页查询
     * 
     * @param jpql
     * @param argNames
     * @param args
     * @param start
     * @param limit
     * @return
     * @throws SQLException
     *             sql exc
     */
    public List pageQuery(final String jpql, final String[] argNames, final Object[] args, final Integer start,
                    final Integer limit) throws SQLException {
        Query query = em.createQuery(jpql);
        if (argNames != null && args != null) {
            if (argNames.length != args.length) {
                throw new SQLException("Length of paramNames array must match length of values array");
            }
            for (int i = 0; i < argNames.length; i++) {
                query.setParameter(argNames[i], args[i]);
            }
        }
        query.setFirstResult(start).setMaxResults(limit);
        return query.getResultList();

    }

    /* *//**
             * 指定参数名的分页查询
             * 
             * @param jpql
             * @param argNames
             * @param args
             * @param start
             * @param limit
             * @return
             * @throws SQLException
             *             所有SQL异常
             */
    /*
    	public List pageQuery(final String jpql, final List<String> argNames,
    			final List<Object> args, final Integer start, final Integer limit)
    			throws SQLException {
    		Query query = em.createQuery(jpql);
    		if (argNames != null && args != null) {
    			if (argNames.size() != args.size()) {
    				throw new SQLException(
    						"Length of paramNames array must match length of values array");
    			}
    			for (int i = 0; i < argNames.size(); i++) {
    				query.setParameter(argNames.get(i), args.get(i));
    			}
    		}
    		query.setFirstResult(start).setMaxResults(limit);
    		return query.getResultList();

    	}
    */
    /**
     * 指定参数名的分页查询
     * 
     * @param jpql
     * @param argNames
     * @param args
     * @param start
     * @param limit
     * @return
     * @throws SQLException
     *             sql exc
     */
    public List pageQuery(final String jpql, final List<String> argNames, final List<Object> args, PageInfo pageInfo)
                    throws SQLException {
        Query query = em.createQuery(jpql);
        if (argNames != null && args != null) {
            if (argNames.size() != args.size()) {
                throw new SQLException("Length of paramNames array must match length of values array");
            }
            for (int i = 0; i < argNames.size(); i++) {
                query.setParameter(argNames.get(i), args.get(i));
            }
        }
        String countHql = "select count(*) " + jpql.substring(jpql.lastIndexOf("from"));
        Object count = uniqueResultObject(countHql, argNames, args);
        pageInfo.setDataCount(Integer.parseInt(count.toString()));
        query.setFirstResult(pageInfo.getStart()).setMaxResults(pageInfo.getEachPageData());
        // 如果没有查到数据，并且当前页数大于1，就查找上一页数据，避免出现删除后没数据现象
        List res = query.getResultList();
        if (res.size() == 0 && pageInfo.getNowPage() > 1) {
            pageInfo.setNowPage(pageInfo.getNowPage() - 1);
            res = pageQuery(jpql, argNames, args, pageInfo.getStart(), pageInfo.getEachPageData());
        }
        return res;
    }

    /**
     * 指定参数名的分页查询
     * 
     * @param jpql
     * @param argNames
     * @param args
     * @param start
     * @param limit
     * @return
     * @throws SQLException
     *             sql exc
     */
    public List pageQuery(final String jpql, final List<String> argNames, final List<Object> args, final Integer start,
                    final Integer limit) throws SQLException {
        Query query = em.createQuery(jpql);
        if (argNames != null && args != null) {
            if (argNames.size() != args.size()) {
                throw new SQLException("Length of paramNames array must match length of values array");
            }
            for (int i = 0; i < argNames.size(); i++) {
                query.setParameter(argNames.get(i), args.get(i));
            }
        }
        query.setFirstResult(start).setMaxResults(limit);
        return query.getResultList();
    }

    /**
     * 清空EntityManager
     * 
     * @throws SQLException
     *             sql exc
     */
    public void clear() throws SQLException {
        em.clear();
    }

    /**
     * 关闭EntityManager
     * 
     * @throws SQLException
     *             sql exc
     */
    public void close() throws SQLException {
        em.close();
    }

    /**
     * 刷新EntityManager
     * 
     * @throws SQLException
     *             sql exc
     */
    public void flush() throws SQLException {
        em.flush();
    }

}
