package com.arobs.internship.musify.repository;

import com.arobs.internship.musify.model.Band;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends CrudRepository<Band, Integer> {
}
