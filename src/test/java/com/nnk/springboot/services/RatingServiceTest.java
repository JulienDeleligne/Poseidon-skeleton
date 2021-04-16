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
        .isThrownBy(() -> ratingService.findById(99));
  }

  @Test
  @DisplayName("When finding a valid object, then assert that the object is returned")
  void findByIdValid() {
    // ARRANGE
    when(ratingRepository.findById(98))
        .thenReturn(Optional.of(Rating.builder().id(98).moodysRating("good").build()));
    // ACT
    Rating rating = ratingService.findById(98);
    // ASSERT
    assertThat(rating.getMoodysRating()).isEqualTo("good");
  }

  @Test
  @DisplayName("When deleting a null object, then assert that an IllegalArgumentException is thrown")
  void deleteNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ratingService.delete(99));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void deleteValid() {
    // ARRANGE
    when(ratingRepository.findById(98)).thenReturn(Optional.of(Rating.builder().id(98).build()));
    // ACT
    ratingService.delete(98);
    // ASSERT
    verify(ratingRepository, times(1)).deleteById(98);
  }

  @Test
  @DisplayName("When updating a null object, then assert that an IllegalArgumentException is thrown")
  void updateNull() {
    // ARRANGE
    Rating rating = Rating.builder().id(99).build();
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ratingService.update(rating, 99));
  }

  @Test
  @DisplayName("When updating a valid object, then assert that the repository is called")
  void updateValid() {
    // ARRANGE
    when(ratingRepository.findById(98)).thenReturn(Optional.of(Rating.builder().id(98).moodysRating("bad").build()));
    Rating rating = Rating.builder().moodysRating("good").build();
    // ACT
    ratingService.update(rating, 98);
    // ASSERT
    verify(ratingRepository, times(1)).save(Rating.builder().id(98).moodysRating("good").build());
  }
}
