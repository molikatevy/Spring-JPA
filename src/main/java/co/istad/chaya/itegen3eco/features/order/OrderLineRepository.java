package co.istad.chaya.itegen3eco.features.order;




import co.istad.chaya.itegen3eco.features.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {

//    Optional<OrderLine> findByCode(String code);

//
//    Boolean existsByName(String name);
}
