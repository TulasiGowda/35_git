package com.javaexpress.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.Product;
import com.javaexpress.exception.ResourceNotFoundException;
import com.javaexpress.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void createProducts(Product product) {
		log.info("ProductService :: createProducts {}", product);
		product.setIsStock(true);
		product.setBarCode(UUID.randomUUID().toString());
		productRepository.save(product);
		log.info("product created successfully in DB");
	}

	public Product fetchProductInfo(Long productId) {
		log.info("ProductService :: fetchProductInfo {}", productId);
		return productRepository.findById(productId)
			.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}
	
	public void updateProduct(Long productId, Product inputProduct) {
		log.info("ProductService :: updateProduct {} {}", productId, inputProduct);
		Product dbProduct = fetchProductInfo(productId);
		dbProduct.setName(inputProduct.getName());
		dbProduct.setPrice(inputProduct.getPrice());
		dbProduct.setQuantity(inputProduct.getQuantity());
		dbProduct.setDescription(inputProduct.getDescription());
		productRepository.save(dbProduct);
		log.info("Product updated successfully in DB");
	}
	
	public void deleteProduct(Long productId) {
		log.info("ProductService :: deleteProduct {}", productId);
		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
		}else {
			throw new RuntimeException("Product not found");
		}
		log.info("Product deleted from DB");
	}
}
