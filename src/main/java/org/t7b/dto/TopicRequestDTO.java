package org.t7b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicRequestDTO {
    private Long topicId;
    private Long studentId;
    private String message;
    private String status;
}
