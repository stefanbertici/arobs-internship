package com.arobs.internship.musify.hibernate;

import com.arobs.internship.musify.model.Band;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateBandRepository {

    public List<Band> getAllBands() {
        Transaction transaction = null;
        List<Band> bands = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Band> query = session.createNamedQuery("findAllBands", Band.class);
            bands = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }

        return bands;
    }

    public Band getBandById(int id) {
        Transaction transaction = null;
        Band band = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Band> query = session.createNamedQuery("findBandById", Band.class);
            query.setParameter("id", id);
            band = query.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }

        return band;
    }

    public void addBand(Band band) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(band);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }

    public void updateBand(Band band) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Band bandToUpdate = session.get(Band.class, band.getId());

            if (bandToUpdate == null) {
                return;
            }

            session.update(band);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }
    }

    public void deleteBand(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Band band = session.get(Band.class, id);
            if (band != null) {
                session.delete(band);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
