package org.t7b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThesisSubmissionDTO {
    private Long studentId;
    private Long topicId;
    private int version;
    private String thesisLink;
    private String feedback;
    private String annonatedLink;
    private String status;
}
