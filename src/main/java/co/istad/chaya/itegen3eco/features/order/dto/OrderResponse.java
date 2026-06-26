package co.istad.chaya.itegen3eco.features.order.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        String customerId,
        String address,
        Float discount,
        String remark,
        Boolean status,
        LocalDateTime orderAt,
        Boolean isDeleted
) {
}
