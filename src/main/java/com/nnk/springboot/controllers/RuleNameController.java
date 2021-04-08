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

  @Autowired
  private RuleNameService ruleNameService;

  @RequestMapping("/ruleName/list")
  public String home(Model model) {
    model.addAttribute("ruleName", ruleNameService.findAll());
    return "ruleName/list";
  }

  @GetMapping("/ruleName/add")
  public String addRuleForm(RuleName ruleName) {
    return "ruleName/add";
  }

  @PostMapping("/ruleName/validate")
  public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
    if (!result.hasErrors()) {
      ruleNameService.save(ruleName);
      model.addAttribute("ruleName", ruleNameService.findAll());
      return "redirect:/ruleName/list";
    }
    return "ruleName/add";
  }

  @GetMapping("/ruleName/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("ruleName", ruleNameService.findById(id));
    return "ruleName/update";
  }

  @PostMapping("/ruleName/update/{id}")
  public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleNameToSave,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "ruleName/update";
    }
    RuleName ruleName = ruleNameService.findById(id);
    ruleName.setName(ruleNameToSave.getName());
    ruleName.setDescription(ruleNameToSave.getDescription());
    ruleName.setJson(ruleNameToSave.getJson());
    ruleName.setTemplate(ruleNameToSave.getTemplate());
    ruleName.setSqlStr(ruleNameToSave.getSqlStr());
    ruleName.setSqlPart(ruleNameToSave.getSqlPart());
    ruleNameService.save(ruleName);
    model.addAttribute("ruleName", ruleNameService.findAll());
    return "redirect:/ruleName/list";
  }

  @GetMapping("/ruleName/delete/{id}")
  public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    ruleNameService.delete(ruleNameService.findById(id).getId());
    model.addAttribute("ruleName", ruleNameService.findAll());
    return "redirect:/ruleName/list";
  }
}
