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
    curvePointService.save(CurvePoint.builder().curveId(12).build());
    // ASSERT
    verify(curvePointRepository, times(1)).save(any());
  }

  @Test
  @DisplayName("When finding a null object, then assert that an IllegalArgumentException is thrown")
  void findByIdNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> curvePointService.findById(99));
  }

  @Test
  @DisplayName("When finding a valid object, then assert that the object is returned")
  void findByIdValid() {
    // ARRANGE
    when(curvePointRepository.findById(98))
        .thenReturn(Optional.of(CurvePoint.builder().id(98).curveId(12).build()));
    // ACT
    CurvePoint curvePoint = curvePointService.findById(98);
    // ASSERT
    assertThat(curvePoint.getCurveId()).isEqualTo(12);
  }

  @Test
  @DisplayName("When deleting a null object, then assert that an IllegalArgumentException is thrown")
  void deleteNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> curvePointService.delete(99));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void deleteValid() {
    // ARRANGE
    when(curvePointRepository.findById(98)).thenReturn(Optional.of(CurvePoint.builder().id(98).build()));
    // ACT
    curvePointService.delete(98);
    // ASSERT
    verify(curvePointRepository, times(1)).deleteById(98);
  }

  @Test
  @DisplayName("When updating a null object, then assert that an IllegalArgumentException is thrown")
  void updateNull() {
    // ARRANGE
    CurvePoint curvePoint = CurvePoint.builder().id(99).curveId(12).build();
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> curvePointService.update(curvePoint, 99));
  }

  @Test
  @DisplayName("When updating a valid object, then assert that the repository is called")
  void updateValid() {
    // ARRANGE
    when(curvePointRepository.findById(98)).thenReturn(Optional.of(CurvePoint.builder().id(98).curveId(24).build()));
    CurvePoint curvePoint = CurvePoint.builder().curveId(12).build();
    // ACT
    curvePointService.update(curvePoint, 98);
    // ASSERT
    verify(curvePointRepository, times(1)).save(CurvePoint.builder().id(98).curveId(12).build());
  }
}

