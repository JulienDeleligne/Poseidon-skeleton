package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class TradeService {

  @Autowired
  private TradeRepository tradeRepository;

  public List<Trade> findAll() {
    return tradeRepository.findAll();
  }

  public void save(Trade trade) {
    Assert.notNull(trade, "Trade must not be null");
    tradeRepository.save(trade);
  }

  public Trade findById(Integer id) {
    return tradeRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid Trade Id:" + id));
  }

  public void delete(Integer id) {
    tradeRepository.deleteById(findById(id).getTradeId());
  }

  public void update(Trade tradeToSave, Integer id) {
    Trade trade = findById(id);
    trade.setAccount(tradeToSave.getAccount());
    trade.setType(tradeToSave.getType());
    trade.setBuyQuantity(tradeToSave.getBuyQuantity());
    save(trade);
  }
}
