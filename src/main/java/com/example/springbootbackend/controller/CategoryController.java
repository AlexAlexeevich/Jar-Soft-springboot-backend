package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Category;
import com.example.springbootbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categories = new ArrayList<>();
            categories.addAll(categoryRepository.findAll());
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        try {
            Category categoryTemp = categoryRepository
                    .save(new Category(category.getName(), category.getReqName(), false));
            return new ResponseEntity<>(categoryTemp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
        Optional<Category> categoryData = categoryRepository.findById(id);
        if (categoryData.isPresent()) {
            Category categoryTemp = categoryData.get();
            categoryTemp.setName(category.getName());
            categoryTemp.setReqName(category.getReqName());
            categoryTemp.setDeleted(category.isDeleted());
            return new ResponseEntity<>(categoryRepository.save(categoryTemp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<HttpStatus> deleteCategories(@PathVariable("id") int id) {
        try {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
