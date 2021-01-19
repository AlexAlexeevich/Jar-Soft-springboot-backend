package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Request;
import com.example.springbootbackend.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class RequestController {
    private RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }


    @GetMapping("/requests")
    public ResponseEntity<List<Request>> getAllRequests() {
        try {
            List<Request> requests = new ArrayList<>();
            requests.addAll(requestService.findAll());
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
            Request requestTemp = requestService
                    .save(new Request(request.getBanner(), request.getUserAgent(), request.getIpAddress(),
                            request.getDate()));
            return new ResponseEntity<>(requestTemp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }
}
