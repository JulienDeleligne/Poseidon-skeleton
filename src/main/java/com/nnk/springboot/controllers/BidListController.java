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

  @Autowired
  private BidListService bidListService;

  @RequestMapping("/bidList/list")
  public String home(Model model) {
    model.addAttribute("bidList", bidListService.findAll());
    return "bidList/list";
  }

  @GetMapping("/bidList/add")
  public String addBidForm(BidList bid) {
    return "bidList/add";
  }

  @PostMapping("/bidList/validate")
  public String validate(@Valid BidList bid, BindingResult result, Model model) {
    if (!result.hasErrors()) {
      bidListService.save(bid);
      model.addAttribute("bidList", bidListService.findAll());
      return "redirect:/bidList/list";
    }
    return "bidList/add";
  }

  @GetMapping("/bidList/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("bidList", bidListService.findById(id));
    return "bidList/update";
  }

  @PostMapping("/bidList/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidListToSave,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "bidList/update";
    }
    BidList bidList = bidListService.findById(id);
    bidList.setBidQuantity(bidListToSave.getBidQuantity());
    bidListService.save(bidList);
    model.addAttribute("bidList", bidListService.findAll());
    return "redirect:/bidList/list";
  }

  @GetMapping("/bidList/delete/{id}")
  public String deleteBid(@PathVariable("id") Integer id, Model model) {
    bidListService.delete(bidListService.findById(id).getBidListId());
    model.addAttribute("bidList", bidListService.findAll());
    return "redirect:/bidList/list";
  }
}
