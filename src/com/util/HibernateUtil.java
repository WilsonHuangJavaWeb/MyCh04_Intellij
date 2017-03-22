package com.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by ki264 on 2017/3/22.
 * <p>
 * 基本的Hibernate輔助類別，用於Hibernate的設定和啟動。
 * 透過靜態的初始化程式碼來讀取Hibernate啟動參數，並初始化Configuration和SessionFactory對象。
 */
public class HibernateUtil {
    private static Log log = LogFactory.getLog(HibernateUtil.class);

    private static Configuration configuration;
    private static SessionFactory sessionFactory;

    static {
        try {
            configuration = new Configuration();
            configuration.configure();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            log.error("建立SessionFactory失敗", ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sf = sessionFactory;
        if (sf == null)
            throw new IllegalStateException("SessionFactory不可呼叫");

        return sf;
    }

    public static void shutdown() {
        log.debug("Shutting down Hibernate.");
        getSessionFactory().close();

        configuration = null;
        sessionFactory = null;

    }

    public static void commitAndBeginTransaction() {
        sessionFactory.getCurrentSession().getTransaction().commit();
        sessionFactory.getCurrentSession().beginTransaction();
    }
}












