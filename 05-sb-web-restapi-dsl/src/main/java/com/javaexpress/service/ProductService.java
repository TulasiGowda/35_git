package com.javaexpress.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.Product;
import com.javaexpress.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public void createProduct(Product product) {
		log.info("ProductService :: createProduct {}", product);
		product.setStock(true);
		product.setBarCode(UUID.randomUUID().toString());
		productRepository.save(product);
		log.info("product data successfully saved to DB");
	}

	public Product fetchProductInfo(long productId) {
		log.info("ProductService :: fetchProductInfo {}", productId);
		return productRepository.findById(productId)
					.orElseThrow(() -> new RuntimeException());
	}

	public Product findproductById(long productId) {
		log.info("ProductService :: findproductById {}" , productId);
		return productRepository.findById(productId)
					.orElseThrow(() -> new RuntimeException());
	}
	
	public void updateProduct(long productId, Product inputProduct) {
		log.info("ProductService :: updateProduct {}" , productId, inputProduct);
		Product dbData = findproductById(productId);
		dbData.setName(inputProduct.getName());
		dbData.setPrice(inputProduct.getPrice());
		dbData.setDescription(inputProduct.getDescription());
		dbData.setQuantity(inputProduct.getQuantity());
		dbData.setCategory(inputProduct.getCategory());
		productRepository.save(dbData);
		log.info("product data updated successfully in DB");
		
	}

	public void deleteProduct(long productId) {
		log.info("ProductService :: deleteProduct", productId);
		if(productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
		}
		else {
			throw new RuntimeException("Product not found");
		}
		log.info("delete product using productId ");
		
	}
}
