package org.t7b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThesisExampleDTO {
    private Long userId;
    private Long approvedById;
    private String title;
    private int grade;
    private String thesisLink;
}
