package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaexpress.entities.ProductRel;

@Repository
public interface ProductRepository extends JpaRepository<ProductRel, Long>{

}
