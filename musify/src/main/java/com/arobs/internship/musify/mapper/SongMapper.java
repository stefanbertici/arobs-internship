package com.arobs.internship.musify.mapper;

import com.arobs.internship.musify.dto.SongDTO;
import com.arobs.internship.musify.dto.SongViewDTO;
import com.arobs.internship.musify.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SongMapper {

    @Mapping(target = "composersIds", expression = "java(song.getComposersIdsList())")
    @Mapping(target = "alternativeTitles", expression = "java(song.getAlternativeSongTitlesList())")
    SongDTO toDto(Song song);

    @Mapping(target = "alternativeTitles", expression = "java(song.getAlternativeSongTitlesList())")
    @Mapping(target = "albums", expression = "java(song.getAlbumsTitlesList())")
    @Mapping(target = "composers", expression = "java(song.getComposersStageNamesList())")
    SongViewDTO toViewDto(Song song);

    Song toEntity(SongDTO songDTO);
}
