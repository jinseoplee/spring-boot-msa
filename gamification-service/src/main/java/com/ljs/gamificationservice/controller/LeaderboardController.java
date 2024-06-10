package com.ljs.gamificationservice.controller;

import com.ljs.gamificationservice.service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * 리더보드 데이터를 실시간으로 제공하는 컨트롤러
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {
    private final SseService sseService;

    /**
     * 클라이언트가 실시간으로 리더보드 데이터를 구독할 수 있도록 SseEmitter를 생성하고 반환한다.
     *
     * @return ResponseEntity<SseEmitter> 클라이언트에게 반환되는 SseEmitter 객체
     */
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> getLeaderboard() {
        SseEmitter emitter = new SseEmitter();
        sseService.addEmitter(emitter);
        return ResponseEntity.ok(emitter);
    }
}
