package org.t7b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentClassDTO {
    private Long professorId;
    private String className;
    private String description;
}
