package com.arobs.internship.musify.service;

import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.mapper.ArtistMapper;
import com.arobs.internship.musify.dto.ArtistDTO;
import com.arobs.internship.musify.model.Artist;
import com.arobs.internship.musify.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, ArtistMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    public List<ArtistDTO> getArtists() {
        return artistMapper.toDtos(artistRepository.findAll());
    }

    public ArtistDTO getArtistById(Integer id) {
        Optional<Artist> optional = artistRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no artist with id = " + id);
        }

        return artistMapper.toDto(optional.get());
    }

    public ArtistDTO addArtist(ArtistDTO artistDTO) {
        Artist artist = artistMapper.toEntity(artistDTO);
        artist = artistRepository.save(artist);

        return artistMapper.toDto(artist);
    }

    @Transactional
    public ArtistDTO updateArtist(Integer id, ArtistDTO artistDTO) {
        Optional<Artist> optional = artistRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no artist with id = " + id);
        }

        Artist artist = optional.get();
        artist.setFirstName(artistDTO.getFirstName());
        artist.setLastName(artistDTO.getLastName());
        artist.setStageName(artistDTO.getStageName());
        artist.setBirthday(artistDTO.getBirthday());
        artist.setActivityStartDate(artistDTO.getActivityStartDate());
        artist.setActivityEndDate(artistDTO.getActivityEndDate());
        artistRepository.save(artist);

        return artistMapper.toDto(artist);
    }

    @Transactional
    public String deleteArtistById(Integer id) {
        Optional<Artist> optional = artistRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no artist with id = " + id);
        }

        artistRepository.delete(optional.get());
        return "Successfully deleted.";
    }
}
