package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.BandDTO;
import com.arobs.internship.musify.exception.ResourceNotFoundException;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.mapper.BandMapper;
import com.arobs.internship.musify.model.Band;
import com.arobs.internship.musify.repository.BandRepository;
import com.arobs.internship.musify.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BandService {
    private final BandRepository bandRepository;
    private final BandMapper bandMapper;

    @Autowired
    public BandService(BandRepository bandRepository, BandMapper bandMapper) {
        this.bandRepository = bandRepository;
        this.bandMapper = bandMapper;
    }

    @Transactional
    public BandDTO addBand(BandDTO bandDTO) {
        if (!UserUtils.isCurrentAdmin()) {
            throw new UnauthorizedException("Only admins can create new bands");
        }

        Band band = bandMapper.toEntity(bandDTO);
        band = bandRepository.save(band);

        return bandMapper.toDto(band);
    }

    @Transactional
    public BandDTO updateBand(Integer id, BandDTO bandDTO) {
        if (!UserUtils.isCurrentAdmin()) {
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
        bandRepository.save(band);

        return bandMapper.toDto(band);
    }
}
