package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.SongDTO;
import com.arobs.internship.musify.model.Song;
import com.arobs.internship.musify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> getSongs() {
        return new ResponseEntity<>(songService.getSongs(), HttpStatus.OK);
    }

    @PostMapping("/song")
    public ResponseEntity<SongDTO> addSong(@RequestBody SongDTO songDTO) {
        return new ResponseEntity<>(songService.addSong(songDTO), HttpStatus.OK);
    }

    @PutMapping("/song/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable Integer id, @RequestBody SongDTO songDTO) {
        return new ResponseEntity<>(songService.updateSong(id, songDTO), HttpStatus.OK);
    }
}
