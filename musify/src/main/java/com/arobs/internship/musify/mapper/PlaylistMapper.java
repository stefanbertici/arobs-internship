package com.arobs.internship.musify.mapper;

import com.arobs.internship.musify.dto.PlaylistDTO;
import com.arobs.internship.musify.model.Playlist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    @Mapping(target = "ownerUserId", expression = "java(playlist.getOwnerUserId())")
    PlaylistDTO toDto(Playlist playlist);

    List<PlaylistDTO> toDtos(List<Playlist> playlists);

    Playlist toEntity(PlaylistDTO playlistTO);
}
