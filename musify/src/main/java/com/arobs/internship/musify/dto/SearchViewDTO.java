package com.arobs.internship.musify.dto;

import java.util.List;

public class SearchViewDTO {
    private List<ArtistDTO> artists;
    private List<BandDTO> bands;
    private List<AlbumDTO> albums;
    private List<SongViewDTO> songs;

    public SearchViewDTO() {
    }

    public SearchViewDTO(List<ArtistDTO> artists, List<BandDTO> bands, List<AlbumDTO> albums, List<SongViewDTO> songs) {
        this.artists = artists;
        this.bands = bands;
        this.albums = albums;
        this.songs = songs;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDTO> artists) {
        this.artists = artists;
    }

    public List<BandDTO> getBands() {
        return bands;
    }

    public void setBands(List<BandDTO> bands) {
        this.bands = bands;
    }

    public List<AlbumDTO> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumDTO> albums) {
        this.albums = albums;
    }

    public List<SongViewDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongViewDTO> songs) {
        this.songs = songs;
    }
}
