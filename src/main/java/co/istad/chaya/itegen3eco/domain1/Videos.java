package co.istad.chaya.itegen3eco.domain1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "videos")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Videos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;
    private String duration;
    private Boolean isDelete;
    private Boolean isPublished;
    private String slug;
    private String thumbnail;
    private String title;
    private String youtube;

    @ManyToOne
    private Course course;
}
