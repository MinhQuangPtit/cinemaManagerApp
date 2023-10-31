package com.example.cinema.service;

import com.example.cinema.dtos.CategoryDTO;
import com.example.cinema.exception.CustomException;
import com.example.cinema.model.Category;
import com.example.cinema.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category getCategoryById(int id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new CustomException("Không tìm thấy danh mục với Id " + id);
        }
        return category.get();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(int categoryId, CategoryDTO categoryDTO) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new CustomException("Không tìm thấy danh mục với Id " + categoryId);
        }
        modelMapper.typeMap(CategoryDTO.class,Category.class)
                .addMappings(mapper->mapper.skip(Category::setId));
        Category newCategory = new Category();
        newCategory.setId(categoryId);
        modelMapper.map(categoryDTO,newCategory);
        return categoryRepository.save(newCategory);
    }

    @Override
    public void deleteCategory(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new CustomException("Không tìm thấy danh mục với Id " + id);
        }
        categoryRepository.deleteById(id);
    }
}
