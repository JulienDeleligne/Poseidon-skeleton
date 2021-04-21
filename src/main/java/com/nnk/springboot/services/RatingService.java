package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Rating service.
 */

@Service
@Transactional
public class RatingService {

  @Autowired
  private RatingRepository ratingRepository;

  /**
   * Find all Rating.
   *
   * @return List of Rating
   */
  public List<Rating> findAll() {
    return ratingRepository.findAll();
  }

  /**
   * Save the Rating.
   * First check that the rating is not null. If it's null, throw a IllegalArgumentException
   *
   * @param rating Rating to save
   */
  public void save(Rating rating) {
    Assert.notNull(rating, "Rating must not be null");
    ratingRepository.save(rating);
  }

  /**
   * Find the Rating by id.
   * First check that the id is not null. If it's null, throw a IllegalArgumentException
   * Then search the Rating. If it doesn't exist, throw a IllegalArgumentException
   *
   * @param id The Rating id
   * @return Rating found
   */
  public Rating findById(Integer id) {
    Assert.notNull(id, "id must not be null");
    return ratingRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid Rating Id:" + id));
  }

  /**
   * Delete the Rating by id
   *
   * @param id The Rating id
   */
  public void delete(Integer id) {
    ratingRepository.deleteById(findById(id).getId());
  }

  /**
   * check that the ratingToSave is not null, then update the Rating and save it.
   *
   * @param ratingToSave The Rating to save
   * @param id The Rating id to update
   */
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
