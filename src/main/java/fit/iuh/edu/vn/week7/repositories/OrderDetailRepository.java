package fit.iuh.edu.vn.week7.repositories;

import fit.iuh.edu.vn.week7.models.Order;
import fit.iuh.edu.vn.week7.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Order> {
}