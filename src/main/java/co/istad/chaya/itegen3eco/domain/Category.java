package co.istad.chaya.itegen3eco.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="categories")
public class Category {
    @Id
//    if user
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false)
    private Boolean isDelete;

    private String description;
    private String icon;

    @ManyToOne
    private Category parentCategory;
    @OneToMany(mappedBy = "parentCategory",cascade = CascadeType.REMOVE)
    private List<Category> childCategories;

    @OneToMany(mappedBy = "category")
    private List<Product> products;












}