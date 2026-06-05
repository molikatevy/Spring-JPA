package co.istad.chaya.itegen3eco.domain1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate enrolledAt;
    private LocalDate paymentAt;
    private String paymentMethod;
    private Boolean paymentStatus;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Student student;

}
