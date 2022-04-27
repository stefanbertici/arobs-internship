package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.SongDTO;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.service.SongService;
import com.arobs.internship.musify.utils.UserChecker;
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
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can create new songs");
        }

        return new ResponseEntity<>(songService.createSong(songDTO), HttpStatus.OK);
    }

    @PutMapping("/song/{id}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable Integer id, @RequestBody @Valid SongDTO songDTO) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can update songs");
        }

        return new ResponseEntity<>(songService.updateSong(id, songDTO), HttpStatus.OK);
    }
}
