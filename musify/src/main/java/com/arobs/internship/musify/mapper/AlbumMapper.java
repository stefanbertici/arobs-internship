package com.arobs.internship.musify.mapper;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.model.Album;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlbumMapper {

    AlbumDTO toDto(Album album);

    List<AlbumDTO> toDtos(List<Album> albums);

    Album toEntity(AlbumDTO albumDTO);
}
