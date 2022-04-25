package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.AlbumMapper;
import com.arobs.internship.musify.mapper.ArtistMapper;
import com.arobs.internship.musify.dto.ArtistDTO;
import com.arobs.internship.musify.model.Album;
import com.arobs.internship.musify.model.Artist;
import com.arobs.internship.musify.repository.ArtistRepository;
import com.arobs.internship.musify.utils.UserChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final AlbumMapper albumMapper;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, ArtistMapper artistMapper, AlbumMapper albumMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
        this.albumMapper = albumMapper;
    }

    @Transactional
    public List<AlbumDTO> readAlbumsByArtistId(Integer id) {
        Optional<Artist> optional = artistRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no artist with id = " + id);
        }

        Set<Album> albums = optional.get().getArtistAlbums();

        return albums
                .stream()
                .map(albumMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ArtistDTO createArtist(ArtistDTO artistDTO) {
        if (UserChecks.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can create new artists");
        }

        Artist artist = artistMapper.toEntity(artistDTO);
        artist = artistRepository.save(artist);

        return artistMapper.toDto(artist);
    }

    @Transactional
    public ArtistDTO updateArtist(Integer id, ArtistDTO artistDTO) {
        if (UserChecks.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can update artists");
        }

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

        return artistMapper.toDto(artist);
    }
}
