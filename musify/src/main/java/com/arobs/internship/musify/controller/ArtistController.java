package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.ArtistDTO;
import com.arobs.internship.musify.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArtistController {
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @PostMapping("/artist")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artistDTO) {
        return new ResponseEntity<>(artistService.createArtist(artistDTO), HttpStatus.OK);
    }

    @PutMapping("/artist/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Integer id, @RequestBody ArtistDTO artistDTO) {
        return new ResponseEntity<>(artistService.updateArtist(id, artistDTO), HttpStatus.OK);
    }
}
