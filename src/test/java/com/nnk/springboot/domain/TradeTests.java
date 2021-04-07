package com.nnk.springboot.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeTests {

  @Autowired
  private TradeRepository tradeRepository;

  @Test
  public void tradeTest() {
    Trade trade = new Trade("Trade Account", "Type");

    // Save
    trade = tradeRepository.save(trade);
    assertThat(trade.getTradeId()).isNotNull();
    assertThat(trade.getAccount()).isEqualTo("Trade Account");

    // Update
    trade.setAccount("Trade Account Update");
    trade = tradeRepository.save(trade);
    assertThat(trade.getAccount()).isEqualTo("Trade Account Update");

    // Find
    List<Trade> listResult = tradeRepository.findAll();
    assertThat(listResult.size() > 0).isTrue();

    // Delete
    Integer id = trade.getTradeId();
    tradeRepository.delete(trade);
    Optional<Trade> tradeList = tradeRepository.findById(id);
    assertThat(tradeList).isNotPresent();
  }
}
