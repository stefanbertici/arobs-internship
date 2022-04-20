package com.arobs.internship.musify.mapper;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    @Mapping(target = "songIds", expression = "java(album.getSongIds())")
    @Mapping(target = "artistId", expression = "java(album.getArtistId())")
    @Mapping(target = "bandId", expression = "java(album.getBandId())")
    AlbumDTO toDto(Album album);

    List<AlbumDTO> toDtos(List<Album> albums);

    Album toEntity(AlbumDTO albumDTO);
}
