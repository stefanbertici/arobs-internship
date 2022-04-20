package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.AlbumMapper;
import com.arobs.internship.musify.model.Album;
import com.arobs.internship.musify.model.Artist;
import com.arobs.internship.musify.model.Band;
import com.arobs.internship.musify.model.Song;
import com.arobs.internship.musify.repository.AlbumRepository;
import com.arobs.internship.musify.repository.ArtistRepository;
import com.arobs.internship.musify.repository.BandRepository;
import com.arobs.internship.musify.repository.SongRepository;
import com.arobs.internship.musify.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final BandRepository bandRepository;
    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, AlbumMapper albumMapper, SongRepository songRepository, ArtistRepository artistRepository, BandRepository bandRepository) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.bandRepository = bandRepository;
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
    public List<AlbumDTO> readAlbumsByBandId(Integer id) {
        Optional<Band> optional = bandRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no band with id = " + id);
        }

        Set<Album> albums = optional.get().getBandAlbums();

        return albums
                .stream()
                .map(albumMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AlbumDTO createAlbum(AlbumDTO albumDTO) {
        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can create new albums");
        }

        if (!albumDTO.isOnlyOneOwnerIdSet()) {
            throw new IllegalArgumentException("One of artist id or band id must be set, the other must remain null");
        }

        Album album = albumMapper.toEntity(albumDTO);
        addArtistOrBandById(album, albumDTO);
        if (!albumDTO.getSongIds().isEmpty()) {
            addSongsById(album, albumDTO);
        }

        album = albumRepository.save(album);
        return albumMapper.toDto(album);
    }

    @Transactional
    public AlbumDTO updateAlbum(Integer id, AlbumDTO albumDTO) {
        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can update albums");
        }

        if (!albumDTO.isOnlyOneOwnerIdSet()) {
            throw new IllegalArgumentException("One of artist id or band id must be set, the other must remain null");
        }

        Optional<Album> optional = albumRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no album with id = " + id);
        }

        Album album = optional.get();
        addArtistOrBandById(album, albumDTO);
        album.setTitle(albumDTO.getTitle());
        album.setDescription(albumDTO.getDescription());
        album.setGenre(albumDTO.getGenre());
        album.setReleaseDate(albumDTO.getReleaseDate());
        album.setLabel(albumDTO.getLabel());
        if (!albumDTO.getSongIds().isEmpty()) {
            clearSongs(album);
            addSongsById(album, albumDTO);
        }

        albumRepository.save(album);
        return albumMapper.toDto(album);
    }

    private void addSongsById(Album album, AlbumDTO albumDTO) {
        List<Song> songs = (List<Song>) songRepository.findAllById(albumDTO.getSongIds());
        for (Song song : songs) {
            album.addSong(song);
        }
    }

    private void clearSongs(Album album) {
        for (Song song : album.getSongs()) {
            song.getAlbums().remove(album);
        }

        album.getSongs().clear();
    }

    private void addArtistOrBandById(Album album, AlbumDTO albumDTO) {
        if (albumDTO.getArtistId() != null && albumDTO.getArtistId() != 0) {
            Integer id = albumDTO.getArtistId();
            Optional<Artist> optional = artistRepository.findById(id);

            if (optional.isEmpty()) {
                throw new ResourceNotFoundException("There is no artist with id = " + id);
            }

            Artist artist = optional.get();
            album.setArtist(artist);
            album.setBand(null);
        } else if (albumDTO.getBandId() != null && albumDTO.getBandId() != 0) {
            Integer id = albumDTO.getBandId();
            Optional<Band> optional = bandRepository.findById(id);

            if (optional.isEmpty()) {
                throw new ResourceNotFoundException("There is no band with id = " + id);
            }

            Band band = optional.get();
            album.setBand(band);
            album.setArtist(null);
        }
    }
}
