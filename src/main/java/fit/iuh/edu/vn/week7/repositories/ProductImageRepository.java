package fit.iuh.edu.vn.week7.repositories;

import fit.iuh.edu.vn.week7.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}