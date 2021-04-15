package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {

  private static final String TRADELINK = "trade";
  private static final String TRADEREDIRECT = "redirect:/trade/list";

  @Autowired
  private TradeService tradeService;

  @RequestMapping("/trade/list")
  public String home(Model model) {
    model.addAttribute(TRADELINK, tradeService.findAll());
    return "trade/list";
  }

  @GetMapping("/trade/add")
  public String addUser() {
    return "trade/add";
  }

  @PostMapping("/trade/validate")
  public String validate(@Valid Trade trade, BindingResult result, Model model) {
    if (!result.hasErrors()) {
      tradeService.save(trade);
      model.addAttribute(TRADELINK, tradeService.findAll());
      return TRADEREDIRECT;
    }
    return "trade/add";
  }

  @GetMapping("/trade/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    model.addAttribute(TRADELINK, tradeService.findById(id));
    return "trade/update";
  }

  @PostMapping("/trade/update/{id}")
  public String updateTrade(@PathVariable("id") Integer id, @Valid Trade tradeToSave,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "curvePoint/update";
    }
    tradeService.update(tradeToSave, id);
    model.addAttribute(TRADELINK, tradeService.findAll());
    return TRADEREDIRECT;
  }

  @GetMapping("/trade/delete/{id}")
  public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    tradeService.delete(id);
    model.addAttribute(TRADELINK, tradeService.findAll());
    return TRADEREDIRECT;
  }
}
