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

import com.javaexpress.entities.Product;
import com.javaexpress.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public void createProduct(@RequestBody Product product) {
		log.info("ProductController :: createProduct {}", product);
		productService.createProducts(product);
		log.info("Product saved succesfully in DB");
	}
	
	@GetMapping("{pId}")
	public Product fetchProduct(@PathVariable(name="pId") Long productId) {
		log.info("ProductController :: fetchProduct {}", productId);
		return productService.fetchProductInfo(productId);
	}
	
	@PutMapping("{productId}")
	public void updateProducts(@PathVariable Long productId, @RequestBody Product product) {
		log.info("ProductController :: updateProducts {} {}", productId, product);
		productService.updateProduct(productId, product);
		log.info("Product updated sucessfully to DB");
	}
	
	@DeleteMapping("{productId}")
	public void deleteProducts(@PathVariable Long productId) {
		log.info("ProductController :: deleteProducts {}", productId);
		productService.deleteProduct(productId);
		log.info("Product deleted successfully from DB");
	}
}
