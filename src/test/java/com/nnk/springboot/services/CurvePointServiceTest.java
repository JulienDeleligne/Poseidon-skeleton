package com.nnk.springboot.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CurvePointServiceTest {

  private static final int TWELVE = 12;
  private static final int NINETY_EIGHT = 98;
  private static final int NINETY_NINE = 99;

  @InjectMocks
  CurvePointService curvePointService;
  @Mock
  CurvePointRepository curvePointRepository;

  @BeforeEach
  void init_mocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("When calling service findAll, then verify that the repository is called")
  void findAll() {
    // ACT
    curvePointService.findAll();
    // ASSERT
    verify(curvePointRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("When saving a null object, then assert that an IllegalArgumentException is thrown")
  void saveNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> curvePointService.save(null));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void saveValid() {
    // ACT
    curvePointService.save(CurvePoint.builder().curveId(TWELVE).build());
    // ASSERT
    verify(curvePointRepository, times(1)).save(any());
  }

  @Test
  @DisplayName("When finding a null object, then assert that an IllegalArgumentException is thrown")
  void findByIdNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> curvePointService.findById(NINETY_NINE));
  }

  @Test
  @DisplayName("When finding a valid object, then assert that the object is returned")
  void findByIdValid() {
    // ARRANGE
    when(curvePointRepository.findById(NINETY_EIGHT))
        .thenReturn(Optional.of(CurvePoint.builder().id(NINETY_EIGHT).curveId(TWELVE).build()));
    // ACT
    CurvePoint curvePoint = curvePointService.findById(NINETY_EIGHT);
    // ASSERT
    assertThat(curvePoint.getCurveId()).isEqualTo(TWELVE);
  }

  @Test
  @DisplayName("When deleting a null object, then assert that an IllegalArgumentException is thrown")
  void deleteNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> curvePointService.delete(NINETY_NINE));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void deleteValid() {
    // ARRANGE
    when(curvePointRepository.findById(NINETY_EIGHT))
        .thenReturn(Optional.of(CurvePoint.builder().id(NINETY_EIGHT).build()));
    // ACT
    curvePointService.delete(NINETY_EIGHT);
    // ASSERT
    verify(curvePointRepository, times(1)).deleteById(NINETY_EIGHT);
  }

  @Test
  @DisplayName("When updating a null object, then assert that an IllegalArgumentException is thrown")
  void updateNull() {
    // ARRANGE
    CurvePoint curvePoint = CurvePoint.builder().id(NINETY_NINE).curveId(TWELVE).build();
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> curvePointService.update(curvePoint, NINETY_NINE));
  }

  @Test
  @DisplayName("When updating a valid object, then assert that the repository is called")
  void updateValid() {
    // ARRANGE
    when(curvePointRepository.findById(NINETY_EIGHT))
        .thenReturn(Optional.of(CurvePoint.builder().id(NINETY_EIGHT).curveId(24).build()));
    CurvePoint curvePoint = CurvePoint.builder().curveId(TWELVE).build();
    // ACT
    curvePointService.update(curvePoint, NINETY_EIGHT);
    // ASSERT
    verify(curvePointRepository, times(1)).save(CurvePoint.builder().id(NINETY_EIGHT).curveId(TWELVE).build());
  }
}

