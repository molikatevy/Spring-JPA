package co.istad.chaya.itegen3eco.domain1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@Getter
@NoArgsConstructor
@Table(name = "instructor_profiles")
public class InstructorProfile {
    @Id

    private String userId;
    private String biography;
    private String facebookLink;
    private String githubLink;
    private String jobTitle;
    private String phoneNumber;
}
