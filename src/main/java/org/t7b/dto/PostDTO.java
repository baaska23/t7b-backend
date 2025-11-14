package org.t7b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    private Long authorId;
    private Long classId;
    private String content;
    private String type;
}
