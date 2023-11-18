package fit.iuh.edu.vn.week7.repositories;

import fit.iuh.edu.vn.week7.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}