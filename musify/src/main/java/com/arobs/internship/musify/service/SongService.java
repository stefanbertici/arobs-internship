package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.SongDTO;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.SongMapper;
import com.arobs.internship.musify.model.*;
import com.arobs.internship.musify.repository.*;
import com.arobs.internship.musify.utils.RepositoryChecker;
import com.arobs.internship.musify.utils.UserChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SongService {
    private final RepositoryChecker repositoryChecker;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlternativeSongTitleRepository alternativeSongTitleRepository;
    private final SongMapper songMapper;

    @Autowired
    public SongService(RepositoryChecker repositoryChecker, SongRepository songRepository, SongMapper songMapper, ArtistRepository artistRepository, AlternativeSongTitleRepository alternativeSongTitleRepository) {
        this.repositoryChecker = repositoryChecker;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.alternativeSongTitleRepository = alternativeSongTitleRepository;
        this.songMapper = songMapper;
    }

    @Transactional
    public SongDTO createSong(SongDTO songDTO) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can create new songs");
        }

        Song song = songMapper.toEntity(songDTO);
        song = songRepository.save(song);

        if (!songDTO.getAlternativeTitles().isEmpty()) {
            addAlternativeTitles(song, songDTO);
        }

        if (!songDTO.getComposersIds().isEmpty()) {
            addComposersById(song, songDTO);
        }

        return songMapper.toDto(song);
    }

    @Transactional
    public SongDTO updateSong(Integer id, SongDTO songDTO) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can update songs");
        }

        Song song = repositoryChecker.getSongIfExists(id);

        song.setTitle(songDTO.getTitle());
        song.setDuration(songDTO.getDuration());
        song.setCreatedDate(songDTO.getCreatedDate());

        if (!songDTO.getAlternativeTitles().isEmpty()) {
            clearAlternativeTitles(song);
            addAlternativeTitles(song, songDTO);
        }

        if (!songDTO.getComposersIds().isEmpty()) {
            clearComposers(song);
            addComposersById(song, songDTO);
        }

        return songMapper.toDto(song);
    }

    private void clearComposers(Song song) {
        for (Artist composer : song.getComposers()) {
            composer.getComposedSongs().remove(song);
        }

        song.getComposers().clear();
    }

    private void addComposersById(Song song, SongDTO songDTO) {
        List<Artist> artists = (List<Artist>) artistRepository.findAllById(songDTO.getComposersIds());
        for (Artist artist : artists) {
            song.addComposer(artist);
        }
    }

    private void addAlternativeTitles(Song song, SongDTO songDTO) {
        for (String title : songDTO.getAlternativeTitles()) {
            AlternativeSongTitle newTitle = new AlternativeSongTitle();
            newTitle.setId(0);
            newTitle.setAlternativeTitle(title);
            newTitle.addSong(song);
            alternativeSongTitleRepository.save(newTitle);
        }
    }

    private void clearAlternativeTitles(Song song) {
        for (AlternativeSongTitle title : song.getAlternativeSongTitles()) {
            title.setSong(null);
            alternativeSongTitleRepository.delete(title);
        }

        song.getAlternativeSongTitles().clear();
    }
}
