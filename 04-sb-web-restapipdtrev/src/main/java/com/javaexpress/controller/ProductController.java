package com.javaexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.entities.ProductRel;
import com.javaexpress.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public void createProduct(@RequestBody ProductRel product) {
		log.info("ProductController :: createProduct {}", product);
		productService.createProduct(product);
		log.info("createProduct() data send service ");
	}
	
	@GetMapping("{pId}")
	public ProductRel fetchProduct(@PathVariable(name="pId") long productId) {
		log.info("ProductController :: fetchProduct {}", productId);
		return productService.fetchProductInfo(productId);
	}
	
	@PutMapping("{productId}")
	public void updateProduct(@PathVariable long productId, @RequestBody ProductRel product) {
		log.info("ProductController :: updateProduct {}", productId, product);
		 productService.updateProduct(productId, product);
		 log.info("update product() data send to service");
	}
	
	@DeleteMapping("{productId}")
	public void deleteProduct(@PathVariable long productId) {
		log.info("ProductController :: deleteProduct");
		productService.deleteProduct(productId);
		log.info(" delete product() info send to service");
	}
}
