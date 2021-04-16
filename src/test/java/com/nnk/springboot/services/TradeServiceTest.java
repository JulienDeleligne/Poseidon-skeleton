package com.nnk.springboot.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TradeServiceTest {

  @InjectMocks
  TradeService tradeService;
  @Mock
  TradeRepository tradeRepository;

  @BeforeEach
  void init_mocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("When calling service findAll, then verify that the repository is called")
  void findAll() {
    // ACT
    tradeService.findAll();
    // ASSERT
    verify(tradeRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("When saving a null object, then assert that an IllegalArgumentException is thrown")
  void saveNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> tradeService.save(null));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void saveValid() {
    // ACT
    tradeService.save(Trade.builder().account("good").build());
    // ASSERT
    verify(tradeRepository, times(1)).save(any());
  }

  @Test
  @DisplayName("When finding a null object, then assert that an IllegalArgumentException is thrown")
  void findByIdNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> tradeService.findById(99));
  }

  @Test
  @DisplayName("When finding a valid object, then assert that the object is returned")
  void findByIdValid() {
    // ARRANGE
    when(tradeRepository.findById(98))
        .thenReturn(Optional.of(Trade.builder().tradeId(98).account("good").build()));
    // ACT
    Trade trade = tradeService.findById(98);
    // ASSERT
    assertThat(trade.getAccount()).isEqualTo("good");
  }

  @Test
  @DisplayName("When deleting a null object, then assert that an IllegalArgumentException is thrown")
  void deleteNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> tradeService.delete(99));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void deleteValid() {
    // ARRANGE
    when(tradeRepository.findById(98)).thenReturn(Optional.of(Trade.builder().tradeId(98).build()));
    // ACT
    tradeService.delete(98);
    // ASSERT
    verify(tradeRepository, times(1)).deleteById(98);
  }

  @Test
  @DisplayName("When updating a null object, then assert that an IllegalArgumentException is thrown")
  void updateNull() {
    // ARRANGE
    Trade trade = Trade.builder().tradeId(99).build();
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> tradeService.update(trade, 99));
  }

  @Test
  @DisplayName("When updating a valid object, then assert that the repository is called")
  void updateValid() {
    // ARRANGE
    when(tradeRepository.findById(98)).thenReturn(Optional.of(Trade.builder().tradeId(98).account("bad").build()));
    Trade trade = Trade.builder().account("good").build();
    // ACT
    tradeService.update(trade, 98);
    // ASSERT
    verify(tradeRepository, times(1)).save(Trade.builder().tradeId(98).account("good").build());
  }
}
