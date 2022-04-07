package com.arobs.internship.musify.service;

import com.arobs.internship.musify.dto.ArtistDTO;
import com.arobs.internship.musify.model.Artist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistDTO toDto(Artist artist);

    Artist toEntity(ArtistDTO artistDTO);
}
