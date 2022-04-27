package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.dto.SongViewDTO;
import com.arobs.internship.musify.mapper.AlbumMapper;
import com.arobs.internship.musify.mapper.SongMapper;
import com.arobs.internship.musify.model.Album;
import com.arobs.internship.musify.model.Artist;
import com.arobs.internship.musify.model.Band;
import com.arobs.internship.musify.model.Song;
import com.arobs.internship.musify.repository.AlbumRepository;
import com.arobs.internship.musify.repository.SongRepository;
import com.arobs.internship.musify.utils.RepositoryChecker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AlbumService {
    private final RepositoryChecker repositoryChecker;
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final AlbumMapper albumMapper;
    private final SongMapper songMapper;

    @Transactional
    public List<SongViewDTO> readSongsByAlbumId(Integer id) {
        Album album = repositoryChecker.getAlbumIfExists(id);
        List<Song> songs = album.getSongs();

        return songMapper.toViewDtos(songs);
    }

    @Transactional
    public AlbumDTO createAlbum(AlbumDTO albumDTO) {
        if (!albumDTO.isOnlyOneOwnerIdSet()) {
            throw new IllegalArgumentException("One of artist id or band id must be set, the other must remain null");
        }

        Album album = albumMapper.toEntity(albumDTO);
        album = albumRepository.save(album);

        addArtistOrBandById(album, albumDTO);

        if (!albumDTO.getSongIds().isEmpty()) {
            addSongsById(album, albumDTO);
        }

        return albumMapper.toDto(album);
    }

    @Transactional
    public AlbumDTO updateAlbum(Integer id, AlbumDTO albumDTO) {
        if (!albumDTO.isOnlyOneOwnerIdSet()) {
            throw new IllegalArgumentException("One of artist id or band id must be set, the other must remain null");
        }

        Album album = repositoryChecker.getAlbumIfExists(id);

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

        return albumMapper.toDto(album);
    }

    private void addSongsById(Album album, AlbumDTO albumDTO) {
        List<Song> songs = (List<Song>) songRepository.findAllById(albumDTO.getSongIds());
        for (Song song : songs) {
            if (!album.getSongs().contains(song)) {
                album.addSong(song);
            }
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
            Artist artist = repositoryChecker.getArtistIfExists(id);
            artist.addAlbum(album);
        } else if (albumDTO.getBandId() != null && albumDTO.getBandId() != 0) {
            Integer id = albumDTO.getBandId();
            Band band = repositoryChecker.getBandIfExists(id);
            band.addAlbum(album);
        }
    }
}
