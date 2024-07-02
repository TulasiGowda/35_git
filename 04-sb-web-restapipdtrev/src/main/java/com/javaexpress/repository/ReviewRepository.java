package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaexpress.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{

}
