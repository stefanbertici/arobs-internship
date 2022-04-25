package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.PlaylistDTO;
import com.arobs.internship.musify.dto.SongViewDTO;
import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.PlaylistMapper;
import com.arobs.internship.musify.mapper.SongMapper;
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
    private final SongMapper songMapper;

    @Autowired
    public PlaylistService(AlbumRepository albumRepository, SongRepository songRepository, PlaylistRepository playlistRepository, UserRepository userRepository, PlaylistMapper playlistMapper, SongMapper songMapper) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
        this.userRepository = userRepository;
        this.playlistMapper = playlistMapper;
        this.songMapper = songMapper;
    }

    @Transactional
    public List<PlaylistDTO> readUserPlaylists() {
        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));

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
    public List<SongViewDTO> readSongsByPlaylistId(Integer id) {
        Optional<Playlist> optional = playlistRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + id);
        }

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));
        Playlist playlist = optional.get();

        if (playlist.getType().equals("private") && playlist.getOwnerUserId().intValue() != user.getId().intValue()) {
            throw new UnauthorizedException("You cannot view this private playlist");
        }

        List<Song> songs = optional.get().getSongsInPlaylist();

        return songs
                .stream()
                .map(songMapper::toViewDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public PlaylistDTO createPlaylist(PlaylistDTO playlistDTO) {
        if (!playlistDTO.isPrivateOrPublic()) {
            throw new IllegalArgumentException("Playlist type must be \"private\" or \"public\"");
        }

        Playlist playlist = playlistMapper.toEntity(playlistDTO);
        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));

        playlist = playlistRepository.save(playlist);

        playlist.setCreatedDate(Date.valueOf(LocalDate.now()));
        playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));
        playlist.setOwnerUser(user);

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public PlaylistDTO updatePlaylist(Integer id, PlaylistDTO playlistDTO) {
        if (!playlistDTO.isPrivateOrPublic()) {
            throw new IllegalArgumentException("Playlist type must be \"private\" or \"public\"");
        }

        Optional<Playlist> optional = playlistRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + id);
        }

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));
        Playlist playlist = optional.get();

        if (user.getId().intValue() != playlist.getOwnerUserId().intValue()) {
            throw new UnauthorizedException("You can't modify playlists you do not own");
        }

        playlist.setType(playlistDTO.getType());
        playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public PlaylistDTO addSongToPlaylist(Integer playlistId, Integer songId) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        Optional<Song> optionalSong = songRepository.findById(songId);

        if (optionalPlaylist.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + playlistId);
        }

        if (optionalSong.isEmpty()) {
            throw new ResourceNotFoundException("There is no song with id = " + songId);
        }

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));
        Playlist playlist = optionalPlaylist.get();

        if (user.getId().intValue() != playlist.getOwnerUserId().intValue()) {
            throw new UnauthorizedException("You can't modify playlists you do not own");
        }

        Song song = optionalSong.get();
        playlist.addSong(song);
        playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public PlaylistDTO removeSongFromPlaylist(Integer playlistId, Integer songId) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        Optional<Song> optionalSong = songRepository.findById(songId);

        if (optionalPlaylist.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + playlistId);
        }

        if (optionalSong.isEmpty()) {
            throw new ResourceNotFoundException("There is no song with id = " + songId);
        }

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));
        Playlist playlist = optionalPlaylist.get();

        if (user.getId().intValue() != playlist.getOwnerUserId().intValue()) {
            throw new UnauthorizedException("You can't modify playlists you do not own");
        }

        Song song = optionalSong.get();
        playlist.removeSong(song);
        playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public PlaylistDTO addAlbumToPlaylist(Integer playlistId, Integer albumId) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        Optional<Album> optionalAlbum = albumRepository.findById(albumId);

        if (optionalPlaylist.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + playlistId);
        }

        if (optionalAlbum.isEmpty()) {
            throw new ResourceNotFoundException("There is no album with id = " + albumId);
        }

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));
        Playlist playlist = optionalPlaylist.get();

        if (user.getId().intValue() != playlist.getOwnerUserId().intValue()) {
            throw new UnauthorizedException("You can't modify playlists you do not own");
        }

        Album album = optionalAlbum.get();
        for (Song song : album.getSongs()) {
            if (!playlist.getSongsInPlaylist().contains(song)) {
                playlist.addSong(song);
            }
        }

        playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));

        return playlistMapper.toDto(playlist);
    }

    public PlaylistDTO changeSongOrder(Integer playlistId, Integer songId, Integer oldPosition, Integer newPosition) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        Optional<Song> optionalSong = songRepository.findById(songId);

        if (optionalPlaylist.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + playlistId);
        }

        if (optionalSong.isEmpty()) {
            throw new ResourceNotFoundException("There is no song with id = " + songId);
        }

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));
        Playlist playlist = optionalPlaylist.get();

        if (user.getId().intValue() != playlist.getOwnerUserId().intValue()) {
            throw new UnauthorizedException("You can't modify playlists you do not own");
        }

        List<Song> songs = playlist.getSongsInPlaylist();
        if (oldPosition < 1 || oldPosition > songs.size() || newPosition < 1 || newPosition > songs.size()) {
            throw new IllegalArgumentException("The given positions are not in range.");
        }

        if (songs.get(oldPosition - 1).getId().intValue() != songId.intValue()) {
            throw new IllegalArgumentException("The song introduced is not in the correct position");
        }

        if (!oldPosition.equals(newPosition)) {
            Song song = songs.get(oldPosition - 1);
            songs.remove(song);
            songs.add(newPosition - 1, song);
            playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));
            playlistRepository.save(playlist);
        }

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public String deletePlaylist(Integer id) {
        Optional<Playlist> optional = playlistRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no playlist with id = " + id);
        }

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));
        Playlist playlist = optional.get();

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

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));
        Playlist playlist = optional.get();

        if (playlist.getSubscribedUsers().contains(user)) {
            throw new UnauthorizedException("You already follow this playlist");
        }

        if (!playlist.getType().equals("public")) {
            throw new UnauthorizedException("Private playlist cannot be followed");
        }

        if (playlist.getOwnerUserId().intValue() == user.getId().intValue()) {
            throw new UnauthorizedException("You cannot follow your own playlist");
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

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));
        Playlist playlist = optional.get();

        if (!playlist.getSubscribedUsers().contains(user)) {
            throw new UnauthorizedException("You have not followed this playlist");
        }

        user.unsubscribeFromPlaylist(playlist);

        return "Successfully unfollowed";
    }
}
