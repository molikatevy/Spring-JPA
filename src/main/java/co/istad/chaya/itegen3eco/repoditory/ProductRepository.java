package co.istad.chaya.itegen3eco.repoditory;

import co.istad.chaya.itegen3eco.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
