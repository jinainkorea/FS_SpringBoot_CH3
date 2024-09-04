package com.mysite.sbb.Comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {
    @NotEmpty(message="답글은 필수항목입니다.")
    private String content;
}
