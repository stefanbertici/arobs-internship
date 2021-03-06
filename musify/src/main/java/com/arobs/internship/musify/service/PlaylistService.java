package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.PlaylistDTO;
import com.arobs.internship.musify.dto.PlaylistViewDTO;
import com.arobs.internship.musify.dto.SongViewDTO;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.PlaylistMapper;
import com.arobs.internship.musify.mapper.SongMapper;
import com.arobs.internship.musify.model.*;
import com.arobs.internship.musify.repository.*;
import com.arobs.internship.musify.security.JwtUtils;
import com.arobs.internship.musify.utils.RepositoryChecker;
import com.arobs.internship.musify.utils.UserChecker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlaylistService {
    private final RepositoryChecker repositoryChecker;
    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final PlaylistMapper playlistMapper;
    private final SongMapper songMapper;

    @Transactional
    public List<PlaylistDTO> readUserPlaylists() {
        User user = repositoryChecker.getCurrentUser();

        Set<Playlist> ownedPlaylists = user.getOwnedPlaylists();
        Set<Playlist> followedPlaylists = user.getFollowedPlaylists();

        List<PlaylistDTO> result = ownedPlaylists
                .stream()
                .map(playlistMapper::toDto)
                .collect(Collectors.toList());

        followedPlaylists.forEach(playlist -> result.add(playlistMapper.toDto(playlist)));

        return result;
    }

    @Transactional
    public List<SongViewDTO> readSongsByPlaylistId(Integer id) {
        Playlist playlist = repositoryChecker.getPlaylistIfExists(id);

        if (playlist.getType().equals("private") && UserChecker.isCurrentUserNotOwnerOfPlaylist(playlist)) {
            throw new UnauthorizedException("You cannot view this private playlist");
        }

        return songMapper.toViewDtos(playlist.getSongsInPlaylist());
    }

    @Transactional
    public PlaylistDTO createPlaylist(PlaylistDTO playlistDTO) {
        if (!playlistDTO.isPrivateOrPublic()) {
            throw new IllegalArgumentException("Playlist type must be \"private\" or \"public\"");
        }

        User user = repositoryChecker.getCurrentUser();

        Playlist playlist = playlistMapper.toEntity(playlistDTO);
        playlist.setCreatedDate(Date.valueOf(LocalDate.now()));
        playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));
        playlist = playlistRepository.save(playlist);

        user.addOwnedPlaylist(playlist);

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public PlaylistDTO updatePlaylist(Integer id, PlaylistDTO playlistDTO) {
        if (!playlistDTO.isPrivateOrPublic()) {
            throw new IllegalArgumentException("Playlist type must be \"private\" or \"public\"");
        }

        Playlist playlist = repositoryChecker.getPlaylistIfExists(id);

        if (UserChecker.isCurrentUserNotOwnerOfPlaylist(playlist)) {
            throw new UnauthorizedException("You can't modify playlists you do not own");
        }

        playlist.setName(playlistDTO.getName());
        playlist.setType(playlistDTO.getType());
        playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public PlaylistViewDTO addSongToPlaylist(Integer playlistId, Integer songId) {
        Playlist playlist = repositoryChecker.getPlaylistIfExists(playlistId);
        Song song = repositoryChecker.getSongIfExists(songId);

        if (UserChecker.isCurrentUserNotOwnerOfPlaylist(playlist)) {
            throw new UnauthorizedException("You can't modify playlists you do not own");
        }

        if (!playlist.getSongsInPlaylist().contains(song)) {
            playlist.addSong(song);
            playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));
        }

        return playlistMapper.toViewDto(playlist);
    }

    @Transactional
    public PlaylistViewDTO removeSongFromPlaylist(Integer playlistId, Integer songId) {
        Playlist playlist = repositoryChecker.getPlaylistIfExists(playlistId);
        Song song = repositoryChecker.getSongIfExists(songId);

        if (UserChecker.isCurrentUserNotOwnerOfPlaylist(playlist)) {
            throw new UnauthorizedException("You can't modify playlists you do not own");
        }

        if (playlist.getSongsInPlaylist().contains(song)) {
            playlist.removeSong(song);
            playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));
        }

        return playlistMapper.toViewDto(playlist);
    }

    @Transactional
    public PlaylistViewDTO addAlbumToPlaylist(Integer playlistId, Integer albumId) {
        Playlist playlist = repositoryChecker.getPlaylistIfExists(playlistId);
        Album album = repositoryChecker.getAlbumIfExists(albumId);


        if (UserChecker.isCurrentUserNotOwnerOfPlaylist(playlist)) {
            throw new UnauthorizedException("You can't modify playlists you do not own");
        }

        for (Song song : album.getSongs()) {
            if (!playlist.getSongsInPlaylist().contains(song)) {
                playlist.addSong(song);
            }
        }

        playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));

        return playlistMapper.toViewDto(playlist);
    }

    @Transactional
    public PlaylistViewDTO changeSongOrder(Integer playlistId, Integer songId, Integer oldPosition, Integer newPosition) {
        Playlist playlist = repositoryChecker.getPlaylistIfExists(playlistId);
        Song song = repositoryChecker.getSongIfExists(songId);

        if (UserChecker.isCurrentUserNotOwnerOfPlaylist(playlist)) {
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
            songs.remove(song);
            songs.add(newPosition - 1, song);
            playlist.setUpdatedDate(Date.valueOf(LocalDate.now()));
            playlistRepository.save(playlist);
        }

        return playlistMapper.toViewDto(playlist);
    }

    @Transactional
    public PlaylistDTO deletePlaylist(Integer id) {
        Playlist playlist = repositoryChecker.getPlaylistIfExists(id);

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));

        if (UserChecker.isCurrentUserNotOwnerOfPlaylist(playlist)) {
            throw new UnauthorizedException("You can only delete your own playlists");
        }

        user.removeOwnedPlaylist(playlist);

        Set<User> followers = playlist.getFollowerUsers();
        for (User follower : followers) {
            follower.getFollowedPlaylists().remove(playlist);
            userRepository.save(follower);
        }

        playlistRepository.delete(playlist);

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public PlaylistDTO followPlaylist(Integer id) {
        Playlist playlist = repositoryChecker.getPlaylistIfExists(id);

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));

        if (playlist.getFollowerUsers().contains(user)) {
            throw new UnauthorizedException("You already follow this playlist");
        }

        if (!playlist.getType().equals("public")) {
            throw new UnauthorizedException("Private playlist cannot be followed");
        }

        if (playlist.getOwnerUserId().intValue() == user.getId().intValue()) {
            throw new UnauthorizedException("You cannot follow your own playlist");
        }

        user.addFollowedPlaylist(playlist);

        return playlistMapper.toDto(playlist);
    }

    @Transactional
    public PlaylistDTO unfollowPlaylist(Integer id) {
        Playlist playlist = repositoryChecker.getPlaylistIfExists(id);

        User user = userRepository.findById(JwtUtils.getCurrentUserId())
                .orElseThrow(() -> new UnauthorizedException("You need to log in"));

        if (!playlist.getFollowerUsers().contains(user)) {
            throw new UnauthorizedException("You have not followed this playlist");
        }

        user.removeFollowedPlaylist(playlist);

        return playlistMapper.toDto(playlist);
    }
}
