package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.dto.BandDTO;
import com.arobs.internship.musify.mapper.AlbumMapper;
import com.arobs.internship.musify.mapper.BandMapper;
import com.arobs.internship.musify.model.Artist;
import com.arobs.internship.musify.model.Band;
import com.arobs.internship.musify.repository.ArtistRepository;
import com.arobs.internship.musify.repository.BandRepository;
import com.arobs.internship.musify.utils.RepositoryChecker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class BandService {
    private final RepositoryChecker repositoryChecker;
    private final BandRepository bandRepository;
    private final ArtistRepository artistRepository;
    private final BandMapper bandMapper;
    private final AlbumMapper albumMapper;

    @Transactional
    public List<AlbumDTO> readAlbumsByBandId(Integer id) {
        Band band = repositoryChecker.getBandIfExists(id);

        return albumMapper.toDtos(new ArrayList<>(band.getBandAlbums()));
    }

    @Transactional
    public BandDTO createBand(BandDTO bandDTO) {
        Band band = bandMapper.toEntity(bandDTO);
        band = bandRepository.save(band);

        if (!bandDTO.getBandMembersIds().isEmpty()) {
            addMembersById(band, bandDTO);
        }

        return bandMapper.toDto(band);
    }

    @Transactional
    public BandDTO updateBand(Integer id, BandDTO bandDTO) {
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
