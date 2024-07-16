package com.javaexpress.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.Category;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public void createCategory(Category category) {
		log.info("CategoryService :: createCategory {}", category);
		categoryRepository.save(category);
		log.info("Category saved successfully in DB");
	}

	public List<Category> fetchAllCategory() {
		log.info("CategoryService :: fetchAllCategory");
		List<Category> categories = categoryRepository.findAll();
		List<Category> result = categories.stream().sorted(Comparator.comparing(Category::getName)).toList();
		return result;
	}

	public Category findByCategoryId(Long categoryId) {
		log.info("CategoryService :: findByCategoryId {}", categoryId);
		return categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
	}

	public void updateCategory(Long categoryId, Category inputCategory) {
		log.info("CategoryService :: updateCategory {} {}", categoryId, inputCategory);
		Category dbCategory = findByCategoryId(categoryId);
		dbCategory.setName(inputCategory.getName());
		categoryRepository.save(dbCategory);
		log.info("Category updated sucessfully");
	}

	public void deleteCategory(Long categoryId) {
		log.info("CategoryService :: deleteCategory {}", categoryId);
		if (categoryRepository.existsById(categoryId)) {
			categoryRepository.deleteById(categoryId);
		} else {
			throw new RuntimeException("Category Not found");
		}
		log.info("Category deleted successfully");
	}
}
