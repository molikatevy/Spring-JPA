package co.istad.chaya.itegen3eco.domain1;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "courses")
@Setter
@Getter
@NoArgsConstructor
public class Course {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer countRating;
    @Column(length = 6)
    private LocalDate createdAt;
    private String description;
    private Float discountPercent;
    private Boolean isDeleted;
    private Boolean isPublished;
    private String keyword;
    private String level;

    private Double price;

    private Float starRating;

    private String thumbnail;
    private String title;
    private Float totalHours;
    private LocalDate updatedAt;

    @ManyToOne
    private Category category;

    @ManyToOne
    private InstructorProfile instructor;

}
