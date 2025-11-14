package org.t7b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentReplyDTO {
    private Long commentId;
    private Long authorId;
    private String content;
}
