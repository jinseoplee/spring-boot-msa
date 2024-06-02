package com.ljs.multiplicationservice.controller;

import com.ljs.multiplicationservice.dto.MultiplicationAttemptRequest;
import com.ljs.multiplicationservice.dto.MultiplicationAttemptResponse;
import com.ljs.multiplicationservice.service.MultiplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/multiplication/attempt")
public class MultiplicationAttemptController {
    private final MultiplicationService multiplicationService;

    @PostMapping
    public ResponseEntity<MultiplicationAttemptResponse> postAnswer(@Valid @RequestBody MultiplicationAttemptRequest request) {
        boolean correct = multiplicationService.checkAnswer(request);
        MultiplicationAttemptResponse body = new MultiplicationAttemptResponse(
                request.getMultiplicationDto(),
                request.getAnswer(),
                correct
        );
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity<List<MultiplicationAttemptResponse>> getUserRecentAttempts(@RequestParam("nickname") String nickname) {
        return ResponseEntity.ok(multiplicationService.getUserRecentAttempts(nickname));
    }

    @GetMapping("/{attemptId}")
    public ResponseEntity<MultiplicationAttemptResponse> getAttemptById(@PathVariable("attemptId") Long attemptId) {
        return ResponseEntity.ok(multiplicationService.getAttemptById(attemptId));
    }
}
