package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class RuleNameService {

  @Autowired
  private RuleNameRepository ruleNameRepository;

  public List<RuleName> findAll() {
    return ruleNameRepository.findAll();
  }

  public void save(RuleName ruleName) {

    Assert.notNull(ruleName, "RuleName must not be null");
    ruleNameRepository.save(ruleName);
  }

  public RuleName findById(Integer id) {
    Assert.notNull(id, "id must not be null");
    return ruleNameRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
  }

  public void delete(Integer id) {
    ruleNameRepository.deleteById(findById(id).getId());
  }

  public void update(RuleName ruleNameToSave, Integer id) {
    Assert.notNull(ruleNameToSave, "RuleNameToSave must not be null");
    RuleName ruleName = findById(id);
    ruleName.setName(ruleNameToSave.getName());
    ruleName.setDescription(ruleNameToSave.getDescription());
    ruleName.setJson(ruleNameToSave.getJson());
    ruleName.setTemplate(ruleNameToSave.getTemplate());
    ruleName.setSqlStr(ruleNameToSave.getSqlStr());
    ruleName.setSqlPart(ruleNameToSave.getSqlPart());
    save(ruleName);
  }
}
