package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.BandDTO;
import com.arobs.internship.musify.model.Band;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BandMapper {

    BandDTO toDto(Band band);

    Band toEntity(BandDTO bandDTO);
}
