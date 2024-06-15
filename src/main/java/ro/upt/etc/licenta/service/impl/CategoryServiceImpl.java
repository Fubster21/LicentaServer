package ro.upt.etc.licenta.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ro.upt.etc.licenta.repository.CategoryRepository;
import ro.upt.etc.licenta.repository.dto.CategoryDto;
import ro.upt.etc.licenta.repository.entity.Category;
import ro.upt.etc.licenta.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getAllCategories() {
        var catList = categoryRepository.findAll();
        if (catList.isEmpty()) { return List.of(); }
        return catList.stream()
                .map(cat -> modelMapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        var cat = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public CategoryDto createCategory(CategoryDto category) {
        Category cat = Category.builder().name(category.getName()).build();
        return modelMapper.map(categoryRepository.save(cat), CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        var cat = categoryRepository.findById(Long.valueOf(categoryDto.getId())).orElseThrow();
        cat.setName(categoryDto.getName());
        return modelMapper.map(categoryRepository.save(cat), CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
