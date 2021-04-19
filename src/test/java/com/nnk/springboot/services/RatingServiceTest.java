package com.nnk.springboot.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RatingServiceTest {

  private static final int NINETY_EIGHT = 98;
  private static final int NINETY_NINE = 99;

  @InjectMocks
  RatingService ratingService;
  @Mock
  RatingRepository ratingRepository;

  @BeforeEach
  void init_mocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("When calling service findAll, then verify that the repository is called")
  void findAll() {
    // ACT
    ratingService.findAll();
    // ASSERT
    verify(ratingRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("When saving a null object, then assert that an IllegalArgumentException is thrown")
  void saveNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ratingService.save(null));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void saveValid() {
    // ACT
    ratingService.save(Rating.builder().moodysRating("good").build());
    // ASSERT
    verify(ratingRepository, times(1)).save(any());
  }

  @Test
  @DisplayName("When finding a null object, then assert that an IllegalArgumentException is thrown")
  void findByIdNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ratingService.findById(NINETY_NINE));
  }

  @Test
  @DisplayName("When finding a valid object, then assert that the object is returned")
  void findByIdValid() {
    // ARRANGE
    when(ratingRepository.findById(NINETY_EIGHT))
        .thenReturn(Optional.of(Rating.builder().id(NINETY_EIGHT).moodysRating("good").build()));
    // ACT
    Rating rating = ratingService.findById(NINETY_EIGHT);
    // ASSERT
    assertThat(rating.getMoodysRating()).isEqualTo("good");
  }

  @Test
  @DisplayName("When deleting a null object, then assert that an IllegalArgumentException is thrown")
  void deleteNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ratingService.delete(NINETY_NINE));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void deleteValid() {
    // ARRANGE
    when(ratingRepository.findById(NINETY_EIGHT)).thenReturn(Optional.of(Rating.builder().id(NINETY_EIGHT).build()));
    // ACT
    ratingService.delete(NINETY_EIGHT);
    // ASSERT
    verify(ratingRepository, times(1)).deleteById(NINETY_EIGHT);
  }

  @Test
  @DisplayName("When updating a null object, then assert that an IllegalArgumentException is thrown")
  void updateNull() {
    // ARRANGE
    Rating rating = Rating.builder().id(NINETY_NINE).build();
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ratingService.update(rating, NINETY_NINE));
  }

  @Test
  @DisplayName("When updating a valid object, then assert that the repository is called")
  void updateValid() {
    // ARRANGE
    when(ratingRepository.findById(NINETY_EIGHT))
        .thenReturn(Optional.of(Rating.builder().id(NINETY_EIGHT).moodysRating("bad").build()));
    Rating rating = Rating.builder().moodysRating("good").build();
    // ACT
    ratingService.update(rating, NINETY_EIGHT);
    // ASSERT
    verify(ratingRepository, times(1)).save(Rating.builder().id(NINETY_EIGHT).moodysRating("good").build());
  }
}
