package co.istad.chaya.itegen3eco.features.order;


import co.istad.chaya.itegen3eco.features.order.dto.CreateOrderRequest;
import co.istad.chaya.itegen3eco.features.order.dto.OrderResponse;
import co.istad.chaya.itegen3eco.features.order.dto.OrderStatusUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponse createNew(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.createNew(createOrderRequest);

    }

    @GetMapping
    public Page<OrderResponse> findAll(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "25") int pageSize
    ) {
        return orderService.findAll(pageNumber, pageSize);
    }

    // 3. Find order by ID
    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable UUID id) {
        return orderService.findById(id);
    }

    // 4. Soft delete order by ID
    @PutMapping("/{id}/soft-delete")
    public OrderResponse softDeleteOrder(@PathVariable UUID id) {
        return orderService.softDeleteOrder(id);
    }

    // 5. Hard delete order by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hardDeleteOrder(@PathVariable UUID id) {
        orderService.hardDeleteOrder(id);
    }

    // 6. Update order status by ID
    @PutMapping("/{id}/status")
    public OrderResponse updateStatus(
            @PathVariable UUID id,
            @Valid @RequestBody OrderStatusUpdateRequest statusUpdateRequest
    ) {
        return orderService.updateStatus(id, statusUpdateRequest);
    }
}
