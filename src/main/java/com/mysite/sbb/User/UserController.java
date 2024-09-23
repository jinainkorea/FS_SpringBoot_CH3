package com.mysite.sbb.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
  private final UserService userService;

  @GetMapping("/signup")
  public String signup(UserCreateForm userCreateForm) {
    return "signup_form";
  }

  @PostMapping("/signup")
  public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "signup_form";
    }
    if (!userCreateForm.getPassword().equals(userCreateForm.getPasswordConfirm())) {
      bindingResult.rejectValue("passwordConfirm", "passwordInCorrect", "비밀번호 두 개가 일치하지 않습니다.");
      return "signup_form";
    }
    userService.create(userCreateForm.getUsername(), userCreateForm.getNickname(), userCreateForm.getPassword());
    return "redirect:/";
  }
}
