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

import com.javaexpress.entities.Review;
import com.javaexpress.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/review")
@Slf4j
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping
	public void createReview(@RequestBody Review review) {
		log.info("ReviewController :: createReview {}", review);
		reviewService.createReview(review);
		log.info(" review request send to service ");
	}
	
	@PutMapping("{rId}")
	public void updateReview(@PathVariable(name="rId") long reviewId, @RequestBody Review review) {
		log.info("ReviewController :: updateReview {}", reviewId);
		 reviewService.updateReview(reviewId, review);
		 log.info(" update request send to service ");
	}
	
	@GetMapping("{rId}")
	public Review fetchReview(@PathVariable(name="rId") long reviewId) {
		log.info("ReviewController :: fetchReview {}", reviewId);
		return reviewService.fetchReview(reviewId);
	}
	
	@DeleteMapping("{reviewId}")
	public void deleteReview(@PathVariable long reviewId) {
		log.info("ReviewController :: deleteReview {}", reviewId);
		reviewService.deleteReview(reviewId);
		log.info("delete request send to service ");
	}
}
