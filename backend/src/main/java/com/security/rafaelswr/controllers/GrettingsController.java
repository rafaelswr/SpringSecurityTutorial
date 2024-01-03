package com.security.rafaelswr.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class GrettingsController {

    @GetMapping("/hello")
    public ResponseEntity<String> greetings(){
        return new ResponseEntity<>("Ola", HttpStatus.OK);
    }

    @GetMapping("/goodbye")
    public ResponseEntity<String> goodbye(){
        return ResponseEntity.ok("Goodbye! See you later! ");

    }

}
