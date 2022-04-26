package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.dto.ArtistDTO;
import com.arobs.internship.musify.service.ArtistService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("/artist/{id}/albums")
    public ResponseEntity<List<AlbumDTO>> readAlbumsByArtistId(@PathVariable Integer id) {
        return new ResponseEntity<>(artistService.readAlbumsByArtistId(id), HttpStatus.OK);
    }

    @PostMapping("/artist")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody @Valid ArtistDTO artistDTO) {
        return new ResponseEntity<>(artistService.createArtist(artistDTO), HttpStatus.OK);
    }

    @PutMapping("/artist/{id}")
    public ResponseEntity<ArtistDTO> updateArtist(@PathVariable Integer id, @RequestBody @Valid ArtistDTO artistDTO) {
        return new ResponseEntity<>(artistService.updateArtist(id, artistDTO), HttpStatus.OK);
    }
}
