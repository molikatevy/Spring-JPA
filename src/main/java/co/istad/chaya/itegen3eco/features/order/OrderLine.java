package co.istad.chaya.itegen3eco.features.order;

import co.istad.chaya.itegen3eco.features.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_lines")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "is_deleted",nullable = false)
    private Boolean isDeleted;

    private Integer quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;






}