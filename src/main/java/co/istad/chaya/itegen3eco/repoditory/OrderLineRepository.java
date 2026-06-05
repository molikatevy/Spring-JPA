package co.istad.chaya.itegen3eco.repoditory;

import co.istad.chaya.itegen3eco.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
