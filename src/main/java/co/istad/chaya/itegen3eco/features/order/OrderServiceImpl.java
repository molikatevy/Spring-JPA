package co.istad.chaya.itegen3eco.features.order;

import co.istad.chaya.itegen3eco.features.order.dto.CreateOrderRequest;
import co.istad.chaya.itegen3eco.features.order.dto.OrderLineDto;
import co.istad.chaya.itegen3eco.features.order.dto.OrderResponse;
import co.istad.chaya.itegen3eco.features.order.dto.OrderStatusUpdateRequest;
import co.istad.chaya.itegen3eco.features.product.Product;
import co.istad.chaya.itegen3eco.features.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    @Transactional  // ADD THIS
    public OrderResponse createNew(CreateOrderRequest createOrderRequest) {
        // Validate order lines
        if (createOrderRequest.orderLines() == null || createOrderRequest.orderLines().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order lines cannot be empty");
        }

        // Create the order first
        Order order = new Order();
        order.setAddress(createOrderRequest.address());
        order.setCustomerId("ISTAD");
        order.setDiscount(createOrderRequest.discount());
        order.setRemark(createOrderRequest.remark());
        order.setIsDeleted(false);
        order.setOrderedAt(LocalDateTime.now());
        order.setStatus(false);

        // Process each order line
        List<OrderLine> orderLines = new ArrayList<>();

        for (OrderLineDto orderLineDto : createOrderRequest.orderLines()) {
            // Check if code is null or empty
            if (orderLineDto.code() == null || orderLineDto.code().trim().isEmpty()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Product code is required"
                );
            }

            // Find product
            Product product = productRepository.findByCode(orderLineDto.code().trim())
                    .orElseThrow(() -> {
                        log.error("Product not found with code: {}", orderLineDto.code());
                        return new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Product not found with code: " + orderLineDto.code()
                        );
                    });

            // Create order line with order reference
            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantity(orderLineDto.qty());
            orderLine.setUnitPrice(orderLineDto.unitPrice());
            orderLine.setOrder(order);  // CRITICAL: Set the order reference
            orderLine.setIsDeleted(false);

            orderLines.add(orderLine);
        }

        order.setOrderLine(orderLines);

        // Save order (cascade will save order lines if configured)
        Order savedOrder = orderRepository.save(order);

        return orderMapper.mapOrderToResponse(savedOrder);
    }


    @Override
    public Page<OrderResponse> findAll(int pageNumber, int pageSize) {
        log.info("Finding all orders with pagination - page: {}, size: {}", pageNumber, pageSize);

        Sort sort = Sort.by(Sort.Direction.DESC, "orderedAt");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page<Order> orders = orderRepository.findAll(pageRequest);
        return orders.map(orderMapper::mapOrderToResponse);
    }


    // 3. Find order by ID
    @Override
    public OrderResponse findById(UUID id) {
        log.info("Finding order by ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order not found with ID: {}", id);
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Order not found with ID: " + id
                    );
                });

        return orderMapper.mapOrderToResponse(order);
    }
//
//    // 4. Soft delete order by ID
    @Override
    @Transactional
    public OrderResponse softDeleteOrder(UUID id) {
        log.info("Soft deleting order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order not found with ID: {}", id);
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Order not found with ID: " + id
                    );
                });

        if (order.getIsDeleted()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Order is already deleted"
            );
        }

        order.setIsDeleted(true);
        Order updatedOrder = orderRepository.save(order);

        log.info("Order soft deleted successfully with ID: {}", id);
        return orderMapper.mapOrderToResponse(updatedOrder);
    }

    // 5. Hard delete order by ID
    @Override
    @Transactional
    public void hardDeleteOrder(UUID id) {
        log.info("Hard deleting order with ID: {}", id);

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order not found with ID: {}", id);
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Order not found with ID: " + id
                    );
                });

        orderRepository.delete(order);
//        log.info("Order hard deleted successfully with ID: {}", id);
    }

    // 6. Update order status by ID
    @Override
    @Transactional
    public OrderResponse updateStatus(UUID id, OrderStatusUpdateRequest statusUpdateRequest) {
        log.info("Updating status for order with ID: {}", id);
        log.info("New status: {}", statusUpdateRequest.status());

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order not found with ID: {}", id);
                    return new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Order not found with ID: " + id
                    );
                });

        if (order.getIsDeleted()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot update status of deleted order"
            );
        }

        order.setStatus(statusUpdateRequest.status());
        Order updatedOrder = orderRepository.save(order);

        log.info("Order status updated successfully for ID: {}", id);
        return orderMapper.mapOrderToResponse(updatedOrder);
    }
}