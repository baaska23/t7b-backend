package org.t7b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDTO {
    private Long userId;
    private String title;
    private String content;
    private String type;
}
