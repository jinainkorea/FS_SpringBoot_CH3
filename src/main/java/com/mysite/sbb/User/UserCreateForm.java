package com.mysite.sbb.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
  @NotEmpty(message = "사용자ID는 필수입력사항입니다.")
  @Size(min = 3, max = 25)
  private String username;

  @NotEmpty(message = "비밀번호는 필수입력사항입니다.")
  private String password;

  @NotEmpty(message = "비밀번호 확인은 필수입력사항입니다.")
  private String passwordConfirm;

  @NotEmpty(message = "닉네임은 필수입력사항입니다.")
  @Size(min = 3, max = 25)
  private String nickname;
}
