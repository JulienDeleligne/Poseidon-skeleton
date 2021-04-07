package com.nnk.springboot.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTests {

  @Autowired
  private RuleNameRepository ruleNameRepository;

  @Test
  public void ruleTest() {
    RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

    // Save
    rule = ruleNameRepository.save(rule);
    assertThat(rule.getId()).isNotNull();
    assertThat(rule.getName()).isEqualTo("Rule Name");

    // Update
    rule.setName("Rule Name Update");
    rule = ruleNameRepository.save(rule);
    assertThat(rule.getName()).isEqualTo("Rule Name Update");

    // Find
    List<RuleName> listResult = ruleNameRepository.findAll();
    assertThat(listResult.size() > 0).isTrue();

    // Delete
    Integer id = rule.getId();
    ruleNameRepository.delete(rule);
    Optional<RuleName> ruleList = ruleNameRepository.findById(id);
    assertThat(ruleList).isNotPresent();
  }
}
