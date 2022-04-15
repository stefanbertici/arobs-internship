package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.SongDTO;
import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.SongMapper;
import com.arobs.internship.musify.model.Song;
import com.arobs.internship.musify.repository.SongRepository;
import com.arobs.internship.musify.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    @Autowired
    public SongService(SongRepository songRepository, SongMapper songMapper) {
        this.songRepository = songRepository;
        this.songMapper = songMapper;
    }

    @Transactional
    public SongDTO addSong(SongDTO songDTO) {
        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can create new songs");
        }

        Song song = songMapper.toEntity(songDTO);
        song = songRepository.save(song);

        return songMapper.toDto(song);
    }

    @Transactional
    public SongDTO updateSong(Integer id, SongDTO songDTO) {
        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can update songs");
        }

        Optional<Song> optional = songRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no song with id = " + id);
        }

        Song song = optional.get();
        song.setTitle(songDTO.getTitle());
        song.setDuration(songDTO.getDuration());
        song.setCreatedDate(songDTO.getCreatedDate());
        songRepository.save(song);

        return songMapper.toDto(song);
    }

    /*public List<Song> getSongs() {
        return songRepository.findAll();
    }*/
}
