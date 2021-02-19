package com.format.controller;

import com.format.exception.UnknownAlignmentException;
import com.format.service.TextFormatterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class FormatController {

    private final TextFormatterService textFormatterService;


    @PostMapping("/align/{alignType}/length/{length}")
    public ResponseEntity<String> align(@PathVariable @NonNull String alignType, @PathVariable int length, @NonNull @RequestBody String text) {
        return ResponseEntity.ok(textFormatterService.alignText(text, alignType, length));
    }

    @ExceptionHandler(UnknownAlignmentException.class)
    public final ResponseEntity<String> handleAllExceptions(UnknownAlignmentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
