package orderservice.filter.specifications;

import jakarta.persistence.criteria.Predicate;
import orderservice.data.Order;
import orderservice.filter.OrderFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class OrderSpecifications {
    public static Specification<Order> withFilters(OrderFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filter.getOperatorName() != null){
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("operatorName")),
                        "%" + filter.getOperatorName().toLowerCase()+"%"
                ));
            }
            if(filter.getStatus() != null){
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
