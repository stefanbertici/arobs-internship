package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.BandDTO;
import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.BandMapper;
import com.arobs.internship.musify.model.Artist;
import com.arobs.internship.musify.model.Band;
import com.arobs.internship.musify.repository.ArtistRepository;
import com.arobs.internship.musify.repository.BandRepository;
import com.arobs.internship.musify.utils.UserChecks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BandService {
    private final BandRepository bandRepository;
    private final ArtistRepository artistRepository;
    private final BandMapper bandMapper;

    @Autowired
    public BandService(BandRepository bandRepository, BandMapper bandMapper, ArtistRepository artistRepository) {
        this.bandRepository = bandRepository;
        this.artistRepository = artistRepository;
        this.bandMapper = bandMapper;
    }

    @Transactional
    public BandDTO createBand(BandDTO bandDTO) {
        if (UserChecks.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can create new bands");
        }

        Band band = bandMapper.toEntity(bandDTO);
        if (!bandDTO.getBandMembersIds().isEmpty()) {
            addMembersById(band, bandDTO);
        }

        band = bandRepository.save(band);

        return bandMapper.toDto(band);
    }

    @Transactional
    public BandDTO updateBand(Integer id, BandDTO bandDTO) {
        if (UserChecks.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can update bands");
        }

        Optional<Band> optional = bandRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("There is no band with id = " + id);
        }

        Band band = optional.get();
        band.setBandName(bandDTO.getBandName());
        band.setLocation(bandDTO.getLocation());
        band.setActivityStartDate(bandDTO.getActivityStartDate());
        band.setActivityEndDate(bandDTO.getActivityEndDate());
        if (!bandDTO.getBandMembersIds().isEmpty()) {
            clearMembers(band);
            addMembersById(band, bandDTO);
        }

        band = bandRepository.save(band);

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
