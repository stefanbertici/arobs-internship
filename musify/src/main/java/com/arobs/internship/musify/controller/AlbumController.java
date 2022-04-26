package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.dto.SongViewDTO;
import com.arobs.internship.musify.service.AlbumService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping("/album/{id}/songs")
    public ResponseEntity<List<SongViewDTO>> readSongsByAlbumId(@PathVariable Integer id) {
        return new ResponseEntity<>(albumService.readSongsByAlbumId(id), HttpStatus.OK);
    }

    @PostMapping("/album")
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody @Valid AlbumDTO albumDTO) {
        return new ResponseEntity<>(albumService.createAlbum(albumDTO), HttpStatus.OK);
    }

    @PutMapping("/album/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Integer id, @RequestBody @Valid AlbumDTO albumDTO) {
        return new ResponseEntity<>(albumService.updateAlbum(id, albumDTO), HttpStatus.OK);
    }
}
