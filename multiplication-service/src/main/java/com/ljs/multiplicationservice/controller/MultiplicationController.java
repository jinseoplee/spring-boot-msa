package com.ljs.multiplicationservice.controller;

import com.ljs.multiplicationservice.dto.MultiplicationDto;
import com.ljs.multiplicationservice.service.MultiplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/multiplication")
public class MultiplicationController {
    private final MultiplicationService multiplicationService;

    @GetMapping("/random")
    public ResponseEntity<MultiplicationDto> getRandomMultiplication() {
        return ResponseEntity.ok(multiplicationService.createRandomMultiplication());
    }
}
