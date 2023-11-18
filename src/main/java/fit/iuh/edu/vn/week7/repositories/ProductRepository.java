package fit.iuh.edu.vn.week7.repositories;

import fit.iuh.edu.vn.week7.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {
}