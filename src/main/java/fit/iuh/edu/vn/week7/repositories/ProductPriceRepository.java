package fit.iuh.edu.vn.week7.repositories;

import fit.iuh.edu.vn.week7.models.Product;
import fit.iuh.edu.vn.week7.models.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Product> {
}