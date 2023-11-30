package com.caracol.mistral.controller;

import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
@RequestMapping("/api/v1/mistral")
public class MistralController {

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Hola mundo mistral");
    }
}
