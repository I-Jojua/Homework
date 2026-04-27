package ge.ibsu.demo.repositories;

import ge.ibsu.demo.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerNameContainingIgnoreCaseAndStatusOrderByCreatedAtDesc(
            String customerName,
            OrderStatus status
    );
}