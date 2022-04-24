package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.SongDTO;
import com.arobs.internship.musify.dto.SongViewDTO;
import com.arobs.internship.musify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }
    
    @GetMapping("/songs/playlist/{id}")
    public ResponseEntity<List<SongViewDTO>> readSongsByPlaylistId(@PathVariable Integer id) {
        return new ResponseEntity<>(songService.readSongsByPlaylistId(id), HttpStatus.OK);
    }

    @GetMapping("/songs/album/{id}")
    public ResponseEntity<List<SongViewDTO>> readSongsByAlbumId(@PathVariable Integer id) {
        return new ResponseEntity<>(songService.readSongsByAlbumId(id), HttpStatus.OK);
    }

    @PostMapping("/song")
    public ResponseEntity<SongDTO> createSong(@RequestBody @Valid SongDTO songDTO) {
        return new ResponseEntity<>(songService.createSong(songDTO), HttpStatus.OK);
    }

    @PutMapping("/song/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable Integer id, @RequestBody @Valid SongDTO songDTO) {
        return new ResponseEntity<>(songService.updateSong(id, songDTO), HttpStatus.OK);
    }
}
