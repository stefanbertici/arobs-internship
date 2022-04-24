package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.dto.BandDTO;
import com.arobs.internship.musify.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BandController {
    private final BandService bandService;

    @Autowired
    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GetMapping("/band/{id}/albums")
    public ResponseEntity<List<AlbumDTO>> readAlbumsByBandId(@PathVariable Integer id) {
        return new ResponseEntity<>(bandService.readAlbumsByBandId(id), HttpStatus.OK);
    }

    @PostMapping("/band")
    public ResponseEntity<BandDTO> createBand(@RequestBody @Valid BandDTO bandDTO) {
        return new ResponseEntity<>(bandService.createBand(bandDTO), HttpStatus.OK);
    }

    @PutMapping("/band/{id}")
    public ResponseEntity<BandDTO> updateBand(@PathVariable Integer id, @RequestBody @Valid BandDTO bandDTO) {
        return new ResponseEntity<>(bandService.updateBand(id, bandDTO), HttpStatus.OK);
    }
}
