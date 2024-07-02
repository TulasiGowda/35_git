package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.entities.Review;
import com.javaexpress.repository.ReviewRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public void createReview(Review review) {
		log.info("ReviewService :: createReview {}", review);
		reviewRepository.save(review);
		log.info("Review created successfully to DB");
	}

	public Review fetchByReviewId(long reviewId) {
		log.info("ReviewService :: fetchByReviewId {}", reviewId);
		return reviewRepository.findById(reviewId)
		.orElseThrow(() -> new RuntimeException());
	}
	
	public void updateReview(long reviewId, Review review) {
		log.info("ReviewService :: updateReview {}", reviewId);
		Review dbRev =  fetchByReviewId(reviewId);
		dbRev.setComment(review.getComment());
		dbRev.setProduct(review.getProduct());
		reviewRepository.save(dbRev);
		log.info("Review updated successfully to DB");
	}

	public Review fetchReview(long reviewId) {
		log.info("ReviewService :: fetchReview {}", reviewId);
		return reviewRepository.findById(reviewId)
				.orElseThrow(() -> new RuntimeException());
	}

	public void deleteReview(long reviewId) {
		log.info("ReviewService :: deleteReview {}", reviewId);
		if(reviewRepository.existsById(reviewId)) {
			reviewRepository.deleteById(reviewId);
		}else {
			throw new RuntimeException("Review not found");
		}
		log.info("review info is deleted using reviewID ");
	}

}
