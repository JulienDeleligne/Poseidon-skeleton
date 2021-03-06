package com.nnk.springboot.domain;

import static org.assertj.core.api.Java6Assertions.assertThat;

import com.nnk.springboot.repositories.BidListRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BidTests {

  @Autowired
  private BidListRepository bidListRepository;

  @Test
  public void bidListTest() {
    BidList bid = new BidList("Account Test", "Type Test", 10d);

    // Save
    bid = bidListRepository.save(bid);
    assertThat(bid.getBidListId()).isNotNull();
    assertThat(bid.getBidQuantity()).isEqualTo(10d);

    // Update
    bid.setBidQuantity(20d);
    bid = bidListRepository.save(bid);
    assertThat(bid.getBidQuantity()).isEqualTo(20d);

    // Find
    List<BidList> listResult = bidListRepository.findAll();
    assertThat(listResult.size() > 0).isTrue();

    // Delete
    Integer id = bid.getBidListId();
    bidListRepository.delete(bid);
    Optional<BidList> bidList = bidListRepository.findById(id);
    Assertions.assertThat(bidList).isNotPresent();
  }
}
