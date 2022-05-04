package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.AlbumDTO;
import com.arobs.internship.musify.dto.BandDTO;
import com.arobs.internship.musify.exception.UnauthorizedException;
import com.arobs.internship.musify.service.BandService;
import com.arobs.internship.musify.utils.UserChecker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/band")
public class BandController {
    private final BandService bandService;

    @GetMapping("/{id}/albums")
    public ResponseEntity<List<AlbumDTO>> readAlbumsByBandId(@PathVariable Integer id) {
        return new ResponseEntity<>(bandService.readAlbumsByBandId(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BandDTO> createBand(@RequestBody @Valid BandDTO bandDTO) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can create new bands");
        }

        return new ResponseEntity<>(bandService.createBand(bandDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BandDTO> updateBand(@PathVariable Integer id, @RequestBody @Valid BandDTO bandDTO) {
        if (UserChecker.isCurrentUserNotAdmin()) {
            throw new UnauthorizedException("Only admins can update bands");
        }

        return new ResponseEntity<>(bandService.updateBand(id, bandDTO), HttpStatus.OK);
    }
}
