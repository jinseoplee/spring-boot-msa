package com.ljs.gamificationservice.controller;

import com.ljs.gamificationservice.domain.LeaderboardRow;
import com.ljs.gamificationservice.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
    private final LeaderboardService leaderBoardService;

    @GetMapping
    public ResponseEntity<List<LeaderboardRow>> getLeaderboard() {
        System.out.println(leaderBoardService.getCurrentLeaderboard().size());
        return ResponseEntity.ok(leaderBoardService.getCurrentLeaderboard());
    }
}
