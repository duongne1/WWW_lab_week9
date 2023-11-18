package fit.iuh.edu.vn.week7.ids;

import fit.iuh.edu.vn.week7.models.Product;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProductPricePK implements Serializable {
    private Product product;
    private LocalDateTime price_date_time;
}
