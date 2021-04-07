package com.nnk.springboot;

import com.nnk.springboot.services.BidListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

  @Autowired
  BidListService bidListService;

  @Test
  public void contextLoads() throws Exception {
  }
}
