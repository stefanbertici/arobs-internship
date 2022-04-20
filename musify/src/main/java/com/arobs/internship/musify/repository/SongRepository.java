package com.arobs.internship.musify.repository;

import com.arobs.internship.musify.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends CrudRepository<Song, Integer> {
}
