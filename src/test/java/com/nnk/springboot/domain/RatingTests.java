package com.nnk.springboot.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

  @Autowired
  private RatingRepository ratingRepository;

  @Test
  public void ratingTest() {
    Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

    // Save
    rating = ratingRepository.save(rating);
    assertThat(rating.getId()).isNotNull();
    assertThat(rating.getOrderNumber()).isEqualTo(10);

    // Update
    rating.setOrderNumber(20);
    rating = ratingRepository.save(rating);
    assertThat(rating.getOrderNumber()).isEqualTo(20);

    // Find
    List<Rating> listResult = ratingRepository.findAll();
    assertThat(listResult.size() > 0).isTrue();

    // Delete
    Integer id = rating.getId();
    ratingRepository.delete(rating);
    Optional<Rating> ratingList = ratingRepository.findById(id);
    assertThat(ratingList).isNotPresent();
  }
}
