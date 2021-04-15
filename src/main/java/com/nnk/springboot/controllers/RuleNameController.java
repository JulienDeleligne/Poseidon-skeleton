package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RuleNameController {

  private static final String RULENAMELINK = "ruleName";
  private static final String RULENAMEREDIRECT = "redirect:/ruleName/list";

  @Autowired
  private RuleNameService ruleNameService;

  @RequestMapping("/ruleName/list")
  public String home(Model model) {
    model.addAttribute(RULENAMELINK, ruleNameService.findAll());
    return "ruleName/list";
  }

  @GetMapping("/ruleName/add")
  public String addRuleForm() {
    return "ruleName/add";
  }

  @PostMapping("/ruleName/validate")
  public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
    if (!result.hasErrors()) {
      ruleNameService.save(ruleName);
      model.addAttribute(RULENAMELINK, ruleNameService.findAll());
      return RULENAMEREDIRECT;
    }
    return "ruleName/add";
  }

  @GetMapping("/ruleName/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    model.addAttribute(RULENAMELINK, ruleNameService.findById(id));
    return "ruleName/update";
  }

  @PostMapping("/ruleName/update/{id}")
  public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleNameToSave,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "ruleName/update";
    }
    ruleNameService.update(ruleNameToSave, id);
    model.addAttribute(RULENAMELINK, ruleNameService.findAll());
    return RULENAMEREDIRECT;
  }

  @GetMapping("/ruleName/delete/{id}")
  public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    ruleNameService.delete(id);
    model.addAttribute(RULENAMELINK, ruleNameService.findAll());
    return RULENAMEREDIRECT;
  }
}
