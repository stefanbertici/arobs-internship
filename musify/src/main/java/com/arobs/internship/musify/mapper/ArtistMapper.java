package com.arobs.internship.musify.mapper;

import com.arobs.internship.musify.dto.ArtistDTO;
import com.arobs.internship.musify.model.Artist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistDTO toDto(Artist artist);

    List<ArtistDTO> toDtos(List<Artist> artists);

    Artist toEntity(ArtistDTO artistDTO);
}
