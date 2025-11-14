package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "analytics")
@Getter
@Setter
@NoArgsConstructor
public class Analytic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "analytics_id")
    private Long analyticsId;
    
    @Column(name = "total_users")
    private int totalUsers;
    
    @Column(name = "total_theses")
    private int totalTheses;
    
    @Column(name = "db_usage_mb")
    private int dbUsageMb;
    
    @Column(name = "s3_usage_mb")
    private int s3UsageMb;
    
    public Analytic(Long analyticsId, int totalUsers, int totalTheses, int dbUsageMb, int s3UsageMb) {
        this.analyticsId = analyticsId;
        this.totalUsers = totalUsers;
        this.totalTheses = totalTheses;
        this.dbUsageMb = dbUsageMb;
        this.s3UsageMb = s3UsageMb;
    }
    
    public Analytic(String totalUsers, long total, Instant now) {
    }
}
