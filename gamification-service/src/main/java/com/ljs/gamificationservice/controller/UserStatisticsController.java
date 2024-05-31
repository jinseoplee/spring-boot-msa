package com.ljs.gamificationservice.controller;

import com.ljs.gamificationservice.domain.GameStatistics;
import com.ljs.gamificationservice.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statistics")
public class UserStatisticsController {
    private final GameService gameService;

    @GetMapping
    public ResponseEntity<GameStatistics> getStatisticsForUser(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(gameService.retrieveStatisticsForUser(userId));
    }
}
