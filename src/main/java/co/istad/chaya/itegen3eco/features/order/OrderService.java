package co.istad.chaya.itegen3eco.features.order;

import co.istad.chaya.itegen3eco.features.order.dto.CreateOrderRequest;
import co.istad.chaya.itegen3eco.features.order.dto.OrderResponse;
import co.istad.chaya.itegen3eco.features.order.dto.OrderStatusUpdateRequest;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OrderService {
    OrderResponse createNew(CreateOrderRequest createOrderRequest);

    Page<OrderResponse> findAll(int pageNumber, int pageSize);

    OrderResponse findById(UUID id);

    OrderResponse softDeleteOrder(UUID id);

    void hardDeleteOrder(UUID id);

    OrderResponse updateStatus(UUID id, OrderStatusUpdateRequest statusUpdateRequest);
}
