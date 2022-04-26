/*
package com.arobs.internship.storage.hibernate;

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

        // called only at the end of program
        HibernateUtil.shutdown();
    }
}
*/
