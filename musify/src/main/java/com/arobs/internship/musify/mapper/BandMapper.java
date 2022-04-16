package com.arobs.internship.musify.mapper;

import com.arobs.internship.musify.dto.BandDTO;
import com.arobs.internship.musify.model.Band;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BandMapper {

    @Mapping(target = "bandMembersIds", expression = "java(band.getArtistsIds())")
    BandDTO toDto(Band band);

    Band toEntity(BandDTO bandDTO);
}
