package orderservice.repository;

import orderservice.data.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Reservation, UUID>, JpaSpecificationExecutor<Reservation> {
    Page<Reservation> findByOperatorId(UUID operatorId, Pageable pageable);

    Long countOrdersByOperatorId(UUID operatorId);

    List<Reservation> findByClientId(UUID clientId);
}
