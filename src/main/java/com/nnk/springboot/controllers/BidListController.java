package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {

  private static final String BIDLISTLINK = "bidList";
  private static final String BIDLISTREDIRECT = "redirect:/bidList/list";

  @Autowired
  private BidListService bidListService;

  @RequestMapping("/bidList/list")
  public String home(Model model) {
    model.addAttribute(BIDLISTLINK, bidListService.findAll());
    return "bidList/list";
  }

  @GetMapping("/bidList/add")
  public String addBidForm() {
    return "bidList/add";
  }

  @PostMapping("/bidList/validate")
  public String validate(@Valid BidList bid, BindingResult result, Model model) {
    if (!result.hasErrors()) {
      bidListService.save(bid);
      model.addAttribute(BIDLISTLINK, bidListService.findAll());
      return BIDLISTREDIRECT;
    }
    return "bidList/add";
  }

  @GetMapping("/bidList/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    model.addAttribute(BIDLISTLINK, bidListService.findById(id));
    return "bidList/update";
  }

  @PostMapping("/bidList/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidListToSave,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "bidList/update";
    }
    bidListService.update(bidListToSave, id);
    model.addAttribute(BIDLISTLINK, bidListService.findAll());
    return BIDLISTREDIRECT;
  }

  @GetMapping("/bidList/delete/{id}")
  public String deleteBid(@PathVariable("id") Integer id, Model model) {
    bidListService.delete(id);
    model.addAttribute(BIDLISTLINK, bidListService.findAll());
    return BIDLISTREDIRECT;
  }
}
