package com.nnk.springboot.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BidListServiceTest {

  @InjectMocks
  BidListService bidListService;
  @Mock
  BidListRepository bidListRepository;

  @BeforeEach
  void init_mocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("When calling service findAll, then verify that the repository is called")
  void findAll() {
    // ACT
    bidListService.findAll();
    // ASSERT
    verify(bidListRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("When saving a null object, then assert that an IllegalArgumentException is thrown")
  void saveNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> bidListService.save(null));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void saveValid() {
    // ACT
    bidListService.save(BidList.builder().account("account").type("type").build());
    // ASSERT
    verify(bidListRepository, times(1)).save(any());
  }

  @Test
  @DisplayName("When finding a null object, then assert that an IllegalArgumentException is thrown")
  void findByIdNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> bidListService.findById(99));
  }

  @Test
  @DisplayName("When finding a valid object, then assert that the object is returned")
  void findByIdValid() {
    // ARRANGE
    when(bidListRepository.findById(98))
        .thenReturn(Optional.of(BidList.builder().account("account").type("type").build()));
    // ACT
    BidList bidList = bidListService.findById(98);
    // ASSERT
    assertThat(bidList.getAccount()).isEqualTo("account");
    assertThat(bidList.getType()).isEqualTo("type");
  }

  @Test
  @DisplayName("When deleting a null object, then assert that an IllegalArgumentException is thrown")
  void deleteNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> bidListService.delete(99));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void deleteValid() {
    // ARRANGE
    when(bidListRepository.findById(98)).thenReturn(Optional.of(BidList.builder().bidListId(98).build()));
    // ACT
    bidListService.delete(98);
    // ASSERT
    verify(bidListRepository, times(1)).deleteById(98);
  }

  @Test
  @DisplayName("When updating a null object, then assert that an IllegalArgumentException is thrown")
  void updateNull() {
    // ARRANGE
    BidList bidList = BidList.builder().account("account").type("type").build();
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> bidListService.update(bidList, 99));
  }

  @Test
  @DisplayName("When updating a valid object, then assert that the repository is called")
  void updateValid() {
    // ARRANGE
    when(bidListRepository.findById(98)).thenReturn(Optional.of(BidList.builder().bidListId(98).build()));
    BidList bidList = BidList.builder().account("account").type("type").bidQuantity(12.00).build();
    // ACT
    bidListService.update(bidList, 98);
    // ASSERT
    verify(bidListRepository, times(1)).save(BidList.builder().bidListId(98).bidQuantity(12.00).build());
  }
}
