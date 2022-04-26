package com.arobs.internship.musify.controller;

import com.arobs.internship.musify.dto.SearchViewDTO;
import com.arobs.internship.musify.service.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<SearchViewDTO> search(@PathVariable String searchTerm) {
        return new ResponseEntity<>(searchService.search(searchTerm), HttpStatus.OK);
    }
}
