package ro.upt.etc.licenta.service;


import ro.upt.etc.licenta.repository.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Long id);
    CategoryDto createCategory(CategoryDto category);
    CategoryDto updateCategory(CategoryDto categoryDto);
    void deleteCategory(Long id);
}
