package co.istad.chaya.itegen3eco.features.file;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "files")
@Setter
@Getter
@NoArgsConstructor

public class FileUpLoad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;
    private String caption;


    private Long size;
    private String mediaType;
}
