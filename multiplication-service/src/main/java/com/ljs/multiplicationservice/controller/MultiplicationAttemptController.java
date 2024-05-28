package com.ljs.multiplicationservice.controller;

import com.ljs.multiplicationservice.dto.MultiplicationAttemptRequest;
import com.ljs.multiplicationservice.dto.MultiplicationAttemptResponse;
import com.ljs.multiplicationservice.service.MultiplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/multiplication")
public class MultiplicationAttemptController {
    private final MultiplicationService multiplicationService;

    @PostMapping("/attempt")
    public ResponseEntity<MultiplicationAttemptResponse> postAnswer(@Valid @RequestBody MultiplicationAttemptRequest request) {
        boolean correct = multiplicationService.checkAnswer(request);
        MultiplicationAttemptResponse body = new MultiplicationAttemptResponse(
                request.getMultiplicationDto(),
                request.getAnswer(),
                correct
        );
        return ResponseEntity.ok(body);
    }
}
