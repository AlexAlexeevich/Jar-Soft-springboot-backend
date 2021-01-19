package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Category;
import com.example.springbootbackend.service.CategoryService;
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

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<Category> categories = new ArrayList<>();
            categories.addAll(categoryService.findAll());
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
            Category categoryTemp = categoryService
                    .save(new Category(category.getName(), category.getReqName(), false));
            return new ResponseEntity<>(categoryTemp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
        Optional<Category> categoryData = categoryService.findById(id);
        if (categoryData.isPresent()) {
            Category categoryTemp = categoryData.get();
            categoryTemp.setName(category.getName());
            categoryTemp.setReqName(category.getReqName());
            categoryTemp.setDeleted(category.isDeleted());
            return new ResponseEntity<>(categoryService.save(categoryTemp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<HttpStatus> deleteCategories(@PathVariable("id") int id) {
        try {
            categoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
