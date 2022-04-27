package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.mapper.AlbumMapper;
import com.arobs.internship.musify.mapper.ArtistMapper;
import com.arobs.internship.musify.dto.ArtistDTO;
import com.arobs.internship.musify.model.Artist;
import com.arobs.internship.musify.repository.ArtistRepository;
import com.arobs.internship.musify.utils.RepositoryChecker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArtistService {
    private final RepositoryChecker repositoryChecker;
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final AlbumMapper albumMapper;

    @Transactional
    public List<AlbumDTO> readAlbumsByArtistId(Integer id) {
        Artist artist = repositoryChecker.getArtistIfExists(id);

        return albumMapper.toDtos(new ArrayList<>(artist.getArtistAlbums()));
    }

    @Transactional
    public ArtistDTO createArtist(ArtistDTO artistDTO) {
        Artist artist = artistMapper.toEntity(artistDTO);
        artist = artistRepository.save(artist);

        return artistMapper.toDto(artist);
    }

    @Transactional
    public ArtistDTO updateArtist(Integer id, ArtistDTO artistDTO) {
        Artist artist = repositoryChecker.getArtistIfExists(id);

        artist.setFirstName(artistDTO.getFirstName());
        artist.setLastName(artistDTO.getLastName());
        artist.setStageName(artistDTO.getStageName());
        artist.setBirthday(artistDTO.getBirthday());
        artist.setActivityStartDate(artistDTO.getActivityStartDate());
        artist.setActivityEndDate(artistDTO.getActivityEndDate());

        return artistMapper.toDto(artist);
    }
}
