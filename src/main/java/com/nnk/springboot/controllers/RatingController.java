package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
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
public class RatingController {

  @Autowired
  private RatingService ratingService;

  @RequestMapping("/rating/list")
  public String home(Model model) {
    model.addAttribute("rating", ratingService.findAll());
    return "rating/list";
  }

  @GetMapping("/rating/add")
  public String addRatingForm(Rating rating) {
    return "rating/add";
  }

  @PostMapping("/rating/validate")
  public String validate(@Valid Rating rating, BindingResult result, Model model) {
    if (!result.hasErrors()) {
      ratingService.save(rating);
      model.addAttribute("rating", ratingService.findAll());
      return "redirect:/rating/list";
    }
    return "rating/add";
  }

  @GetMapping("/rating/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("rating", ratingService.findById(id));
    return "rating/update";
  }

  @PostMapping("/rating/update/{id}")
  public String updateRating(@PathVariable("id") Integer id, @Valid Rating ratingToSave,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "rating/update";
    }
    Rating rating = ratingService.findById(id);
    rating.setMoodysRating(ratingToSave.getMoodysRating());
    rating.setSandPRating(ratingToSave.getSandPRating());
    rating.setFitchRating(ratingToSave.getFitchRating());
    rating.setOrderNumber(ratingToSave.getOrderNumber());
    ratingService.save(rating);
    model.addAttribute("rating", ratingService.findAll());
    return "redirect:/rating/list";
  }

  @GetMapping("/rating/delete/{id}")
  public String deleteRating(@PathVariable("id") Integer id, Model model) {
    ratingService.delete(ratingService.findById(id).getId());
    model.addAttribute("rating", ratingService.findAll());
    return "redirect:/rating/list";
  }
}
