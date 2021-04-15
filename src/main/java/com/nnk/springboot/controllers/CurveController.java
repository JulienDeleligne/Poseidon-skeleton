package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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
public class CurveController {

  private static final String CURVEPOINTLINK = "curvePoint";
  private static final String CURVEPOINTREDIRECT = "redirect:/curvePoint/list";

  @Autowired
  private CurvePointService curvePointService;

  @RequestMapping("/curvePoint/list")
  public String home(Model model) {
    model.addAttribute(CURVEPOINTLINK, curvePointService.findAll());
    return "curvePoint/list";
  }

  @GetMapping("/curvePoint/add")
  public String addCurvePointForm(CurvePoint curve) {
    return "curvePoint/add";
  }

  @PostMapping("/curvePoint/validate")
  public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
    if (!result.hasErrors()) {
      curvePointService.save(curvePoint);
      model.addAttribute(CURVEPOINTLINK, curvePointService.findAll());
      return CURVEPOINTREDIRECT;
    }
    return "curvePoint/add";
  }

  @GetMapping("/curvePoint/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    model.addAttribute(CURVEPOINTLINK, curvePointService.findById(id));
    return "curvePoint/update";
  }

  @PostMapping("/curvePoint/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePointToSave, BindingResult result,
      Model model) {
    if (result.hasErrors()) {
      return "curvePoint/update";
    }
    curvePointService.update(curvePointToSave, id);
    model.addAttribute(CURVEPOINTLINK, curvePointService.findAll());
    return CURVEPOINTREDIRECT;
  }

  @GetMapping("/curvePoint/delete/{id}")
  public String deleteBid(@PathVariable("id") Integer id, Model model) {
    curvePointService.delete(id);
    model.addAttribute(CURVEPOINTLINK, curvePointService.findAll());
    return CURVEPOINTREDIRECT;
  }
}
