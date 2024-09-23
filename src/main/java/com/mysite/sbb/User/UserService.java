package com.mysite.sbb.User;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public SiteUser create(String username, String nickname, String password) {
    SiteUser user = new SiteUser();
    user.setUsername(username);
    user.setNickname(nickname);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    user.setPassword(passwordEncoder.encode(password));
    this.userRepository.save(user);
    return user;
  }
}
