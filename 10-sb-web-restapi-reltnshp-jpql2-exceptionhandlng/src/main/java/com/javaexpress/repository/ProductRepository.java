package com.javaexpress.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javaexpress.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

//	native queries (SQL) we can execute directly in db
//	JPQL queries - it will support all databases means sql db
//	JPQL - follow table Name as class Name and column name means variable name
	
	@Query("select p from Product p INNER JOIN p.category c Where c.name=:categoryName")
	List<Product> fetchProductByCategoryName(String categoryName);
	
	List<Product> findByCategoryName(String categoryName);

//	JPQL
	@Query("select p from Product p where p.barCode=:barCode")
	Product fetchProductUsingJPQL(String barCode);
	
//	DSL
	Product findByBarCode(String barCode);
	
//	Native Query
	@Query(value = "select * from product p where p.bar_code=:barCode", nativeQuery = true)
	Product fetchProductUsingNative(String barCode);
	
//	1st priority - DSL
//	2nd priority - JPQL
//	3rd priority - Native Query
}
