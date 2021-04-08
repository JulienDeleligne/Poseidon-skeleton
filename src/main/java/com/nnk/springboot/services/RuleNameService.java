package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleNameService {

  @Autowired
  private RuleNameRepository ruleNameRepository;

  public List<RuleName> findAll() {
    return ruleNameRepository.findAll();
  }

  public void save(RuleName ruleName) {
    ruleNameRepository.save(ruleName);
  }

  public RuleName findById(Integer id) {
    return ruleNameRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
  }

  public void delete(Integer id) {
    ruleNameRepository.deleteById(id);
  }
}
