package com.arobs.internship.musify.mapper;

import com.arobs.internship.musify.dto.SongDTO;
import com.arobs.internship.musify.model.Song;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {

    SongDTO toDto(Song song);

    List<SongDTO> toDtos(List<Song> songs);

    Song toEntity(SongDTO songDTO);
}
