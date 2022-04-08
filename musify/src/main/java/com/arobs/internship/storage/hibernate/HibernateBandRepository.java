package com.arobs.internship.storage.hibernate;

import com.arobs.internship.musify.dto.BandDTO;
import com.arobs.internship.musify.mapper.BandMapperImpl;
import com.arobs.internship.musify.model.Band;
import com.arobs.internship.musify.mapper.BandMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateBandRepository {
    private final BandMapper bandMapper = new BandMapperImpl();

    public List<BandDTO> getAllBands() {
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
            return null;
        }

        return bands
                .stream()
                .map(bandMapper::toDto)
                .collect(Collectors.toList());
    }

    public BandDTO getBandById(int id) {
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

            e.printStackTrace();
            return null;
        }

        return bandMapper.toDto(band);
    }

    public int createBand(BandDTO bandDTO) {
        Transaction transaction = null;
        int id;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Band band = bandMapper.toEntity(bandDTO);
            id = (Integer) session.save(band);

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

    public void updateBand(BandDTO bandDTO) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Band bandWithGivenId = session.get(Band.class, bandDTO.getId());

            bandWithGivenId.setBandName(bandDTO.getBandName());
            bandWithGivenId.setLocation(bandDTO.getLocation());
            bandWithGivenId.setActivityStartDate(bandDTO.getActivityStartDate());
            bandWithGivenId.setActivityEndDate(bandDTO.getActivityEndDate());

            session.update(bandWithGivenId);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
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
        }
    }
}
