package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.dto.BandDTO;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.AlbumMapper;
import com.arobs.internship.musify.mapper.BandMapper;
import com.arobs.internship.musify.model.Album;
import com.arobs.internship.musify.model.Artist;
import com.arobs.internship.musify.model.Band;
import com.arobs.internship.musify.repository.ArtistRepository;
import com.arobs.internship.musify.repository.BandRepository;
import com.arobs.internship.musify.utils.RepositoryChecker;
import com.arobs.internship.musify.utils.UserChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BandService {
    private final RepositoryChecker repositoryChecker;
    private final BandRepository bandRepository;
    private final ArtistRepository artistRepository;
    private final BandMapper bandMapper;
    private final AlbumMapper albumMapper;

    @Autowired
    public BandService(RepositoryChecker repositoryChecker, BandRepository bandRepository, BandMapper bandMapper, ArtistRepository artistRepository, AlbumMapper albumMapper) {
        this.repositoryChecker = repositoryChecker;
        this.bandRepository = bandRepository;
        this.artistRepository = artistRepository;
        this.bandMapper = bandMapper;
        this.albumMapper = albumMapper;
    }

    @Transactional
    public List<AlbumDTO> readAlbumsByBandId(Integer id) {
        Band band = repositoryChecker.getBandIfExists(id);

        Set<Album> albums = band.getBandAlbums();

        return albums
                .stream()
                .map(albumMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BandDTO createBand(BandDTO bandDTO) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can create new bands");
        }

        Band band = bandMapper.toEntity(bandDTO);
        band = bandRepository.save(band);

        if (!bandDTO.getBandMembersIds().isEmpty()) {
            addMembersById(band, bandDTO);
        }

        return bandMapper.toDto(band);
    }

    @Transactional
    public BandDTO updateBand(Integer id, BandDTO bandDTO) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can update bands");
        }

        Band band = repositoryChecker.getBandIfExists(id);

        band.setBandName(bandDTO.getBandName());
        band.setLocation(bandDTO.getLocation());
        band.setActivityStartDate(bandDTO.getActivityStartDate());
        band.setActivityEndDate(bandDTO.getActivityEndDate());

        if (!bandDTO.getBandMembersIds().isEmpty()) {
            clearMembers(band);
            addMembersById(band, bandDTO);
        }

        return bandMapper.toDto(band);
    }

    public void clearMembers(Band band) {
        Set<Artist> currentMembers = band.getArtists();
        for (Artist artist : currentMembers) {
            artist.getBands().remove(band);
        }

        band.getArtists().clear();
    }

    public void addMembersById(Band band, BandDTO bandDTO) {
        List<Artist> artists = (List<Artist>) artistRepository.findAllById(bandDTO.getBandMembersIds());
        for (Artist artist : artists) {
            band.addArtist(artist);
        }
    }
}
