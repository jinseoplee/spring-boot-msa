package com.ljs.gamificationservice.service;

import com.ljs.gamificationservice.domain.LeaderboardRow;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RequiredArgsConstructor
@Service
public class SseService {
    private final LeaderboardService leaderboardService;

    // 클라이언트와의 Sse 연결을 관리하는 SseEmitter 객체들을 저장하는 리스트
    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    /**
     * 새로운 SseEmitter 객체를 리스트에 추가하고,
     * 연결 완료 또는 타임아웃 시 제거하도록 설정한다.
     *
     * @param emitter 클라이언트와의 SSE 연결을 나타내는 SseEmitter 객체
     */
    public void addEmitter(SseEmitter emitter) {
        // 새로운 SseEmitter 객체를 리스트에 추가한다.
        emitters.add(emitter);

        // 연결이 완료되면 리스트에서 해당 SseEmitter를 제거한다.
        emitter.onCompletion(() -> emitters.remove(emitter));

        // 연결이 타임아웃되면 리스트에서 해당 SseEmitter를 제거한다.
        emitter.onTimeout(() -> emitters.remove(emitter));
    }

    /**
     * 클라이언트에게 현재 리더보드 정보를 주기적으로 전송한다.
     * 예외가 발생한 경우 해당 클라이언트와의 연결을 종료한다.
     */
    @Scheduled(fixedRate = 5000)
    public void sendEvents() {
        List<LeaderboardRow> leaderboard = leaderboardService.getCurrentLeaderboard();

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(leaderboard);
            } catch (IOException e) {
                // 전송 중 오류가 발생하면 해당 클라이언트와의 연결을 종료한다.
                emitter.completeWithError(e);
                emitters.remove(emitter);
            }
        }
    }
}
