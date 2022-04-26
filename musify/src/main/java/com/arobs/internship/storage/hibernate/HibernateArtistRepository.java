/*
package com.arobs.internship.storage.hibernate;

import com.arobs.internship.musify.dto.ArtistDTO;
import com.arobs.internship.musify.mapper.ArtistMapperImpl;
import com.arobs.internship.musify.model.Artist;
import com.arobs.internship.musify.model.Band;
import com.arobs.internship.musify.mapper.ArtistMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateArtistRepository {
    private final ArtistMapper artistMapper = new ArtistMapperImpl();

    public List<ArtistDTO> getAllArtists() {
        Transaction transaction = null;
        List<Artist> artists;

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
            return null;
        }

        return artists.stream()
                .map(artistMapper::toDto)
                .collect(Collectors.toList());
    }

    public ArtistDTO getArtistById(int id) {
        Transaction transaction = null;
        Artist artist;

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

            e.printStackTrace();
            return null;
        }

        return artistMapper.toDto(artist);
    }

    public int createArtist(ArtistDTO artistDTO) {
        Transaction transaction = null;
        int id;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Artist artist = artistMapper.toEntity(artistDTO);
            id = (Integer) session.save(artist);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
            return -1;
        }

        return id;
    }

    public void updateArtist(ArtistDTO artistDTO) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Artist artistWithGivenId = session.get(Artist.class, artistDTO.getId());

            artistWithGivenId.setFirstName(artistDTO.getFirstName());
            artistWithGivenId.setLastName(artistDTO.getLastName());
            artistWithGivenId.setStageName(artistDTO.getStageName());
            artistWithGivenId.setBirthday(artistDTO.getBirthday());
            artistWithGivenId.setActivityStartDate(artistDTO.getActivityStartDate());
            artistWithGivenId.setActivityEndDate(artistDTO.getActivityEndDate());

            session.update(artistWithGivenId);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
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
        }
    }

    public void addBand(int artistId, int bandId) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Artist artist = session.get(Artist.class, artistId);
            Band band = session.get(Band.class, bandId);
            if (artist == null || band == null) {
                return;
            }

            artist.addBand(band);
            session.persist(artist);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    public void removeBand(int artistId, int bandId) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Artist artist = session.get(Artist.class, artistId);
            Band band = session.get(Band.class, bandId);
            if (artist == null || band == null) {
                return;
            }

            artist.removeBand(band);
            session.persist(artist);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }
}
*/
