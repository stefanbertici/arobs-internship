package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.BandDTO;
import com.arobs.internship.musify.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BandController {
    private final BandService bandService;

    @Autowired
    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @PostMapping("/band")
    public ResponseEntity<BandDTO> addBand(@RequestBody BandDTO bandDTO) {
        return new ResponseEntity<>(bandService.addBand(bandDTO), HttpStatus.OK);
    }

    @PutMapping("/band/{id}")
    public ResponseEntity<BandDTO> updateBand(@PathVariable Integer id, @RequestBody BandDTO bandDTO) {
        return new ResponseEntity<>(bandService.updateBand(id, bandDTO), HttpStatus.OK);
    }
}
