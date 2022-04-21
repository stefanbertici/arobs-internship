package com.arobs.internship.musify.repository;

import com.arobs.internship.musify.model.Album;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Integer> {

    List<Album> findAllByTitleContainingIgnoreCase(String searchTerm);
}
