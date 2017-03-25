package com.dao;

import com.persistence.User;

import org.hibernate.query.Query;


/**
 * Created by ki264 on 2017/3/25.
 */
public class UserDAO extends GenericHibernateDAO<User, String> {
    public UserDAO() {
        super(User.class);
    }

    public User getUserByUserName(String userName) {
        Query query = getSession().getNamedQuery("getUserByUserName");
        query.setString(0, userName);
        return (User) query.uniqueResult();
    }
}
