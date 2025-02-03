package com.greta.myproject.controllers;


import com.greta.myproject.daos.CategoryDao;
import com.greta.myproject.entities.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryDao categoryDao;

    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategory() {
        return ResponseEntity.ok(categoryDao.findAll());
    }


    @GetMapping("/{category}")
    public ResponseEntity<Category> getUserByCategory_name(@PathVariable String category_name) {
        return ResponseEntity.ok(categoryDao.findByCategory_name(category_name));
    }


    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryDao.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }


    @PutMapping("/{category_name}")
    public ResponseEntity<Category> updateCategory(@PathVariable String category_name, @RequestBody Category category) {
        Category updatedCategory = categoryDao.update(category_name, category);
        return ResponseEntity.ok(updatedCategory);
    }



    @DeleteMapping("/{category_name}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String category_name) {
        if (categoryDao.delete(category_name)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

