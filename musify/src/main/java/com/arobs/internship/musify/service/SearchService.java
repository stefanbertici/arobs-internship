package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.SearchViewDTO;
import com.arobs.internship.musify.mapper.AlbumMapper;
import com.arobs.internship.musify.mapper.ArtistMapper;
import com.arobs.internship.musify.mapper.BandMapper;
import com.arobs.internship.musify.mapper.SongMapper;
import com.arobs.internship.musify.repository.AlbumRepository;
import com.arobs.internship.musify.repository.ArtistRepository;
import com.arobs.internship.musify.repository.BandRepository;
import com.arobs.internship.musify.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SearchService {
    private final AlbumRepository albumRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final BandRepository bandRepository;
    private final ArtistMapper artistMapper;
    private final BandMapper bandMapper;
    private final SongMapper songMapper;
    private final AlbumMapper albumMapper;

    @Autowired
    public SearchService(AlbumRepository albumRepository, SongRepository songRepository, ArtistRepository artistRepository, BandRepository bandRepository, ArtistMapper artistMapper, BandMapper bandMapper, SongMapper songMapper, AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.bandRepository = bandRepository;
        this.artistMapper = artistMapper;
        this.bandMapper = bandMapper;
        this.songMapper = songMapper;
        this.albumMapper = albumMapper;
    }

    @Transactional
    public SearchViewDTO search(String searchTerm) {
        SearchViewDTO searchViewDTO = new SearchViewDTO();
        searchViewDTO.setArtists(artistMapper.toDtos(artistRepository.findAllByStageNameContainingIgnoreCase(searchTerm)));
        searchViewDTO.setBands(bandMapper.toDtos(bandRepository.findAllByBandNameContainingIgnoreCase(searchTerm)));
        searchViewDTO.setAlbums(albumMapper.toDtos(albumRepository.findAllByTitleContainingIgnoreCase(searchTerm)));
        searchViewDTO.setSongs(songMapper.toViewDtos(songRepository.findAllByTitleContainingIgnoreCase(searchTerm)));

        return searchViewDTO;
    }
}
