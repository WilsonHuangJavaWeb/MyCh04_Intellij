package com.dao;

import com.persistence.Information;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by ki264 on 2017/3/25.
 */
public class InformationDAO extends GenericHibernateDAO<Information, String> {
    public InformationDAO() {
        super(Information.class);
    }

    public List<Information> getInfoByInfoTitle(String title) {
        Criteria criteria = getSession().createCriteria(Information.class);
        criteria.add(Restrictions.eq("title", title));
        return criteria.list();
    }
}
