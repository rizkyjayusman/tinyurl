package com.rizkyjayusman.tinyurl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/timeout")
public class TimeoutController {

    private final RestTemplate restTemplate;

    @PostMapping("/provider")
    public ResponseEntity<String> timeout() {
        try {
            // Sleep for 3 seconds
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Handle any interruptions that may occur
            e.printStackTrace();
        }

        return ResponseEntity.ok("hello consumer!");
    }

    @PostMapping("/consumer")
    public ResponseEntity<String> doRequest() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        String apiUrl = "http://localhost:8080/timeout/provider";
        String response = restTemplate.postForObject(apiUrl, formData, String.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/consumer-2")
    public ResponseEntity<String> doRequest2() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        String apiUrl = "http://localhost:8080/timeout/provider";
        String response = restTemplate.postForObject(apiUrl, formData, String.class);
        return ResponseEntity.ok(response);
    }

}
