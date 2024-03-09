package com.sparta.hh99springlv4.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 예외 처리가 이렇게 많아지면 어떻게 되는거야
//@ControllerAdvice
//public class LectureControllerAdvice {
//
//    @ExceptionHandler(LectureNotFoundException.class)
//    public ResponseEntity<String> handleLectureNotFoundException(LectureNotFoundException ex) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//    }
//}