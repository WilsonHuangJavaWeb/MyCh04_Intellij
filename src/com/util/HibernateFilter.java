package com.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ki264 on 2017/3/25.
 */
public class HibernateFilter implements Filter {
    private static Log log = LogFactory.getLog(HibernateFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        SessionFactory sf = HibernateUtil.getSessionFactory();
        try {
            log.debug("開始一個資料庫交易");
            sf.getCurrentSession();

            log.debug("Request Paht:\t" +
                    ((HttpServletRequest) servletRequest).getServletPath());
            filterChain.doFilter(servletRequest, servletResponse);

            log.debug("提交資料庫交易");
            sf.getCurrentSession().getTransaction().commit();

        } catch (Throwable ex) {
            ex.printStackTrace();
            try {
                log.debug("拋出例外後試圖恢復資料庫交易");
                sf.getCurrentSession().getTransaction().rollback();
            } catch (Throwable rbex) {
                log.debug("恢復交易失敗", rbex);
            }
            throw new ServletException(ex);
        }
    }

    @Override
    public void destroy() {

    }
}
