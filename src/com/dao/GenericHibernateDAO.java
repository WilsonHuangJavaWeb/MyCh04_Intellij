package com.dao;

import com.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;


/**
 * Created by ki264 on 2017/3/22.
 */
public class GenericHibernateDAO<T, ID extends Serializable> {
    private Class<T> persistentClass;

    public GenericHibernateDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    protected Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public List<T> getByHsql(String hsql) {
        Query query = getSession().createQuery(hsql);
        return query.list();
    }

    protected List<T> getByCriteria(Criterion... criteria) {
        Criteria crit = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criteria) {
            crit.add(c);
        }
        return crit.list();
    }

    public T getById(ID id) {
        T entity = (T) getSession().get(getPersistentClass(), id);
        return entity;
    }

    public List<T> getAll() {
        return getByCriteria();
    }

    public List<T> getByExample(T exampleInstance) {
        return getByCriteria(Example.create(exampleInstance));
    }

    public T makePersistent(T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    public void makeTransient(T entity) {
        getSession().delete(entity);
    }

}









































