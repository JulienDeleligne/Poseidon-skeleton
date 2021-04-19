package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class RatingService {

  @Autowired
  private RatingRepository ratingRepository;

  public List<Rating> findAll() {
    return ratingRepository.findAll();
  }

  public void save(Rating rating) {
    Assert.notNull(rating, "Rating must not be null");
    ratingRepository.save(rating);
  }

  public Rating findById(Integer id) {
    Assert.notNull(id, "id must not be null");
    return ratingRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id:" + id));
  }

  public void delete(Integer id) {
    ratingRepository.deleteById(findById(id).getId());
  }

  public void update(Rating ratingToSave, Integer id) {
    Assert.notNull(ratingToSave, "RatingToSave must not be null");
    Rating rating = findById(id);
    rating.setMoodysRating(ratingToSave.getMoodysRating());
    rating.setSandPRating(ratingToSave.getSandPRating());
    rating.setFitchRating(ratingToSave.getFitchRating());
    rating.setOrderNumber(ratingToSave.getOrderNumber());
    save(rating);
  }
}
