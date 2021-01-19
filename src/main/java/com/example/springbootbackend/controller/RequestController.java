package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Request;
import com.example.springbootbackend.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RequestController {
    private RequestRepository requestRepository;

    @Autowired
    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @GetMapping("/requests")
    public ResponseEntity<List<Request>> getAllRequests() {
        try {
            List<Request> requests = new ArrayList<>();
            requests.addAll(requestRepository.findAll());
            if (requests.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(requests, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/requests")
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        try {
            Request requestTemp = requestRepository
                    .save(new Request(request.getBanner(), request.getUserAgent(), request.getIpAddress(),
                            request.getDate()));
            return new ResponseEntity<>(requestTemp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
