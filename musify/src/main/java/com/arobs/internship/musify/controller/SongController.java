package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.SongDTO;
import com.arobs.internship.musify.service.SongService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class SongController {
    private final SongService songService;

    @PostMapping("/song")
    public ResponseEntity<SongDTO> createSong(@RequestBody @Valid SongDTO songDTO) {
        return new ResponseEntity<>(songService.createSong(songDTO), HttpStatus.OK);
    }

    @PutMapping("/song/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable Integer id, @RequestBody @Valid SongDTO songDTO) {
        return new ResponseEntity<>(songService.updateSong(id, songDTO), HttpStatus.OK);
    }
}
