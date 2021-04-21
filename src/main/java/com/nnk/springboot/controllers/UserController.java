package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The User controller.
 */
@Controller
public class UserController {

  @Autowired
  private UserRepository userRepository;

  /**
   * Add list of User to the model and redirect to bidList/list
   *
   * @return user/list
   */
  @RolesAllowed("ADMIN")
  @RequestMapping("/user/list")
  public String home(Model model) {
    model.addAttribute("users", userRepository.findAll());
    return "user/list";
  }

  /**
   * Redirect to the add menu
   *
   * @return user/add
   */
  @GetMapping("/user/add")
  public String addUser(User bid) {
    return "user/add";
  }

  /**
   * Check that the User is valid and save it.
   * Then find all User and add them to the model
   * If it's all good, it redirect to user/list. If not, it stays on the add page
   */
  @PostMapping("/user/validate")
  public String validate(@Valid User user, BindingResult result, Model model) {
    if (!result.hasErrors()) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      user.setPassword(encoder.encode(user.getPassword()));
      userRepository.save(user);
      model.addAttribute("users", userRepository.findAll());
      return "redirect:/user/list";
    }
    return "user/add";
  }

  /**
   * Add the User for this ID to the model and redirect to user/update
   *
   * @return user/update
   */
  @GetMapping("/user/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    user.setPassword("");
    model.addAttribute("user", user);
    return "user/update";
  }

  /**
   * Check that the User is valid and update it.
   * Then find all User and add them to the model
   * If it's all good, it redirect to user/list. If not, it stays on the update page
   */
  @PostMapping("/user/update/{id}")
  public String updateUser(@PathVariable("id") Integer id, @Valid User user,
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "user/update";
    }

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    user.setPassword(encoder.encode(user.getPassword()));
    user.setId(id);
    userRepository.save(user);
    model.addAttribute("users", userRepository.findAll());
    return "redirect:/user/list";
  }

  /**
   * Delete the User for this ID
   *
   * @return user/list
   */
  @GetMapping("/user/delete/{id}")
  public String deleteUser(@PathVariable("id") Integer id, Model model) {
    User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
    userRepository.delete(user);
    model.addAttribute("users", userRepository.findAll());
    return "redirect:/user/list";
  }
}
