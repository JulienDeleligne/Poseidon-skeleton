package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class BidListService {

  @Autowired
  private BidListRepository bidListRepository;

  public List<BidList> findAll() {
    return bidListRepository.findAll();
  }

  public void save(BidList bid) {
    Assert.notNull(bid, "BidList must not be null");
    bidListRepository.save(bid);
  }

  public BidList findById(Integer id) {
    Assert.notNull(id, "id must not be null");
    return bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
  }

  public void delete(Integer id) {
    bidListRepository.deleteById(findById(id).getBidListId());
  }

  public void update(BidList bidListToSave, Integer id) {
    Assert.notNull(bidListToSave, "BidListToSave must not be null");
    BidList bidList = findById(id);
    bidList.setBidQuantity(bidListToSave.getBidQuantity());
    save(bidList);
  }
}
