package com.nnk.springboot.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RuleNameServiceTest {

  @InjectMocks
  RuleNameService ruleNameService;
  @Mock
  RuleNameRepository ruleNameRepository;

  @BeforeEach
  void init_mocks() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  @DisplayName("When calling service findAll, then verify that the repository is called")
  void findAll() {
    // ACT
    ruleNameService.findAll();
    // ASSERT
    verify(ruleNameRepository, times(1)).findAll();
  }

  @Test
  @DisplayName("When saving a null object, then assert that an IllegalArgumentException is thrown")
  void saveNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ruleNameService.save(null));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void saveValid() {
    // ACT
    ruleNameService.save(RuleName.builder().name("good").build());
    // ASSERT
    verify(ruleNameRepository, times(1)).save(any());
  }

  @Test
  @DisplayName("When finding a null object, then assert that an IllegalArgumentException is thrown")
  void findByIdNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ruleNameService.findById(99));
  }

  @Test
  @DisplayName("When finding a valid object, then assert that the object is returned")
  void findByIdValid() {
    // ARRANGE
    when(ruleNameRepository.findById(98))
        .thenReturn(Optional.of(RuleName.builder().id(98).name("good").build()));
    // ACT
    RuleName ruleName = ruleNameService.findById(98);
    // ASSERT
    assertThat(ruleName.getName()).isEqualTo("good");
  }

  @Test
  @DisplayName("When deleting a null object, then assert that an IllegalArgumentException is thrown")
  void deleteNull() {
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ruleNameService.delete(99));
  }

  @Test
  @DisplayName("When saving a valid object, then assert that the repository is called")
  void deleteValid() {
    // ARRANGE
    when(ruleNameRepository.findById(98)).thenReturn(Optional.of(RuleName.builder().id(98).build()));
    // ACT
    ruleNameService.delete(98);
    // ASSERT
    verify(ruleNameRepository, times(1)).deleteById(98);
  }

  @Test
  @DisplayName("When updating a null object, then assert that an IllegalArgumentException is thrown")
  void updateNull() {
    // ARRANGE
    RuleName ruleName = RuleName.builder().id(99).build();
    Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
        .isThrownBy(() -> ruleNameService.update(ruleName, 99));
  }

  @Test
  @DisplayName("When updating a valid object, then assert that the repository is called")
  void updateValid() {
    // ARRANGE
    when(ruleNameRepository.findById(98)).thenReturn(Optional.of(RuleName.builder().id(98).name("bad").build()));
    RuleName ruleName = RuleName.builder().name("good").build();
    // ACT
    ruleNameService.update(ruleName, 98);
    // ASSERT
    verify(ruleNameRepository, times(1)).save(RuleName.builder().id(98).name("good").build());
  }
}
