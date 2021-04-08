package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

  @Autowired
  private RatingRepository ratingRepository;

  public List<Rating> findAll() {
    return ratingRepository.findAll();
  }

  public void save(Rating rating) {
    ratingRepository.save(rating);
  }

  public Rating findById(Integer id) {
    return ratingRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id:" + id));
  }

  public void delete(Integer id) {
    ratingRepository.deleteById(id);
  }
}
