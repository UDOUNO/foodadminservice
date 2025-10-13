package orderservice.service;

import lombok.RequiredArgsConstructor;
import orderservice.data.Order;
import orderservice.filter.OrderFilter;
import orderservice.filter.specifications.OrderSpecifications;
import orderservice.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilterService {

    private final OrderRepository orderRepository;

    public Page<Order> findAllWithFilters(OrderFilter orderFilter, Pageable pageable) {
        Specification<Order> spec = OrderSpecifications.withFilters(orderFilter);
        return orderRepository.findAll(spec, pageable);
    }
}
