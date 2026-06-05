package co.istad.chaya.itegen3eco.repoditory;

import co.istad.chaya.itegen3eco.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
