package co.istad.chaya.itegen3eco.domain1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Entity
@Getter
@NoArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    private String userId;
    private String biography;
    private String facebookLink;
    private String githubLink;
    private String jobTitle;
    private String major;
    private String phoneNumber;
    private String university;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;
}
