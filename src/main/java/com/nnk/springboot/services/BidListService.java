package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidListService {

  @Autowired
  private BidListRepository bidListRepository;

  public List<BidList> findAll() {
    return bidListRepository.findAll();
  }

  public void save(BidList bid) {
    bidListRepository.save(bid);
  }

  public BidList findById(Integer id) {
    return bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
  }

  public void delete(Integer id) {
    bidListRepository.deleteById(id);
  }

  public void deleteAll() {
    bidListRepository.deleteAll();
  }
}
