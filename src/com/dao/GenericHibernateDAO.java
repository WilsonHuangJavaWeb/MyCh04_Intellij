package com.dao;

import org.hibernate.Session;

import java.io.Serializable;

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
        return null;
    }
}
