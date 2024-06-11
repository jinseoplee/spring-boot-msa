package com.ljs.memorygame.controller;

import com.ljs.memorygame.dto.RandomNumberResponse;
import com.ljs.memorygame.service.MemoryGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/memory-game")
public class MemoryGameController {
    private final MemoryGameService memoryGameService;

    @GetMapping("/random")
    public ResponseEntity<RandomNumberResponse> getFiveRandomNumbers() {
        return ResponseEntity.ok(new RandomNumberResponse(memoryGameService.generateFiveRandomNumbers()));
    }
}
