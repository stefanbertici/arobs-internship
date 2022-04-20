package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.SongDTO;
import com.arobs.internship.musify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    // TODO get songs based on playlist
    /*@GetMapping("/songs")
    public ResponseEntity<List<Song>> readSongs() {
        return new ResponseEntity<>(songService.readSongs(), HttpStatus.OK);
    }*/

    // TODO get songs based on albums

    @PostMapping("/song")
    public ResponseEntity<SongDTO> createSong(@RequestBody SongDTO songDTO) {
        return new ResponseEntity<>(songService.createSong(songDTO), HttpStatus.OK);
    }

    @PutMapping("/song/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable Integer id, @RequestBody SongDTO songDTO) {
        return new ResponseEntity<>(songService.updateSong(id, songDTO), HttpStatus.OK);
    }
}
