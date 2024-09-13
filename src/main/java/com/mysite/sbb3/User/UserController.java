package com.mysite.sbb3.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping("/signup")
  public String signup(UserCreateForm userCreateForm) {
    return "signup_form";
  }

  @PostMapping("/signup")
  public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
    if(bindingResult.hasErrors()) {
      return "signup_form";
    }

    if(!userCreateForm.getPassword().equals(userCreateForm.getPassword_check())) {
      bindingResult.rejectValue("password_check", "passwordInCorrect", "두개의 비밀번호가 일치하지 않습니다.");
    }

    userService.signup(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword());
    return "redirect:/";
  }

  @GetMapping("/login")
  public String login() {
    return "login_form";
  }


}
