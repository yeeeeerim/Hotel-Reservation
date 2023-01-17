package com.example.demo.service.impl;

import com.example.demo.domain.Review;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public void write(Review review){
        reviewRepository.save(review);
    }
    public Review healthreview(Integer id){
        return reviewRepository.findById(id).get();
    }

}
