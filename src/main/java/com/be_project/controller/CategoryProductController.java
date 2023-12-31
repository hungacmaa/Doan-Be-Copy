package com.be_project.controller;

import com.be_project.entity.Account;
import com.be_project.entity.CategoryProduct;
import com.be_project.service.ICategoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryProductController {
    @Autowired
    private ICategoryProductService categoryProductService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(categoryProductService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createProductCategory(@RequestBody CategoryProduct categoryProduct) {
        try {
            return ResponseEntity.ok(categoryProductService.createProductCategory(categoryProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{productCategoryId}")
    public ResponseEntity<?> editProductCategory(@RequestBody CategoryProduct categoryProduct, @PathVariable long productCategoryId) {
        try {

            return ResponseEntity.ok(categoryProductService.editProductCategory(productCategoryId, categoryProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{productCategoryId}")
    public ResponseEntity<?> deleteProductCategory(@PathVariable long productCategoryId) {
        try {
            categoryProductService.deleteProductCategory(productCategoryId);
            return ResponseEntity.ok("Success");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
