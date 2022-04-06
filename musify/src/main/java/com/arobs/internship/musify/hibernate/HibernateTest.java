package com.arobs.internship.musify.hibernate;

import org.hibernate.Session;

public class HibernateTest {

    public static void test() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Check database version
        String sql = "select version()";

        String result = (String) session.createNativeQuery(sql).getSingleResult();
        System.out.println(result);

        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }
}
