package com.sortingvisualizer.controller;

import com.sortingvisualizer.model.SortResponse;
import com.sortingvisualizer.service.SortService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sort")
public class SortController {

    private final SortService sortService;

    public SortController(SortService sortService) {
        this.sortService = sortService;
    }

    @PostMapping("/{algorithm}")
    public SortResponse sortArray(@PathVariable String algorithm, @RequestBody int[] array) {
        return sortService.sort(algorithm, array);
    }
}
