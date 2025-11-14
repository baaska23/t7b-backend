package org.t7b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThesisTopicDTO {
    private Long professorId;
    private Long classId;
    private String title;
    private String description;
    private String status;
}

