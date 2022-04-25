package com.arobs.internship.musify.mapper;

import com.arobs.internship.musify.dto.PlaylistDTO;
import com.arobs.internship.musify.dto.PlaylistViewDTO;
import com.arobs.internship.musify.dto.SongViewDTO;
import com.arobs.internship.musify.model.Playlist;
import com.arobs.internship.musify.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    @Mapping(target = "ownerUserId", expression = "java(playlist.getOwnerUserId())")
    PlaylistDTO toDto(Playlist playlist);

    List<PlaylistDTO> toDtos(List<Playlist> playlists);

    @Mapping(target = "ownerUserId", expression = "java(playlist.getOwnerUserId())")
    @Mapping(target = "songs", expression = "java(getSongViewDTOS(playlist.getSongsInPlaylist()))")
    PlaylistViewDTO toViewDto(Playlist playlist);

    Playlist toEntity(PlaylistDTO playlistTO);

    default List<SongViewDTO> getSongViewDTOS(List<Song> songs) {
        SongMapper songMapper = new SongMapperImpl();
        return songMapper.toViewDtos(songs);
    }
}
