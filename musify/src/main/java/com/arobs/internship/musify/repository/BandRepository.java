package com.arobs.internship.musify.repository;

import com.arobs.internship.musify.model.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepository extends CrudRepository<Band, Integer> {

    List<Band> findAllByBandNameContainingIgnoreCase(String searchTerm);
}
