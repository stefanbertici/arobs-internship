package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.AlbumMapper;
import com.arobs.internship.musify.model.Album;
import com.arobs.internship.musify.repository.AlbumRepository;
import com.arobs.internship.musify.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
    }

    @Transactional
    public AlbumDTO addAlbum(AlbumDTO albumDTO) {
        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can create new albums");
        }

        Album album = albumMapper.toEntity(albumDTO);
        album = albumRepository.save(album);

        return albumMapper.toDto(album);
    }

    @Transactional
    public AlbumDTO updateAlbum(Integer id, AlbumDTO albumDTO) {
        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can update albums");
        }

        Optional<Album> optional = albumRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no album with id = " + id);
        }

        Album album = optional.get();
        album.setTitle(albumDTO.getTitle());
        album.setDescription(albumDTO.getDescription());
        album.setGenre(albumDTO.getGenre());
        album.setReleaseDate(albumDTO.getReleaseDate());
        album.setLabel(albumDTO.getLabel());
        albumRepository.save(album);

        return albumMapper.toDto(album);
    }
}
