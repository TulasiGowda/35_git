package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.entities.Category;
import com.javaexpress.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/category")
@Slf4j
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public void createCategory(@RequestBody Category category) {
		log.info("CategoryController :: createCategory");
		categoryService.createCategory(category);
		log.info("Category created successfully");
	}
	
	@GetMapping("{categoryId}")
	public Category getCategoryById(@PathVariable Long categoryId) {
		log.info("CategoryController :: fetchAllCategory");
		return categoryService.findByCategoryId(categoryId);
	}
	
	@PutMapping("{categoryId}")
	public void updateCategory(@PathVariable Long categoryId, @RequestBody Category category) {
		log.info("CategoryController :: updateCategory {} {}", categoryId, category);
		categoryService.updateCategory(categoryId, category);
		log.info("Category updated in DB");
	}
	
	@DeleteMapping("{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		log.info("CategoryController :: deleteCategory {}", categoryId);
		categoryService.deleteCategory(categoryId);
		log.info("category deleted in DB");
	}
}
