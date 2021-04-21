package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Trade service.
 */
@Service
@Transactional
public class TradeService {

  @Autowired
  private TradeRepository tradeRepository;

  /**
   * Find all Trade.
   *
   * @return List of Trade
   */
  public List<Trade> findAll() {
    return tradeRepository.findAll();
  }

  /**
   * Save the Trade.
   * First check that the trade is not null. If it's null, throw a IllegalArgumentException
   *
   * @param trade Trade to save
   */
  public void save(Trade trade) {
    Assert.notNull(trade, "Trade must not be null");
    tradeRepository.save(trade);
  }

  /**
   * Find the Trade by id.
   * First check that the id is not null. If it's null, throw a IllegalArgumentException
   * Then search the Trade. If it doesn't exist, throw a IllegalArgumentException
   *
   * @param id The Trade id
   * @return Trade found
   */
  public Trade findById(Integer id) {
    Assert.notNull(id, "id must not be null");
    return tradeRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid Trade Id:" + id));
  }

  /**
   * Delete the Trade by id
   *
   * @param id The Trade id
   */
  public void delete(Integer id) {
    tradeRepository.deleteById(findById(id).getTradeId());
  }

  /**
   * check that the tradeToSave is not null, then update the Trade and save it.
   *
   * @param tradeToSave The Trade to save
   * @param id The Trade id to update
   */
  public void update(Trade tradeToSave, Integer id) {
    Assert.notNull(tradeToSave, "TradeToSave must not be null");
    Trade trade = findById(id);
    trade.setAccount(tradeToSave.getAccount());
    trade.setType(tradeToSave.getType());
    trade.setBuyQuantity(tradeToSave.getBuyQuantity());
    save(trade);
  }
}
