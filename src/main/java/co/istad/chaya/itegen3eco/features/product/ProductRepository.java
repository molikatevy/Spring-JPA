package co.istad.chaya.itegen3eco.features.product;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByCode(String code);
    Boolean existsByName(String name);



}
