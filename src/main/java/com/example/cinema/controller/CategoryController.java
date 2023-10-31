package com.example.cinema.controller;


import com.example.cinema.dtos.CategoryDTO;
import com.example.cinema.model.Category;
import com.example.cinema.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result ){
        if(result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        Category category = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(category);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCategories(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit,
            BindingResult result
    ){
        if(result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable int id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable int id,
                                            @Valid @RequestBody CategoryDTO categoryDTO,
                                            BindingResult result
    ){
        if(result.hasErrors()){
            List<String> errorMessage = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessage);
        }
        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Deleted");
    }
}
