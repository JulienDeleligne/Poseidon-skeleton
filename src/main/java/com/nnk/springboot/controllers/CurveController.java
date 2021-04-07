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

  @Autowired
  private CurvePointService curvePointService;

  @RequestMapping("/curvePoint/list")
  public String home(Model model) {
    model.addAttribute("curvePoint", curvePointService.findAll());
    return "curvePoint/list";
  }

  @GetMapping("/curvePoint/add")
  public String addCurvePointForm(CurvePoint curvePoint) {
    return "curvePoint/add";
  }

  @PostMapping("/curvePoint/validate")
  public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
    if (!result.hasErrors()) {
      curvePointService.save(curvePoint);
      model.addAttribute("curvePoint", curvePointService.findAll());
      return "redirect:/curvePoint/list";
    }
    return "curvePoint/add";
  }

  @GetMapping("/curvePoint/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    model.addAttribute("curvePoint", curvePointService.findById(id));
    return "curvePoint/update";
  }

  @PostMapping("/curvePoint/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePointToSave, BindingResult result,
      Model model) {
    if (result.hasErrors()) {
      return "curvePoint/update";
    }
    CurvePoint curvePoint = curvePointService.findById(id);
    curvePoint.setCurveId(curvePointToSave.getCurveId());
    curvePoint.setTerm(curvePointToSave.getTerm());
    curvePoint.setValue(curvePointToSave.getValue());
    curvePointService.save(curvePoint);
    model.addAttribute("curvePoint", curvePointService.findAll());
    return "redirect:/curvePoint/list";
  }

  @GetMapping("/curvePoint/delete/{id}")
  public String deleteBid(@PathVariable("id") Integer id, Model model) {
    curvePointService.delete(curvePointService.findById(id).getId());
    model.addAttribute("curvePoint", curvePointService.findAll());
    return "redirect:/curvePoint/list";
  }
}
