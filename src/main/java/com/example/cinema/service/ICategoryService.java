package com.example.cinema.service;

import com.example.cinema.dtos.CategoryDTO;
import com.example.cinema.model.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO);

    Category getCategoryById(int id);

    List<Category> getAllCategory();

    Category updateCategory(int categoryId, CategoryDTO categoryDTO);

    void deleteCategory(int id);
}
