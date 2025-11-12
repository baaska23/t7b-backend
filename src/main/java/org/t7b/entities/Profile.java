package org.t7b.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "university_branch")
    private String universityBranch;
    
    private String major;
    
    @Column(name = "teams_address")
    private String teamsAddress;
    
    public Profile(Long profileId, User user, String universityBranch, String major, String teamsAddress) {
        this.profileId = profileId;
        this.user = user;
        this.universityBranch = universityBranch;
        this.major = major;
        this.teamsAddress = teamsAddress;
    }
}
