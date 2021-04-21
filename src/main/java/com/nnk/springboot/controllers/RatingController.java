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

/**
 * Controller for the Rating.
 */
@Controller
public class RatingController {

  private static final String RATINGLINK = "rating";
  private static final String RATINGREDIRECT = "redirect:/rating/list";

  @Autowired
  private RatingService ratingService;

  /**
   * Add list of Rating to the model and redirect to rating/list
   *
   * @return rating/list
   */
  @RequestMapping("/rating/list")
  public String home(Model model) {
    model.addAttribute(RATINGLINK, ratingService.findAll());
    return "rating/list";
  }

  /**
   * Redirect to the add menu
   *
   * @return rating/add
   */
  @GetMapping("/rating/add")
  public String addRatingForm(Rating rating) {
    return "rating/add";
  }

  /**
   * Check that the Rating is valid and save it.
   * Then find all Rating and add them to the model
   * If it's all good, it redirect to rating/list. If not, it stays on the add page
   */
  @PostMapping("/rating/validate")
  public String validate(@Valid Rating rating, BindingResult result, Model model) {
    if (!result.hasErrors()) {
      ratingService.save(rating);
      model.addAttribute(RATINGLINK, ratingService.findAll());
      return RATINGREDIRECT;
    }
    return "rating/add";
  }

  /**
   * Add the Rating for this ID to the model and redirect to rating/update
   *
   * @return rating/update
   */
  @GetMapping("/rating/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    model.addAttribute(RATINGLINK, ratingService.findById(id));
    return "rating/update";
  }

  /**
   * Check that the Rating is valid and update it.
   * Then find all Rating and add them to the model
   * If it's all good, it redirect to rating/list. If not, it stays on the update page
   */
  @PostMapping("/rating/update/{id}")
  public String updateRating(@PathVariable("id") Integer id, @Valid Rating ratingToSave,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "rating/update";
    }
    ratingService.update(ratingToSave, id);
    model.addAttribute(RATINGLINK, ratingService.findAll());
    return RATINGREDIRECT;
  }

  /**
   * Delete the Rating for this ID
   *
   * @return rating/list
   */
  @GetMapping("/rating/delete/{id}")
  public String deleteRating(@PathVariable("id") Integer id, Model model) {
    ratingService.delete(id);
    model.addAttribute(RATINGLINK, ratingService.findAll());
    return RATINGREDIRECT;
  }
}
