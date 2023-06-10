package com.rizkyjayusman.tinyurl.controller;

import com.rizkyjayusman.tinyurl.service.TinyUrlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/tiny-url")
public class TinyUrlController {

    private final TinyUrlService tinyUrlService;

    @PostMapping
    public ResponseEntity<String> encode(@RequestParam("url") String url) {
        log.error("TinyUrlController.encode() :: start encoding url :: {}", url);
        return ResponseEntity.ok(tinyUrlService.encode(url));
    }

    @GetMapping("/{uniqueCode}")
    public ResponseEntity<String> decode(@PathVariable("uniqueCode") String uniqueCode) {
        log.error("TinyUrlController.encode() :: start decoding unique code :: {}", uniqueCode);
        String originalUrl = tinyUrlService.decode(uniqueCode);
        if (originalUrl == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(originalUrl);
    }

}
