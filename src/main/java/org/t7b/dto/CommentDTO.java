package org.t7b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private Long authorId;
    private Long postId;
    private String content;
}
