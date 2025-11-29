package com.organicbazar.category.service;

import com.organicbazar.category.dto.CategoryDto;
import com.organicbazar.category.entity.Category;

import java.util.List;

public interface CategoryService {
    Category create(CategoryDto dto);
    List<Category> getAll();
    Category getById(Long id);
    void delete(Long id);
}
