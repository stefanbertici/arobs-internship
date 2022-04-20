package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.PlaylistDTO;
import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.PlaylistMapper;
import com.arobs.internship.musify.model.*;
import com.arobs.internship.musify.repository.*;
import com.arobs.internship.musify.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlaylistService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final PlaylistMapper playlistMapper;

    @Autowired
    public PlaylistService(AlbumRepository albumRepository, SongRepository songRepository, PlaylistRepository playlistRepository, UserRepository userRepository, PlaylistMapper playlistMapper) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.playlistMapper = playlistMapper;
    }

    @Transactional
    public List<PlaylistDTO> readUserPlaylists() {
        User user = userRepository.findById(JwtUtils.getCurrentUserId()).get();
        Set<Playlist> ownedPlaylists = user.getOwnedPlaylists();
        Set<Playlist> followedPlaylists = user.getSubscribedToPlaylists();

        List<PlaylistDTO> result = ownedPlaylists
                .stream()
                .map(playlistMapper::toDto)
                .collect(Collectors.toList());

        followedPlaylists.forEach(playlist -> result.add(playlistMapper.toDto(playlist)));

        return result;
    }

    @Transactional
    public PlaylistDTO createPlaylist(PlaylistDTO playlistDTO) {
        Playlist playlist = playlistMapper.toEntity(playlistDTO);
        User user = userRepository.findById(JwtUtils.getCurrentUserId()).get();

        if (!playlistDTO.isPrivateOrPublic()) {
            throw new IllegalArgumentException("Playlist type must be \"private\" or \"public\"");
        }

        playlist.setCreatedDate(Date.valueOf(LocalDate.now()));
        playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));
        playlist.setOwnerUser(user);
        playlist = playlistRepository.save(playlist);

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public PlaylistDTO updatePlaylist(Integer id, PlaylistDTO playlistDTO) {
        Optional<Playlist> optional = playlistRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + id);
        }

        if (!playlistDTO.isPrivateOrPublic()) {
            throw new IllegalArgumentException("Playlist type must be \"private\" or \"public\"");
        }

        Playlist playlist = optional.get();
        playlist.setType(playlistDTO.getType());
        playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));
        playlist = playlistRepository.save(playlist);

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public PlaylistDTO addSongToPlaylist(Integer playlistId, Integer songId) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        Optional<Song> optionalSong = songRepository.findById(songId);
        User user = userRepository.findById(JwtUtils.getCurrentUserId()).get();

        if (optionalPlaylist.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + playlistId);
        }

        if (optionalSong.isEmpty()) {
            throw new ResourceNotFoundException("There is no song with id = " + songId);
        }

        Playlist playlist = optionalPlaylist.get();

        if (user.getId().intValue() != playlist.getOwnerUserId().intValue()) {
            throw new UnauthorizedException("You can't modify playlists you do not own");
        }

        Song song = optionalSong.get();
        playlist.addSong(song);
        playlist = playlistRepository.save(playlist);

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public PlaylistDTO addAlbumToPlaylist(Integer playlistId, Integer albumId) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        Optional<Album> optionalAlbum = albumRepository.findById(albumId);
        User user = userRepository.findById(JwtUtils.getCurrentUserId()).get();

        if (optionalPlaylist.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + playlistId);
        }

        if (optionalAlbum.isEmpty()) {
            throw new ResourceNotFoundException("There is no album with id = " + albumId);
        }

        Playlist playlist = optionalPlaylist.get();

        if (user.getId().intValue() != playlist.getOwnerUserId().intValue()) {
            throw new UnauthorizedException("You can't modify playlists you do not own");
        }

        Album album = optionalAlbum.get();
        for (Song song : album.getSongs()) {
            playlist.addSong(song);
        }

        playlist = playlistRepository.save(playlist);

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public String deletePlaylist(Integer id) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);

        if (optionalPlaylist.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + id);
        }

        Playlist playlist = optionalPlaylist.get();
        User user = userRepository.findById(JwtUtils.getCurrentUserId()).get();

        if (user.getId().intValue() != playlist.getOwnerUserId().intValue()) {
            throw new UnauthorizedException("You can only delete your own playlists");
        }

        user.removeOwnedPlaylist(playlist);
        playlistRepository.delete(playlist);

        return "Playlist successfully deleted";
    }

    @Transactional
    public String followPlaylist(Integer id) {
        Optional<Playlist> optional = playlistRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + id);
        }

        Playlist playlist = optional.get();
        User user = userRepository.findById(JwtUtils.getCurrentUserId()).get();

        if (!playlist.getType().equals("public")) {
            throw new UnauthorizedException("Private playlist cannot be followed");
        }

        if (playlist.getOwnerUserId().intValue() == user.getId().intValue()) {
            throw new UnauthorizedException("Your own playlist cannot be followed");
        }

        user.subscribeToPlaylist(playlist);

        return "Successfully followed";
    }

    @Transactional
    public String unfollowPlaylist(Integer id) {
        Optional<Playlist> optional = playlistRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + id);
        }

        Playlist playlist = optional.get();
        User user = userRepository.findById(JwtUtils.getCurrentUserId()).get();

        if (!playlist.getSubscribedUsers().contains(user)) {
            throw new UnauthorizedException("You aren't subscribed to this playlist");
        }

        user.unsubscribeFromPlaylist(playlist);

        return "Successfully unfollowed";
    }
}
