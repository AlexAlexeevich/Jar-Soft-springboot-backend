package com.example.springbootbackend.service;

import com.example.springbootbackend.model.Category;
import com.example.springbootbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(int id) {
        Optional<Category> categoryData = categoryRepository.findById(id);
        return categoryData;
    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}
