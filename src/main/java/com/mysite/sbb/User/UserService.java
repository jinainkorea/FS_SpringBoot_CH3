package com.mysite.sbb.User;

import com.mysite.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

  public SiteUser getUser(String username) {
    Optional<SiteUser> user = this.userRepository.findByusername(username);
    if (user.isPresent()) {
      return user.get();
    } else {
      throw new DataNotFoundException("site_user not found");
    }
  }
}
