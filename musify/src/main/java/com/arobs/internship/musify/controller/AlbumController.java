package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums/artist/{id}")
    public ResponseEntity<List<AlbumDTO>> readAlbumsByArtistId(@PathVariable Integer id) {
        return new ResponseEntity<>(albumService.readAlbumsByArtistId(id), HttpStatus.OK);
    }

    @GetMapping("/albums/band/{id}")
    public ResponseEntity<List<AlbumDTO>> readAlbumsByBandId(@PathVariable Integer id) {
        return new ResponseEntity<>(albumService.readAlbumsByBandId(id), HttpStatus.OK);
    }

    @PostMapping("/album")
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO) {
        return new ResponseEntity<>(albumService.createAlbum(albumDTO), HttpStatus.OK);
    }

    @PutMapping("/album/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Integer id, @RequestBody AlbumDTO albumDTO) {
        return new ResponseEntity<>(albumService.updateAlbum(id, albumDTO), HttpStatus.OK);
    }
}
