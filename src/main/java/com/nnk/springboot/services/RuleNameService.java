package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * RuleName service.
 */
@Service
@Transactional
public class RuleNameService {

  @Autowired
  private RuleNameRepository ruleNameRepository;

  /**
   * Find all RuleName.
   *
   * @return List of RuleName
   */
  public List<RuleName> findAll() {
    return ruleNameRepository.findAll();
  }

  /**
   * Save the RuleName.
   * First check that the ruleName is not null. If it's null, throw a IllegalArgumentException
   *
   * @param ruleName RuleName to save
   */
  public void save(RuleName ruleName) {

    Assert.notNull(ruleName, "RuleName must not be null");
    ruleNameRepository.save(ruleName);
  }

  /**
   * Find the RuleName by id.
   * First check that the id is not null. If it's null, throw a IllegalArgumentException
   * Then search the RuleName. If it doesn't exist, throw a IllegalArgumentException
   *
   * @param id The RuleName id
   * @return RuleName found
   */
  public RuleName findById(Integer id) {
    Assert.notNull(id, "id must not be null");
    return ruleNameRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid RuleName Id:" + id));
  }

  /**
   * Delete the RuleName by id
   *
   * @param id The RuleName id
   */
  public void delete(Integer id) {
    ruleNameRepository.deleteById(findById(id).getId());
  }

  /**
   * check that the ruleNameToSave is not null, then update the RuleName and save it.
   *
   * @param ruleNameToSave The RuleName to save
   * @param id The RuleName id to update
   */
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
