package co.istad.chaya.itegen3eco.features.order;


import co.istad.chaya.itegen3eco.features.order.dto.CreateOrderRequest;
import co.istad.chaya.itegen3eco.features.order.dto.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse mapOrderToResponse(Order order);
}
