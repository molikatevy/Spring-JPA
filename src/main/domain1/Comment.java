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
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate createAt;
    private  Boolean isDeleted;
    private String text;

    @ManyToOne
    private Comment parentComment;

    @ManyToOne
    private Videos video;

}
