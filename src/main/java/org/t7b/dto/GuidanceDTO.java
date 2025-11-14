package org.t7b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuidanceDTO {
    private Long authorId;
    private String title;
    private String content;
}
