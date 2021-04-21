package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * BidList service.
 */
@Service
@Transactional
public class BidListService {

  @Autowired
  private BidListRepository bidListRepository;

  /**
   * Find all BidList.
   *
   * @return List of BidList
   */
  public List<BidList> findAll() {
    return bidListRepository.findAll();
  }

  /**
   * Save the BidList.
   * First check that the bidList is not null. If it's null, throw a IllegalArgumentException
   *
   * @param bid BidList to save
   */
  public void save(BidList bid) {
    Assert.notNull(bid, "BidList must not be null");
    bidListRepository.save(bid);
  }

  /**
   * Find the BidList by id.
   * First check that the id is not null. If it's null, throw a IllegalArgumentException
   * Then search the BidList. If it doesn't exist, throw a IllegalArgumentException
   *
   * @param id The BidList id
   * @return BidList found
   */
  public BidList findById(Integer id) {
    Assert.notNull(id, "id must not be null");
    return bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
  }

  /**
   * Delete the BidList by id
   *
   * @param id The BidList id
   */
  public void delete(Integer id) {
    bidListRepository.deleteById(findById(id).getBidListId());
  }

  /**
   * check that the bidListToSave is not null, then update the BidList and save it.
   *
   * @param bidListToSave The BidList to save
   * @param id The BidList id to update
   */
  public void update(BidList bidListToSave, Integer id) {
    Assert.notNull(bidListToSave, "BidListToSave must not be null");
    BidList bidList = findById(id);
    bidList.setBidQuantity(bidListToSave.getBidQuantity());
    save(bidList);
  }
}
