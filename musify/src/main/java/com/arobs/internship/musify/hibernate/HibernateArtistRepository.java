package com.arobs.internship.musify.hibernate;

import com.arobs.internship.musify.model.Artist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateArtistRepository {

    public List<Artist> getAllArtists() {
        Transaction transaction = null;
        List<Artist> artists = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Artist> query = session.createNamedQuery("findAllArtists", Artist.class);
            artists = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }

        return artists;
    }

    public Artist getArtistById(int id) {
        Transaction transaction = null;
        Artist artist = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Artist> query = session.createNamedQuery("findArtistById", Artist.class);
            query.setParameter("id", id);
            artist = query.getSingleResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println(e.getMessage());
        } finally {
            HibernateUtil.shutdown();
        }

        return artist;
    }

    public void addArtist(Artist artist) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(artist);

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

    public void updateArtist(Artist artist) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            /*Artist artistToUpdate = session.get(Artist.class, artist.getId());

            if (artistToUpdate == null) {
                return;
            }*/

            session.saveOrUpdate(artist);
            //session.update(artist);

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

    public void deleteArtist(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Artist artist = session.get(Artist.class, id);
            if (artist != null) {
                session.delete(artist);
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
