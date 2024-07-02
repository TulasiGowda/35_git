package com.javaexpress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.Category;
import com.javaexpress.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void createCategory(Category category) {
		log.info("CategoryService :: createCategory");
		categoryRepository.save(category);
		log.info("Category is saved successfully to DB");
	}
	
	public Category findByCategoryId(Long categoryId) {
		log.info("CategoryService :: findByUserId");
		return categoryRepository.findById(categoryId)
			.orElseThrow(() -> new RuntimeException("Category not found"));
		
	}
	
	public void updateCategory(Long categoryId, Category inputCategory) {
		log.info("CategoryService :: updateCategory");
		Category dbData = findByCategoryId(categoryId);
		dbData.setName(inputCategory.getName());
		categoryRepository.save(dbData);
		log.info("Category Updated successfully to DB");
	}
	
	public void deleteCategory(Long categoryId) {
		log.info("CategoryService :: deleteCategory");
		Category dbData = findByCategoryId(categoryId);
		categoryRepository.delete(dbData);
		log.info("category deleted based on catId");
	}
	
}
