package org.t7b.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnalyticsInternalDTO {
    private Long dbUsage;
    private Long storageUsage;
}
