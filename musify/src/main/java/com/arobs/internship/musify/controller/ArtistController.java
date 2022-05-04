package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.dto.ArtistDTO;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.service.ArtistService;
import com.arobs.internship.musify.utils.UserChecker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("/{id}/albums")
    public ResponseEntity<List<AlbumDTO>> readAlbumsByArtistId(@PathVariable Integer id) {
        return new ResponseEntity<>(artistService.readAlbumsByArtistId(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody @Valid ArtistDTO artistDTO) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can create new artists");
        }

        return new ResponseEntity<>(artistService.createArtist(artistDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Integer id, @RequestBody @Valid ArtistDTO artistDTO) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can update artists");
        }

        return new ResponseEntity<>(artistService.updateArtist(id, artistDTO), HttpStatus.OK);
    }
}


