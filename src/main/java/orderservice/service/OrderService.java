package orderservice.service;

import lombok.RequiredArgsConstructor;
import orderservice.data.Order;
import orderservice.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order findById(UUID id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Page<Order> findByOperatorId(UUID id, Pageable pageable) {
        return orderRepository.findByOperatorId(id, pageable);
    }

    public Page<Order> findWithoutOperator(Pageable pageable) {
        return orderRepository.findByOperatorId(null, pageable);
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void changeOperatorId(UUID orderId, UUID operatorId) {
        Order order = findById(orderId);
        order.setOperatorId(operatorId);
        orderRepository.save(order);
    }

    public void comment(UUID orderId, String comment) {
        Order order = findById(orderId);
        order.setComment(comment);
        orderRepository.save(order);
    }

    public Long getStat(UUID operatorId) {
        return orderRepository.countOrdersByOperatorId(operatorId);
    }

    public void setDeclineReason(UUID orderId, String reason) {
        Order order = findById(orderId);
        order.setDeclineReason(reason);
        orderRepository.save(order);
    }
}
