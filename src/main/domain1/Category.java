package co.istad.chaya.itegen3eco.domain1;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String icon;

  @Column(nullable = false)
  private Boolean is_deleted;

  @Column(nullable = false, unique = true, length = 50)
  private String name;


}
