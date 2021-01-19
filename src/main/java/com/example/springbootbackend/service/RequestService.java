package com.example.springbootbackend.service;

import com.example.springbootbackend.model.Request;
import com.example.springbootbackend.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {
    private RequestRepository requestRepository;

    @Autowired
    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    public Request save(Request request) {
        return requestRepository.save(request);
    }
}
