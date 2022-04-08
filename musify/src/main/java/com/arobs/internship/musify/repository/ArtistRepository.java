package com.arobs.internship.musify.repository;

import com.arobs.internship.musify.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Integer> {

    @Override
    List<Artist> findAll();
}
